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

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getmepretestreport {                                                       //力学性能试验报告
    @RequestMapping(value = "getmepretestreport")
    public @ResponseBody ResponseEntity<byte[]> getmepretestreport(String prodno,String department,String indate,HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        int i = 0;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"力学性能试验报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "力学性能试验报告_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,2,1,department);
        putsheet(sheet,2,7,indate);

        ps = conn.prepareStatement("SELECT * FROM productplate WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        while (rs.next()){
            putsheet(sheet,2,17,rs.getString("testno"));

            putsheet(sheet,8+i,0,rs.getString("specimenno"));
            putsheet(sheet,8+i,3,rs.getString("f02mpa"));             //屈服强度RP0.2(Mpa)
            putsheet(sheet,8+i,4,rs.getString("f1mpa"));             //屈服强度RP1.0(Mpa)
            putsheet(sheet,8+i,5,rs.getString("f1mpa"));             //屈服强度RP1.0(Mpa)
            putsheet(sheet,8+i,7,rs.getString("fractposit"));             //抗拉断裂位置
            putsheet(sheet,8+i,9,rs.getString("bendatype"));             //弯曲类型
            putsheet(sheet,8+i,10,rs.getString("bendangle"));             //弯曲角度（°）
            putsheet(sheet,8+i,11,rs.getString("bendaxdia"));             //弯曲直径

            i++;
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        while (rs.next()){
            putsheet(sheet,4,1,rs.getString("specimenname"));
            putsheet(sheet,4,7,rs.getString("designation"));
            putsheet(sheet,4,10,rs.getString("spec"));
            putsheet(sheet,4,14,rs.getString("drawingnumber"));
            putsheet(sheet,4,17,rs.getString("weldzoneshocknum"));
            putsheet(sheet,4,18,rs.getString("thermalimpactzonenum"));


            i++;
        }
        rs.close();
        ps.close();








        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "力学性能试验报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }





    @RequestMapping(value = "getmepretestreportre")                             //力学性能试验报告复验
    public @ResponseBody ResponseEntity<byte[]> getmepretestreportre(String department,String codedmarking,String name,String testno,HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"力学性能试验报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "力学性能试验报告_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,2,1,department);

        ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,2,7,rs.getString("indate"));
            putsheet(sheet,2,17,testno);
            putsheet(sheet,4,1,name);
            putsheet(sheet,4,7,rs.getString("designation"));
            putsheet(sheet,4,10,rs.getString("spec"));
            putsheet(sheet,4,14,rs.getString("drawingnumber"));
            putsheet(sheet,4,17,rs.getString("weldzoneshocknum"));
            putsheet(sheet,4,18,rs.getString("thermalimpactzonenum"));
        }
        rs.close();
        ps.close();






        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "力学性能试验报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }

}
