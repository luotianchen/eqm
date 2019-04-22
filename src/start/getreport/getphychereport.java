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
import java.util.UUID;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getphychereport {                                          //理化试验委托单报表
    @RequestMapping(value = "getphychereport")
    public @ResponseBody ResponseEntity<byte[]> getphychereport(String prodno , String specimenno, String date, String department, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = format.parse(date);
        java.sql.Date dated = new java.sql.Date(d.getTime());

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
        calendar.setTime(dated);

        String prodname = null;                                 //产品名称
        String judgestand = null;                               //评定标准*
        String prodno_specimenno = prodno + "-" + specimenno;   //试件编号
        String specimenname = null;                             //试件名称
        String weldingsteelseal = null;                         //焊工钢印
        String designation_spec = null;                         //材料牌号、规格(空格隔开)
        String groovetype = null;                               //坡口型式
        String weldingmethod_weldingposition = null;            //焊接方法及位置(空格隔开)
        String heatcondi = null;                                //热处理状态
        String representno = null;                              //代表产品批号
        String representpart = null;                            //代表部位
        String drawingnumber = null;                            //拉伸数量
        String surfacebending = null;                           //面弯
        String backbending = null;                              //背弯
        String lateralbending = null;                           //侧弯
        String shocktemperature = null;                         //冲击温度
        String weldzoneshocknum = null;                         //焊缝区冲击数量
        String thermalimpactzonenum = null;                     //热影响区冲击数量
        String gaptype = null;                                  //缺口型式*
        String matl=null;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"理化委托单.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        ps = conn.prepareStatement("SELECT * FROM productplate WHERE prodno = ? AND specimenno = ? AND status = 1");
        ps.setString(1,prodno);
        ps.setString(2,specimenno);
        rs= ps.executeQuery();
        if(rs.next()){
            judgestand = rs.getString("judgestand");
            gaptype = rs.getString("gaptype");
            matl = rs.getString("specimenmatl");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE prodno = ? AND specimenno = ? AND status = 1");
        ps.setString(1,prodno);
        ps.setString(2,specimenno);
        rs= ps.executeQuery();
        if(rs.next()){
            prodname = rs.getString("prodname");
            specimenname = rs.getString("specimenname");
            weldingsteelseal = rs.getString("weldingsteelseal");
            designation_spec = rs.getString("designation")+ " " + rs.getString("spec");
            groovetype = rs.getString("groovetype");
            weldingmethod_weldingposition = rs.getString("weldingmethod")+ " " + rs.getString("weldingposition");
            heatcondi = rs.getString("heatcondi");
            representno = rs.getString("representno");
            representpart = rs.getString("representpart");
            drawingnumber = rs.getString("drawingnumber");
            surfacebending = rs.getString("surfacebending");
            backbending = rs.getString("backbending");
            lateralbending = rs.getString("lateralbending");
            shocktemperature = rs.getString("shocktemperature");
            weldzoneshocknum = rs.getString("weldzoneshocknum");
            thermalimpactzonenum = rs.getString("thermalimpactzonenum");
        }
        rs.close();
        ps.close();

        putsheet(sheet,5,8,prodname);
        putsheet(sheet,5,29,judgestand);
        putsheet(sheet,5,50,prodno_specimenno);
        putsheet(sheet,7,8,specimenname);
        putsheet(sheet,7,29,weldingsteelseal);
        putsheet(sheet,7,50,matl);
        putsheet(sheet,9,8,designation_spec);
        putsheet(sheet,9,29,groovetype);
        putsheet(sheet,9,50,weldingmethod_weldingposition);
        putsheet(sheet,11,8,heatcondi);
        putsheet(sheet,11,29,representno);
        putsheet(sheet,11,50,representpart);
        putsheet(sheet,17,9,drawingnumber);
        putsheet(sheet,23,9,surfacebending);
        putsheet(sheet,25,9,backbending);
        putsheet(sheet,27,9,lateralbending);
        putsheet(sheet,19,17,shocktemperature);
        putsheet(sheet,19,22,weldzoneshocknum);
        putsheet(sheet,19,26,thermalimpactzonenum);
        putsheet(sheet,21,22,gaptype);
        putsheet(sheet,4,5,department);
        putsheet(sheet,4,47,simpleDateFormat1.format(calendar.getTime()));
        putsheet(sheet,39,46,simpleDateFormat1.format(calendar.getTime()));
        calendar.add(calendar.DATE, 3);
        putsheet(sheet,31,46,simpleDateFormat1.format(calendar.getTime()));





        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();

        conn.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "理化委托单.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;


    }
}
