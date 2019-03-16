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

import static start.excel.excel.getUploadFileName;
import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getpretestreport {                             //气压（气液组合）试验过程卡报表
    @RequestMapping(value = "getpretestreport")
    public @ResponseBody ResponseEntity<byte[]> getpretestreport(String prodno, String name, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String dwgno = null;
        String type = null;                                 //容器类别
        String pttype = null;                               //试压类别
        String leaktest = null;                             //气密性试验方式
        String depress = null;                              //设计压力
        String wpress = null;                               //工作压力
        String wmedia = null;                               //工作介质
        String detemp = null;                               //设计温度
        String wtemp = null;                                //工作温度
        String testpress = null;                            //试验压力
        String leaktestp = null;                            //气密性试验压力
        String testmedia = null;                            //试压介质
        String prenotiform_user = null;                     //数据填写
        String prenotiform_username = null;                     //数据填写




        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"气压、气液混合.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "气压、气液混合_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
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
            testmedia = rs.getString("testmedia");
            prenotiform_username = rs.getString("user");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        if(rs.next()){
            type = rs.getString("type");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND name = ? AND status = 1");
        ps.setString(1,dwgno);
        ps.setString(2,name);
        rs = ps.executeQuery();
        if(rs.next()){
            pttype = rs.getString("pttype");
            leaktestp = rs.getString("leaktestp");
            depress = rs.getString("depress");
            wpress = rs.getString("wpress");
            wmedia = rs.getString("wmedia");
            detemp = rs.getString("detemp");
            wtemp = rs.getString("wtemp");
            testpress = rs.getString("testpress");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
        ps.setString(1,prenotiform_username);
        rs = ps.executeQuery();
        if(rs.next()){
            prenotiform_user = rs.getString("name");
        }
        rs.close();
        ps.close();

        putsheet(sheet,2,1,prodno);
        putsheet(sheet,2,3,type);
        putsheet(sheet,2,8,pttype);
        putsheet(sheet,3,8,leaktest);
        putsheet(sheet,4,1,depress);
        putsheet(sheet,4,3,wpress);
        putsheet(sheet,4,8,wmedia);
        putsheet(sheet,5,1,detemp);
        putsheet(sheet,5,3,wtemp);
        putsheet(sheet,6,1,testpress);
        putsheet(sheet,7,1,leaktestp);
        putsheet(sheet,6,3,testmedia);
        putsheet(sheet,10,1,prenotiform_user);


        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();

        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "气压、气液混合.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
