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
import org.springframework.web.bind.annotation.RequestBody;
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
import java.util.Locale;
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getweldingreport {                                         //产品焊接记录报表
    @RequestMapping(value = "getweldingreport")
    public @ResponseBody ResponseEntity<byte[]> getweldingreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        int i = 0;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM.yyyy", Locale.US);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MMM.dd.yyyy", Locale.US);

        ResponseEntity<byte[]> download = null;
        File file = null;
        File filepdf = null;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"焊接记录.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        file = new File(uploadPath, filename);

        try {

            FileUtils.copyInputStreamToFile(inputStream, file);
            String url1 = uploadPath +"/"+ filename;


            FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
            XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
            fileXlsx.close();
            Sheet sheet=workBook.getSheetAt(0);


            putsheet(sheet,3,42,prodno);
            putsheet(sheet,3+61,42,prodno);
            ps = conn.prepareStatement("SELECT * FROM weldingrecord WHERE prodno = ?");
            ps.setString(1,prodno);
            rs= ps.executeQuery();
            while (rs.next()){
                if(i<25){
                    putsheet(sheet,3,0,rs.getString("dwgno"));
                    putsheet(sheet,9+i*2,3,rs.getString("weldno"));
                    if(rs.getString("weldno")!=null){
                        putsheet(sheet,9+i*2,0,rs.getString("weldno").substring(0,1));
                    }
                    putsheet(sheet,9+i*2,13,rs.getString("weldevano"));
                    putsheet(sheet,9+i*2,23,rs.getString("weldmethod"));
                    putsheet(sheet,9+i*2,30,rs.getString("usernote"));

                    calendar.setTime(rs.getDate("welddate"));
                    putsheet(sheet,9+i*2+61-25*2,38,simpleDateFormat1.format(calendar.getTime()));
                    putsheet(sheet,59+61,39,simpleDateFormat3.format(calendar.getTime()));
                    putsheet(sheet,60+61,39,simpleDateFormat4.format(calendar.getTime()));

                    putsheet(sheet,9+i*2,46,rs.getString("inspector"));
                    i++;

                }else {
                    putsheet(sheet,3+61,0,rs.getString("dwgno"));
                    putsheet(sheet,9+i*2+61-25*2,3,rs.getString("weldno"));
                    if(rs.getString("weldno")!=null){
                        putsheet(sheet,9+i*2+61-25*2,0,rs.getString("weldno").substring(0,1));
                    }
                    putsheet(sheet,9+i*2+61-25*2,13,rs.getString("weldevano"));
                    putsheet(sheet,9+i*2+61-25*2,23,rs.getString("weldmethod"));
                    putsheet(sheet,9+i*2+61-25*2,30,rs.getString("usernote"));

                    calendar.setTime(rs.getDate("welddate"));
                    putsheet(sheet,9+i*2+61-25*2,38,simpleDateFormat1.format(calendar.getTime()));
                    putsheet(sheet,59+61,39,simpleDateFormat3.format(calendar.getTime()));
                    putsheet(sheet,60+61,39,simpleDateFormat4.format(calendar.getTime()));

                    putsheet(sheet,9+i*2+61-25*2,46,rs.getString("inspector"));
                    i++;

                }
            }
            rs.close();
            ps.close();




            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();

            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "焊接记录.xlsx");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            file.delete();
            filepdf.delete();
        }catch (Exception e){
            file.delete();
            System.out.println(e);
        }
        return download;
    }
}
