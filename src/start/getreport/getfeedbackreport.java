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
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getfeedbackreport {                                    //用户质量信息反馈单
    @RequestMapping(value = "getfeedbackreport")
    public  @ResponseBody ResponseEntity<byte[]> getfeedbackreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String dwgno = null;
        int name_id = 0;
        String prodname = null;
        String ename = null;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"用户质量信息反馈单.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


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

        putsheet(sheet,5,1,prodname);
        putsheet(sheet,6,1,ename);
        putsheet(sheet,5,3,prodno);




        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();

        conn.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "用户质量信息反馈单.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
