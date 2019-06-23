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
public class getprecertificatereport {                              //压力容器产品合格证报表
    @RequestMapping(value = "getprecertificatereport")
    public @ResponseBody ResponseEntity<byte[]> getprecertificatereport(String prodno, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String z_deconame = null;                 //制造单位
        String z_edeconame = null;                //制造单位英文
        String z_orgcode = null;                //制造单位统一社会信用代码
        String z_delicense = null;                //制造许可证编号
        String manulevel = null;                //制造许可级别
        String dwgno = null;
        String prodname = null;
        String ename = null;
        int prodname_id = 0;
        String ecode = null;                    //设备代码*
        String type = null;                     //压力容器类别*
        String s_deconame = null;               //设计单位*
        String s_edeconame = null;               //设计单位英文
        String s_orgcode = null;                //设计单位统一社会信用代码
        String s_delicense = null;              //设计许可证编号
        String designdate = null;               //设计日期*
        String designdate_x = null;               //设计日期*
        String exworkdate = null;               //制造日期*
        String exworkdate_x = null;               //制造日期*
        String x_exworkdate = null;               //设计日期*
        String x_exworkdate_x = null;               //设计日期*




        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月");
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

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        file = new File(uploadPath, filename);
        try {

            File realfile = new File(uploadPath,"压力容器产品合格证.xlsx");
            InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径





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

            ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                z_deconame = rs.getString("deconame");
                z_edeconame = rs.getString("edeconame");
                z_orgcode = rs.getString("orgcode");
                z_delicense = rs.getString("delicense");
                manulevel = rs.getString("manulevel");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps.setString(1,dwgno);
            rs = ps.executeQuery();
            if(rs.next()){
                prodname_id = rs.getInt("productname_id_prodname");
                type = rs.getString("type");
                calendar.setTime(rs.getDate("designdate"));
                designdate = simpleDateFormat3.format(calendar.getTime());
                designdate_x = simpleDateFormat4.format(calendar.getTime());
                s_deconame = rs.getString("deconame");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
            ps.setInt(1,prodname_id);
            rs = ps.executeQuery();
            if(rs.next()){
                prodname = rs.getString("prodname");
                ename = rs.getString("ename");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
            ps.setString(1,prodno);
            rs = ps.executeQuery();
            if(rs.next()){
                ecode = rs.getString("ecode");
                calendar.setTime(rs.getDate("exworkdate"));
                exworkdate = simpleDateFormat1.format(calendar.getTime());
                exworkdate_x = simpleDateFormat2.format(calendar.getTime());
                x_exworkdate = simpleDateFormat1.format(calendar.getTime());
                x_exworkdate_x = simpleDateFormat2.format(calendar.getTime());

            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM designunit WHERE deconame = ?");
            ps.setString(1,s_deconame);
            rs = ps.executeQuery();
            if(rs.next()){
                s_edeconame = rs.getString("edeconame");
                s_delicense = rs.getString("delicense");
                s_orgcode = rs.getString("orgcode");
            }
            rs.close();
            ps.close();

            putsheet(sheet,4,1,z_deconame);
            putsheet(sheet,5,1,z_edeconame);
            putsheet(sheet,6,1,z_orgcode);
            putsheet(sheet,6,3,z_delicense);
            putsheet(sheet,8,1,prodname);
            putsheet(sheet,9,1,ename);
            putsheet(sheet,8,3,manulevel);
            putsheet(sheet,11,1,prodno);
            putsheet(sheet,11,3,ecode);
            putsheet(sheet,13,1,dwgno);
            putsheet(sheet,13,3,type);
            putsheet(sheet,15,1, s_deconame != null ? s_deconame.replaceAll("\\d", "") : null);
            putsheet(sheet,16,1,s_edeconame);
            putsheet(sheet,17,1,s_orgcode);
            putsheet(sheet,17,3,s_delicense);
            putsheet(sheet,19,1,designdate);
            putsheet(sheet,20,1,designdate_x);
            putsheet(sheet,19,3,exworkdate);
            putsheet(sheet,20,3,exworkdate_x);
            putsheet(sheet,29,3,x_exworkdate);
            putsheet(sheet,30,3,x_exworkdate_x);
            putsheet(sheet,36,3,x_exworkdate);
            putsheet(sheet,37,3,x_exworkdate_x);
            putsheet(sheet,41,3,x_exworkdate);
            putsheet(sheet,42,3,x_exworkdate_x);

            putsheet(sheet,54,1,z_deconame);
            putsheet(sheet,55,1,z_edeconame);
            putsheet(sheet,56,1,z_orgcode);
            putsheet(sheet,56,3,z_delicense);
            putsheet(sheet,58,1,prodname);
            putsheet(sheet,59,1,ename);
            putsheet(sheet,58,3,manulevel);
            putsheet(sheet,61,1,prodno);
            putsheet(sheet,61,3,ecode);
            putsheet(sheet,63,1,dwgno);
            putsheet(sheet,65,1, s_deconame != null ? s_deconame.replaceAll("\\d", "") : null);
            putsheet(sheet,66,1,s_edeconame);
            putsheet(sheet,67,1,s_orgcode);
            putsheet(sheet,67,3,s_delicense);
            putsheet(sheet,69,1,designdate);
            putsheet(sheet,70,1,designdate_x);
            putsheet(sheet,69,3,exworkdate);
            putsheet(sheet,70,3,exworkdate_x);
            putsheet(sheet,79,3,x_exworkdate);
            putsheet(sheet,80,3,x_exworkdate_x);
            putsheet(sheet,86,3,x_exworkdate);
            putsheet(sheet,87,3,x_exworkdate_x);
            putsheet(sheet,91,3,x_exworkdate);
            putsheet(sheet,92,3,x_exworkdate_x);









            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();


            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "压力容器产品合格证.xlsx");// 文件的属性，也就是文件叫什么吧
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
