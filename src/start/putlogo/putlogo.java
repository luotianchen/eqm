package start.putlogo;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@CrossOrigin
public class putlogo {
    @RequestMapping(value = "putlogo")
    public @ResponseBody putlogoresult putlogo(@RequestParam(value="logo") MultipartFile multipartFile, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putlogoresult result = new putlogoresult();

        try {
            String realPath = request.getSession().getServletContext().getRealPath("");
            InputStream inputStream = multipartFile.getInputStream();                           //服务器根目录的路径
            String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
            String uploadPath = path + "upload";                                                //获取文件名称
            String filename = getUploadFileName(multipartFile);                                 //将文件上传的服务器根目录下的upload文件夹
            File file = new File(uploadPath, filename);
            FileUtils.copyInputStreamToFile(inputStream, file);
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/eqm/upload/" + filename;

            ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
            rs = ps.executeQuery();
            if(!rs.next()){
                ps = conn.prepareStatement("INSERT INTO email(logo,id) VALUES (?,?)");
                ps.setString(1,url);
                ps.setInt(2,1);
                ps.executeUpdate();
                ps.close();
            }else {
                ps = conn.prepareStatement("UPDATE email SET logo = ? WHERE id = 1");
                ps.setString(1,url);
                ps.executeUpdate();
                ps.close();
            }



            result.setResult("success");

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }


    private String getUploadFileName(MultipartFile multipartFile) {
        String uploadFileName = multipartFile.getOriginalFilename();
        String fileName = uploadFileName.substring(0,
                uploadFileName.lastIndexOf("."));
        String type = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String name = fileName + "_" + timeStr + type;
        return name;
    }
}
