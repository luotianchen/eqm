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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class gettrackingreport {                                    //焊工/材料跟踪记录报表
    @RequestMapping(value = "gettrackingreport")
    public @ResponseBody ResponseEntity<byte[]> gettrackingreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;

        String dwgno= null;
        int prodname_id = 0;
        int parts_id = 0;
        int designation_id = 0;
        int i = 0;
        int z = 0;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM.yyyy", Locale.US);
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MMM.dd.yyyy", Locale.US);


        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"焊工材料跟踪记录.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "焊工材料跟踪记录_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,5,2,prodno);
        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            dwgno = rs.getString("dwgno");
            putsheet(sheet,5,5,rs.getString("dwgno"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        if(rs.next()){
            prodname_id = rs.getInt("productname_id_prodname");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
        ps.setInt(1,prodname_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,5,10,rs.getString("prodname"));
            putsheet(sheet,6,10,rs.getString("ename"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        while (rs.next()){
            parts_id = rs.getInt("parts_id_name");
            ps1 = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
            ps1.setInt(1,parts_id);
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                putsheet(sheet,9+i,9,rs1.getString("partsname"));
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
            ps1.setString(1,rs.getString("codedmarking"));
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                ps2 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                ps2.setInt(1,rs1.getInt("contraststand_id_designation"));
                rs2 = ps2.executeQuery();
                if(rs2.next()){
                    putsheet(sheet,9+i,10,rs2.getString("designation"));
                }
                rs2.close();
                ps2.close();
            }
            rs1.close();
            ps1.close();


            putsheet(sheet,9+i,11,rs.getString("codedmarking"));
            putsheet(sheet,9+i,13,rs.getString("spec"));
            i++;
        }
        rs.close();
        ps.close();


        ps = conn.prepareStatement("SELECT * FROM weldingrecord WHERE prodno = ?");
        ps.setString(1,prodno);
        rs= ps.executeQuery();
        while (rs.next()){
            if(z<7){
                putsheet(sheet,31+z,0,rs.getString("weldno"));
                putsheet(sheet,31+z,2,rs.getString("usernote"));
            }else if(z<14){
                putsheet(sheet,24+z,4,rs.getString("weldno"));
                putsheet(sheet,24+z,6,rs.getString("usernote"));
            }

            z++;
        }
        rs.close();
        ps.close();



        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "焊工材料跟踪记录.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;

    }
}