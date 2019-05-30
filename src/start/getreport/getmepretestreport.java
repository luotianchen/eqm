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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getmepretestreport {                                                       //力学性能试验报告产品试板
    @RequestMapping(value = "getmepretestreport")
    public @ResponseBody ResponseEntity<byte[]> getmepretestreport(String prodno,String department,String indate,String specimenno,HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        int i = 0;
        int surfacebending=0;
        int backbending=0;
        int lateralbending=0;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");

        ResponseEntity<byte[]> download = null;
        File file = null;
        File filepdf = null;
        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        file = new File(uploadPath, filename);
        try {

            File realfile = new File(uploadPath,"力学性能试验报告.xlsx");
            InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径





            FileUtils.copyInputStreamToFile(inputStream, file);
            String url1 = uploadPath +"/"+ filename;


            FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
            XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
            fileXlsx.close();
            Sheet sheet=workBook.getSheetAt(0);

            putsheet(sheet,2,1,department);
            putsheet(sheet,2,7,indate);

            ps = conn.prepareStatement("SELECT * FROM productplate WHERE prodno = ? AND status = 1 AND specimenno=?");
            ps.setString(1,prodno);
            ps.setString(2,specimenno);
            rs = ps.executeQuery();
            if (rs.next()){
                putsheet(sheet,2,17,rs.getString("testno").substring(0,4)+"-m"+rs.getString("testno").substring(4));//****-c**

                if(rs.getInt("hardness1")==0 && rs.getInt("hardness2")==0 && rs.getInt("hardness3")==0){
                    putsheet(sheet,5,20,"/");
                }else {
                    putsheet(sheet,5,20,"1");
                }

                putsheet(sheet,8,0,specimenno);
                putsheet(sheet,8,2,rs.getString("so"));             //截面积
                putsheet(sheet,8,3,rs.getString("f02mpa"));             //屈服强度RP0.2(Mpa)
                putsheet(sheet,8,4,rs.getString("f1mpa"));             //屈服强度RP1.0(Mpa)
                putsheet(sheet,8,5,rs.getString("rm"));             //抗拉强度
                putsheet(sheet,8,6,rs.getString("a"));             //延伸率%
                putsheet(sheet,8,7,rs.getString("fractposit"));             //抗拉断裂位置
                putsheet(sheet,8,9,rs.getString("bendatype"));             //弯曲类型
                putsheet(sheet,8,10,rs.getString("bendangle"));             //弯曲角度（°）
                putsheet(sheet,8,11,rs.getString("bendaxdia"));             //弯曲直径

                if((rs.getString("bendatype")!=null) ||
                        (rs.getString("bendangle")!=null) ||
                        (rs.getString("bendaxdia")!=null)){
                    putsheet(sheet,8,13,"合格");
                    putsheet(sheet,8,8,"1");
                }

                putsheet(sheet,8,15,rs.getString("shocktemp"));             //试验温度
                putsheet(sheet,8,16,rs.getString("gaptype"));             //缺口类型

                if(rs.getString("w1")!=null && !rs.getString("w1").equals("")){
                    putsheet(sheet,8+i,17,"焊缝区");
                    putsheet(sheet,8+i,18,rs.getString("w1"));
                    putsheet(sheet,8+i,19,rs.getString("lew1"));
                    i++;
                }
                if(rs.getString("w2")!=null && !rs.getString("w2").equals("")){
                    putsheet(sheet,8+i,17,"焊缝区");
                    putsheet(sheet,8+i,18,rs.getString("w2"));
                    putsheet(sheet,8+i,19,rs.getString("lew2"));
                    i++;
                }
                if(rs.getString("w3")!=null && !rs.getString("w3").equals("")){
                    putsheet(sheet,8+i,17,"焊缝区");
                    putsheet(sheet,8+i,18,rs.getString("w3"));
                    putsheet(sheet,8+i,19,rs.getString("lew3"));
                    i++;
                }
                if(rs.getString("h1")!=null && !rs.getString("h1").equals("")){
                    putsheet(sheet,8+i,17,"热处理区");
                    putsheet(sheet,8+i,18,rs.getString("h1"));
                    putsheet(sheet,8+i,19,rs.getString("leh1"));
                    i++;
                }
                if(rs.getString("h2")!=null && !rs.getString("h2").equals("")){
                    putsheet(sheet,8+i,17,"热处理区");
                    putsheet(sheet,8+i,18,rs.getString("h2"));
                    putsheet(sheet,8+i,19,rs.getString("leh2"));
                    i++;
                }
                if(rs.getString("h3")!=null && !rs.getString("h3").equals("")){
                    putsheet(sheet,8+i,17,"热处理区");
                    putsheet(sheet,8+i,18,rs.getString("h3"));
                    putsheet(sheet,8+i,19,rs.getString("leh3"));
                    i++;
                }

                putsheet(sheet,8,20,rs.getString("hardness1"));
                putsheet(sheet,9,20,rs.getString("hardness2"));
                putsheet(sheet,10,20,rs.getString("hardness3"));

                calendar.setTime(rs.getDate("testdate"));
                putsheet(sheet,21,17,simpleDateFormat1.format(calendar.getTime()));

            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE prodno = ? AND status = 1 AND specimenno=?");
            ps.setString(1,prodno);
            ps.setString(2,specimenno);
            rs = ps.executeQuery();
            while (rs.next()){
                putsheet(sheet,4,1,prodno + "--"+rs.getString("specimenname"));
                putsheet(sheet,4,7,rs.getString("designation"));
                putsheet(sheet,4,10,rs.getString("spec"));
                putsheet(sheet,5,14,rs.getString("drawingnumber"));

                if(!rs.getString("surfacebending").equals("/")){
                    surfacebending=rs.getInt("surfacebending");
                }
                if(!rs.getString("backbending").equals("/")){
                    backbending=rs.getInt("backbending");
                }
                if(!rs.getString("lateralbending").equals("/")){
                    lateralbending=rs.getInt("lateralbending");
                }


                putsheet(sheet,5,16,String.valueOf(surfacebending+backbending+lateralbending));
                putsheet(sheet,5,17,rs.getString("weldzoneshocknum"));
                putsheet(sheet,5,18,rs.getString("thermalimpactzonenum"));

                i++;
            }
            rs.close();
            ps.close();








            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();


            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "力学性能试验报告.xlsx");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            file.delete();
            filepdf.delete();
        }catch (Exception e){
            file.delete();
        }

        return download;
    }

}
