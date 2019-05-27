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
public class getprenotiformreport {                                         //压力试验通知单报表
    @RequestMapping(value = "getprenotiformreport")
    public @ResponseBody ResponseEntity<byte[]> getprenotiformreport(String prodno, String name, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String dwgno = null;
        String pttype = null;                       //试压类别
        String prodname = null;
        int name_id = 0;
        String depress = null;                      //设计压力
        String detemp = null;                       //设计温度
        String wmedia = null;                       //工作介质
        String testpress = null;                    //试验压力
        String leaktestp = null;                    //气密性试验压力
        String dated = null;                        //开具日期

        int ppart_id = 0;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");

        ResponseEntity<byte[]> download = null;
        File file = null;
        File filepdf = null;

        try {
            String realPath = request.getSession().getServletContext().getRealPath("");
            String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
            String uploadPath = path + "upload";                                                //获取文件名称
            System.out.println(uploadPath);
            File realfile = new File(uploadPath,"压力试验通知单.xlsx");
            InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

            String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
            file = new File(uploadPath, filename);



            FileUtils.copyInputStreamToFile(inputStream, file);
            String url1 = uploadPath +"/"+ filename;


            FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
            XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
            fileXlsx.close();
            Sheet sheet=workBook.getSheetAt(0);

            ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ?");
            ps.setString(1,name);
            rs = ps.executeQuery();
            if(rs.next()){
                ppart_id = rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ? AND presstestp_id_ppart1=?");
            ps.setString(1,prodno);
            ps.setInt(2,ppart_id);
            rs = ps.executeQuery();
            if(rs.next()){
                dwgno = rs.getString("dwgno");
                if(rs.getString("dated3")==null || rs.getString("dated3").equals("")){
                    if(rs.getString("dated2")==null || rs.getString("dated2").equals("")){
                        if(!(rs.getString("dated1")==null || rs.getString("dated1").equals(""))){
                            calendar.setTime(rs.getDate("dated1"));
                            dated = simpleDateFormat1.format(calendar.getTime());
                        }
                    }else {
                        calendar.setTime(rs.getDate("dated2"));
                        dated = simpleDateFormat1.format(calendar.getTime());
                    }
                }else {
                    calendar.setTime(rs.getDate("dated3"));
                    dated = simpleDateFormat1.format(calendar.getTime());
                }

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
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND name = ? AND status = 1");
            ps.setString(1,dwgno);
            ps.setString(2,name);
            rs = ps.executeQuery();
            if(rs.next()){
                pttype = rs.getString("pttype");                       //试压类别
                depress = rs.getString("depress");                      //设计压力
                detemp = rs.getString("detemp");                       //设计温度
                wmedia = rs.getString("wmedia");                       //工作介质
                testpress = rs.getString("testpress");                    //试验压力
                leaktestp = rs.getString("leaktestp");                     //气密性试验方式
            }
            rs.close();
            ps.close();

            putsheet(sheet,2,4,prodno);
            putsheet(sheet,2,10,dwgno);
            putsheet(sheet,3,4,pttype);
            putsheet(sheet,3,4,pttype);
            putsheet(sheet,4,4,prodname);
            putsheet(sheet,4,10,name);
            putsheet(sheet,6,3,depress);
            putsheet(sheet,6,5,detemp);
            putsheet(sheet,6,8,wmedia);
            putsheet(sheet,6,12,testpress);
            putsheet(sheet,7,12,leaktestp);
            putsheet(sheet,21,9,dated);
            putsheet(sheet,23,9,dated);


            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();

            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "压力试验通知单.xlsx");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            file.delete();
            filepdf.delete();
        }catch (Exception e){
            file.delete();
            filepdf.delete();
        }

        return download;
    }
}
