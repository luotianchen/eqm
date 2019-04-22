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
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getspacetestreport {                                       //真空检测报告报表
    @RequestMapping(value = "getspacetestreport")
    public @ResponseBody ResponseEntity<byte[]> getspacetestreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy/MM/dd");

        String dwgno = null;
        int name_id = 0;
        String prodname = null;
        String ename = null;
        String htcurrent = null;                            //规管加热电流
        String leakoutrate = null;                          //真空夹层漏放气速率
        String leakoutrate_x[] = new String[2];
        String initdate = null;                             //检测开始日期
        String enddate = null;                              //检测结束日期
        String testtemp = null;                             //测漏放气率温度
        String sealtemp = null;                             //封口温度
        String sealvacu = null;                             //封口真空度
        String sealdate = null;                             //封口日期
        String volume_s = null;                               //第一个通道,容器容积
        double volume_i = 0;                               //第一个通道,容器容积
        String deconame = null;                             //制造单位名称
        String edeconame = null;
        String vacuop = null;                               //试验者

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"真空检测报告.xlsx");
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

        ps = conn.prepareStatement("SELECT * FROM vacuumparameters WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            htcurrent = rs.getString("htcurrent");
            leakoutrate = rs.getString("leakoutrate");
            leakoutrate_x = leakoutrate.split("e");
            calendar.setTime(rs.getDate("initdate"));
            initdate = simpleDateFormat1.format(calendar.getTime());
            calendar.setTime(rs.getDate("enddate"));
            enddate = simpleDateFormat1.format(calendar.getTime());
            testtemp = rs.getString("testtemp");
            sealtemp = rs.getString("sealtemp");
            sealvacu = rs.getString("sealvacu");
            calendar.setTime(rs.getDate("sealdate"));
            sealdate = simpleDateFormat1.format(calendar.getTime());
            vacuop = rs.getString("vacuop");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        if(rs.next()){
            System.out.println(1);
            volume_i = rs.getDouble("volume");
            volume_s = rs.getString("volume");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
        rs = ps.executeQuery();
        if(rs.next()){
            deconame = rs.getString("deconame");
            edeconame = rs.getString("edeconame");
        }
        rs.close();
        ps.close();

        putsheet(sheet,0,0,deconame);
        putsheet(sheet,1,0,edeconame);
        putsheet(sheet,4,2,dwgno);
        putsheet(sheet,4,9,prodname);
        putsheet(sheet,5,9,ename);
        putsheet(sheet,4,19,prodno);
        putsheet(sheet,8,14,htcurrent);
        if(leakoutrate_x[0]!=null){
            putsheet(sheet,12,4,leakoutrate_x[0]+"×10");
            putsheet(sheet,12,7,leakoutrate_x[1]);
        }
        putsheet(sheet,12,19,initdate);
        putsheet(sheet,13,19,enddate);
        putsheet(sheet,14,7,testtemp);
        putsheet(sheet,16,7,sealtemp);
        putsheet(sheet,14,20,volume_s);
        putsheet(sheet,18,3,sealvacu);
        putsheet(sheet,18,22,sealdate);
        putsheet(sheet,22,18,sealdate);
        putsheet(sheet,24,21,sealdate);
        putsheet(sheet,22,1,vacuop);
        System.out.println(volume_s);

        if(volume_i>1 && volume_i<=10){
            putsheet(sheet,12,10,"≤2×10");
            putsheet(sheet,12,14,"-5");
        }
        if(volume_i>10 && volume_i<=100){
            putsheet(sheet,12,10,"≤6×10");
            putsheet(sheet,12,14,"-5");
        }
        if(volume_i>100 && volume_i<=500){
            putsheet(sheet,12,10,"≤2×10");
            putsheet(sheet,12,14,"-4");
        }

        if(volume_i>1 && volume_i<=10){
            putsheet(sheet,18,12,"≤");
            putsheet(sheet,18,14,"2");
        }
        if(volume_i>10 && volume_i<=50){
            putsheet(sheet,18,12,"≤");
            putsheet(sheet,18,14,"3");
        }
        if(volume_i>50 && volume_i<=100){
            putsheet(sheet,18,12,"≤");
            putsheet(sheet,18,14,"5");
        }
        if(volume_i>100 && volume_i<=500){
            putsheet(sheet,18,12,"≤");
            putsheet(sheet,18,14,"8");
        }






        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();

        conn.close();

        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "真空检测报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
