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
import java.util.Locale;
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getpromanchangereport {                                //产品制造变更报告
    @RequestMapping(value = "getpromanchangereport")
    public @ResponseBody ResponseEntity<byte[]> getpromanchangereport(String prodno, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy年MM月");
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MMM.yyyy", Locale.US);


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

            File realfile = new File(uploadPath,"产品制造变更报告.xlsx");
            InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径





            FileUtils.copyInputStreamToFile(inputStream, file);
            String url1 = uploadPath +"/"+ filename;


            FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
            XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
            fileXlsx.close();
            Sheet sheet=workBook.getSheetAt(0);

            putsheet(sheet,4,45,prodno);
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
            ps.setString(1,prodno);
            rs = ps.executeQuery();
            if(rs.next()){
                putsheet(sheet,4,8,rs.getString("dwgno"));
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT MAX(date) as date FROM pretest WHERE prodno = ?");
            ps.setString(1,prodno);
            rs = ps.executeQuery();
            if(rs.next()){
                ps1 = conn.prepareStatement("SELECT MAX(date) as date FROM leakagetest WHERE prodno = ?");
                ps1.setString(1,prodno);
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    if(rs.getDate("date").before(rs1.getDate("date"))){
                        calendar.setTime(rs1.getDate("date"));
                    }else {
                        calendar.setTime(rs.getDate("date"));
                    }

                    putsheet(sheet,56,46,simpleDateFormat3.format(calendar.getTime()));
                    putsheet(sheet,57,46,simpleDateFormat4.format(calendar.getTime()));


                }
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();






            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();

            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "产品制造变更报告.xlsx");// 文件的属性，也就是文件叫什么吧
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
