package start.getreport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getinspectionreport {                                                  //检验合格证
    @RequestMapping(value = "getinspectionreport")
    public @ResponseBody ResponseEntity<byte[]> getinspectionreport(String codedmarking, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;


        String erweima = null;
        String erweimapath = null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

        ResponseEntity<byte[]> download = null;
        File filepdf1 = null;
        File filepdf = null;

//        try {
            String realPath = request.getSession().getServletContext().getRealPath("");
            String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
            String uploadPath = path + "upload";                                                //获取文件名称

            PdfReader reader = null;
            PdfStamper stamper = null;

            //创建一个pdf读入流
            reader = new PdfReader(uploadPath+File.separator+"检验合格证.pdf");
            //根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
            String newpath = uploadPath+File.separator+ UUID.randomUUID().toString() +".pdf";
            stamper = new PdfStamper(reader,new FileOutputStream(newpath));
            //这个字体是itext-asian.jar中自带的 所以不用考虑操作系统环境问题.
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            //baseFont不支持字体样式设定.但是font字体要求操作系统支持此字体会带来移植问题.
            Font font = new Font(bf,10);
            font.setStyle(Font.BOLD);
            font.getBaseFont();
            PdfContentByte over;
            //页数是从1开始的
            for (int i=1; i<=reader.getNumberOfPages(); i++){
                //获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
                over = stamper.getOverContent(i);
                //当前页的下层打印内容  按自己需求选择
                //over = stamper.getUnderContent(i);
                //用pdfreader获得当前页字典对象.包含了该页的一些数据.比如该页的坐标轴信息.
                PdfDictionary p = reader.getPageN(i);
                //拿到mediaBox 里面放着该页pdf的大小信息.
                PdfObject po =  p.get(new PdfName("MediaBox"));
                //po是一个数组对象.里面包含了该页pdf的坐标轴范围.
                PdfArray pa = (PdfArray) po;
                //开始写入文本
                over.beginText();
                //设置字体和大小
                over.setFontAndSize(font.getBaseFont(), 7);
                //设置字体颜色
                over.setColorFill(new BaseColor(0,0,0,255));
                com.itextpdf.text.pdf.PdfGState gState = new PdfGState();
                gState.setStrokeOpacity(0.1f);
                over.setGState(gState);

                ps = conn.prepareStatement("SELECT * FROM putmaterialcache WHERE codedmarking = ? AND status = 1");
                ps.setString(1,codedmarking);
                rs = ps.executeQuery();
                if(rs.next()){
                    //要输出的text          对齐方式          写的字        设置字体的输出位置  字体是否旋转
                    over.showTextAligned(0,codedmarking,220,425,0);
                    erweima = codedmarking;

                    if(!(rs.getString("modelstand_id_modelstand")==null||rs.getString("modelstand_id_modelstand").equals(""))) {
                        over.showTextAligned(0,rs.getString("modelstand_id_modelstand"),307,425,0);
                        erweima = erweima + "," + rs.getString("modelstand_id_modelstand");
                    }

                    if(!(rs.getString("contraststand_id_designation")==null||rs.getString("contraststand_id_designation").equals(""))) {
                        over.showTextAligned(0,rs.getString("contraststand_id_designation"),220,402,0);
                        erweima = erweima + "," + rs.getString("contraststand_id_designation");
                    }


                    if(!(rs.getString("heatbatchno")==null||rs.getString("heatbatchno").equals(""))) {
                        over.showTextAligned(0, rs.getString("heatbatchno"), 307, 402, 0);
                        erweima = erweima + "," + rs.getString("heatbatchno");
                    }

                    if(!(rs.getString("spec")==null||rs.getString("spec").equals(""))) {
                        over.showTextAligned(0, rs.getString("spec"), 220, 387, 0);
                        erweima = erweima + "," + rs.getString("spec");
                    }

                    if(!(rs.getString("dimension")==null||rs.getString("dimension").equals(""))){
                        over.showTextAligned(0,rs.getString("dimension"),220,377,0);
                        erweima = erweima + "," + rs.getString("dimension");
                    }


                    if(!(rs.getString("millunit_id_millunit")==null||rs.getString("millunit_id_millunit").equals(""))){
                        over.showTextAligned(0,rs.getString("millunit_id_millunit"),307,382,0);
                        erweima = erweima + "," + rs.getString("millunit_id_millunit");
                    }



                    ps1 = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
                    ps1.setString(1,rs.getString("user_id"));
                    rs1 = ps1.executeQuery();
                    if(rs1.next()){
                        over.showTextAligned(0,rs1.getString("name"),220,358,0);
                        erweima = erweima + "," + rs1.getString("name");
                    }
                    rs1.close();
                    ps1.close();

                    if(!(rs.getString("indate")==null||rs.getString("indate").equals(""))){
                        calendar.setTime(rs.getDate("indate"));
                        over.showTextAligned(0,sdf.format(calendar.getTime()),307,358,0);
                        erweima = erweima + "," + sdf.format(calendar.getTime());
                    }


                    over.endText();

                    ps1 = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
                    rs1 = ps1.executeQuery();
                    if(rs1.next()){
                        if(rs1.getString("logo") != null && !rs1.getString("logo").equals("")){
                            try{
                                //创建一个image对象.
                                Image image = Image.getInstance(rs1.getString("logo"));
                                //设置image对象的输出位置pa.getAsNumber(pa.size()-1).floatValue() 是该页pdf坐标轴的y轴的最大值  0, 0, 841.92, 595.32
                                image.setAbsolutePosition(195,443);
                                //设置插入的图片大小
                                image.scaleToFit(50,50);
                                over.addImage(image);
                            }catch (Exception e){ }
                        }
                    }


                    erweimapath = QRCodeUtil.getQRCode(request,erweima,105,105);
                    System.out.println(erweimapath);

                    //创建一个image对象.
                    Image image1 = Image.getInstance(erweimapath);
                    //设置image对象的输出位置pa.getAsNumber(pa.size()-1).floatValue() 是该页pdf坐标轴的y轴的最大值  0, 0, 841.92, 595.32
                    image1.setAbsolutePosition(343,430);
                    //设置插入的图片大小
                    image1.scaleToFit(60,60);

                    over.addImage(image1);
                }



//            //画一个圈.
//            over.setRGBColorStroke(0xFF, 0x00, 0x00);
//            over.setLineWidth(5f);
//            over.ellipse(250, 450, 350, 550);
//            over.stroke();
            }
            stamper.close();
            conn.close();

            System.out.println(erweimapath);
            filepdf = new File(newpath);
            filepdf1 = new File(erweimapath);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "检验合格证.pdf");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            filepdf.delete();
            filepdf1.delete();
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }


        return download;
    }

}
