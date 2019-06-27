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

import static start.excel.excel.excel2Pdf;
import static start.excel.excel.getUploadFileName;
import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getcover {                                                 //获取封面报表
    @RequestMapping(value = "getcover")
    public @ResponseBody ResponseEntity<byte[]> getcover(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String dwgno = null;
        String prodname = null;
        String ename = null;
        String date = null;         //完工日期
        int name_id = 0;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月");

        ResponseEntity<byte[]> download = null;
        File file = null;
        File filepdf = null;
        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);


        String filename =UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        file = new File(uploadPath, filename);
//        try {
            File realfile = new File(uploadPath,"封面.xlsx");
            InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径



            FileUtils.copyInputStreamToFile(inputStream, file);
            String pdfname = getUploadFileName("封面.pdf");
            String url1 = uploadPath +"/"+ filename;
            String url2 = uploadPath +"/"+ pdfname;
            System.out.println(pdfname);
            System.out.println(url1);
            System.out.println(url2);



            FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
            XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
            fileXlsx.close();
            Sheet sheet=workBook.getSheetAt(0);

            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
            ps.setString(1,prodno);
            rs = ps.executeQuery();
            if(rs.next()){
                dwgno = rs.getString("dwgno");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps.setString(1,dwgno);
            rs = ps.executeQuery();
            if(rs.next()){
                name_id = rs.getInt("productname_id_prodname");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
            ps.setInt(1,name_id);
            rs = ps.executeQuery();
            if(rs.next()){
                prodname = rs.getString("prodname");
                ename = rs.getString("ename");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status=1");
            ps.setString(1,prodno);
            rs = ps.executeQuery();
            if(rs.next()){
                calendar.setTime(rs.getDate("exworkdate"));
                date = simpleDateFormat1.format(calendar.getTime());
            }
            rs.close();
            ps.close();

            putsheet(sheet,6,1,prodname);
            putsheet(sheet,7,1,ename);
            putsheet(sheet,11,1,prodno);
            putsheet(sheet,36,1,dwgno);
            putsheet(sheet,38,1,prodname);
            putsheet(sheet,40,1,prodno);
            putsheet(sheet,42,1,date);
            putsheet(sheet,64,1,dwgno);
            putsheet(sheet,66,1,prodname);
            putsheet(sheet,68,1,prodno);
            putsheet(sheet,70,1,date);
            putsheet(sheet,96,1,prodname);
            putsheet(sheet,97,1,ename);
            putsheet(sheet,101,1,prodno);



            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();

            excel2Pdf(url1,url2);                                       //转PDF
            filepdf = new File(uploadPath, pdfname);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "封面.pdf");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            file.delete();
            filepdf.delete();
//        }catch (Exception e){
//            file.delete();
//        }

        return download;

    }
}
