package start.getreport;

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
public class getcleanlinessreport {                                     //清洁度检查报告报表
    @RequestMapping(value = "getcleanlinessreport")
    public @ResponseBody ResponseEntity<byte[]> getcleanlinessreport(String prodno,String name,String username,String cleanname,String date, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");

        String clean_1 = "化学 Chemical";
        String clean_2 = "擦洗 Scrubbing";
        String clean_3 = "高压水 H-pressure water";
        String clean_4 = "浸泡 Soaking";
        String inspection_1 = "白光直接目测 White Light";
        String inspection_2 = "紫外线（黑光灯）Ultraviolet Radiation";
        String inspection_3 = "擦拭试验 Wipe Testing";
        String inspection_4 = "溶剂萃取 Solvent leaching";
        String inspection_5 = "蓝点法  Blue Point Inspection Act";
        String quain_1 = "目视检查无杂质、无有机物、无水";
        String quain_2 = "No Slag , organic matters, water after visual inspection";
        String quain_3 = "用滤纸擦拭，在黑光灯下检查无蓝白或黄绿荧光显示，无油，脂或纤维污染";
        String quain_4 = "No appearance of blue-whiter or yellow-green fluorescence grease or fiber";
        String quain_5 = "油脂、洗涤剂、有机涂层萃取后含量<200mg/m2";
        String quain_6 = "The content of grease, scour ,organic coat after leach<200mg/m2";


        String dwgno = null;
        String wmedia = null;
        String ewmedia = null;
        String shmatl1 = null;
        String shmatl2 = null;
        String shmatl3 = null;
        String shmatl = null;
        String dated = null;


        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"清洁度检查报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,3,8,prodno);

        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            dwgno = rs.getString("dwgno");
            putsheet(sheet,3,0,dwgno);
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND name = ? AND status = 1");
        ps.setString(1,dwgno);
        ps.setString(2,name);
        rs = ps.executeQuery();
        if(rs.next()){
            wmedia = rs.getString("wmedia");
            shmatl1 = rs.getString("shmatl1");
            shmatl2 = rs.getString("shmatl2");
            shmatl3 = rs.getString("shmatl3");
            if(shmatl1 != null && !shmatl1.equals("")){
                shmatl = shmatl1;
            }

            if(shmatl2 != null && !shmatl2.equals("") && !shmatl2.equals(shmatl1)){
                shmatl = shmatl + "/" + shmatl2;
            }

            if(shmatl3 != null && !shmatl3.equals("") && !shmatl3.equals(shmatl1) && !shmatl3.equals(shmatl2)){
                shmatl = shmatl + "/" + shmatl3;
            }

            putsheet(sheet,4,2,shmatl);
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM wmedia WHERE wmedia = ?");
        ps.setString(1,wmedia);
        rs = ps.executeQuery();
        if(rs.next()){
            ewmedia = rs.getString("wmediaen");
        }
        rs.close();
        ps.close();

        putsheet(sheet,4,7,wmedia);
        putsheet(sheet,5,7,ewmedia);

        ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
        ps.setString(1,username);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,37,1,rs.getString("name"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            if(rs.getString("dated3")==null || rs.getString("dated3").equals("")){
                if(rs.getString("dated2")==null || rs.getString("dated2").equals("")){
                    if(!(rs.getString("dated1")==null || rs.getString("dated1").equals(""))){
                        calendar.setTime(rs.getDate("dated1"));
                        calendar.add(calendar.DATE, 1);
                        dated = simpleDateFormat1.format(calendar.getTime());
                    }
                }else {
                    calendar.setTime(rs.getDate("dated2"));
                    calendar.add(calendar.DATE, 1);
                    dated = simpleDateFormat1.format(calendar.getTime());
                }
            }else {
                calendar.setTime(rs.getDate("dated3"));
                calendar.add(calendar.DATE, 1);
                dated = simpleDateFormat1.format(calendar.getTime());
            }

        }
        rs.close();
        ps.close();

        putsheet(sheet,8,4,date);
        putsheet(sheet,9,4,dated);
        putsheet(sheet,10,4,dated);



        if(cleanname.equals("普通")){
            putsheet(sheet,7,6,clean_2);

            putsheet(sheet,12,6,inspection_1);

            putsheet(sheet,17,0,quain_1);
            putsheet(sheet,18,0,quain_2);
        }

        if(cleanname.equals("去油脱脂")){
            putsheet(sheet,7,6,clean_1);
            putsheet(sheet,8,6,clean_2);

            putsheet(sheet,12,6,inspection_1);
            putsheet(sheet,13,6,inspection_2);


            putsheet(sheet,17,0,quain_1);
            putsheet(sheet,18,0,quain_2);
            putsheet(sheet,19,0,quain_3);
            putsheet(sheet,20,0,quain_4);
        }

        if(cleanname.equals("酸洗钝化")){
            putsheet(sheet,7,6,clean_1);
            putsheet(sheet,8,6,clean_2);
            putsheet(sheet,9,6,clean_3);


            putsheet(sheet,12,6,inspection_1);
            putsheet(sheet,13,6,inspection_2);
            putsheet(sheet,14,6,inspection_3);


            putsheet(sheet,17,0,quain_1);
            putsheet(sheet,18,0,quain_2);
            putsheet(sheet,19,0,quain_3);
            putsheet(sheet,20,0,quain_4);
        }

        if(cleanname.equals("特殊要求")){
            putsheet(sheet,7,6,clean_1);
            putsheet(sheet,8,6,clean_2);
            putsheet(sheet,9,6,clean_3);
            putsheet(sheet,10,6,clean_4);

            putsheet(sheet,12,6,inspection_1);
            putsheet(sheet,13,6,inspection_2);
            putsheet(sheet,14,6,inspection_3);
            putsheet(sheet,15,6,inspection_4);

            putsheet(sheet,17,0,quain_1);
            putsheet(sheet,18,0,quain_2);
            putsheet(sheet,19,0,quain_3);
            putsheet(sheet,20,0,quain_4);
            putsheet(sheet,21,0,quain_5);
            putsheet(sheet,22,0,quain_6);
        }

        if(cleanname.equals("蓝点法要求")){
            putsheet(sheet,7,6,clean_1);
            putsheet(sheet,8,6,clean_2);
            putsheet(sheet,9,6,clean_3);


            putsheet(sheet,12,6,inspection_1);
            putsheet(sheet,13,6,inspection_2);
            putsheet(sheet,14,6,inspection_3);
            putsheet(sheet,15,6,inspection_5);

            putsheet(sheet,17,0,quain_1);
            putsheet(sheet,18,0,quain_2);
            putsheet(sheet,19,0,quain_3);
            putsheet(sheet,20,0,quain_4);
            putsheet(sheet,21,0,quain_5);
            putsheet(sheet,22,0,quain_6);
        }










        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "清洁度检查报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
