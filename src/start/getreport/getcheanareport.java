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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getcheanareport {                                              //化学分析试验报告
    @RequestMapping(value = "getcheanareport")
    public @ResponseBody ResponseEntity<byte[]> getcheanareport(String codedmarking,String department, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = null;

        ArrayList<String> material = null;
        String num=null;
        int matlname_id=0;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"化学分析报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "化学分析报告_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,3,6,codedmarking);
        putsheet(sheet,7,0,codedmarking);
        putsheet(sheet,4,7,department);


        ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status=1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            material = new ArrayList<String>();
            putsheet(sheet,4,48,rs.getString("designation"));
            if(rs.getInt("times")<10){
                num="0"+rs.getString("times");
            }else {
                num=rs.getString("times");
            }
            simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            calendar.setTime(rs.getDate("indate"));
            putsheet(sheet,3,42,simpleDateFormat1.format(calendar.getTime())+"-c"+num);
            putsheet(sheet,7,7,simpleDateFormat1.format(calendar.getTime())+"-c"+num);

            setma(material,"c",rs);
            setma(material,"mn",rs);
            setma(material,"si",rs);
            setma(material,"p",rs);
            setma(material,"s",rs);
            setma(material,"cr",rs);
            setma(material,"ni",rs);
            setma(material,"ti",rs);
            setma(material,"cu",rs);
            setma(material,"fe",rs);
            setma(material,"n",rs);
            setma(material,"alt",rs);
            setma(material,"mo",rs);
            setma(material,"mg",rs);
            setma(material,"zn",rs);
            setma(material,"nb",rs);
            setma(material,"v",rs);
            setma(material,"b",rs);
            setma(material,"w",rs);
            setma(material,"sb",rs);
            setma(material,"al",rs);
            setma(material,"zr",rs);
            setma(material,"ca",rs);
            setma(material,"be",rs);
            setma(material,"als",rs);

            for (int z = 0;z<material.size();z++){
                if(z>=12){
                    break;
                }
                putsheet(sheet,6,29+z*2,material.get(z).substring(0, 1).toUpperCase()+material.get(z).substring(1));
            }

            for (int c = 0;c<material.size();c++){
                if(c>=12){
                    break;
                }
                rs.getString(material.get(c));
                putsheet(sheet,7,29+c*2,rs.getString(material.get(c)));
            }

        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status=1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
            calendar.setTime(rs.getDate("indate"));
            calendar.add(calendar.DATE, 3);
            putsheet(sheet,4,25,simpleDateFormat1.format(calendar.getTime()));

            putsheet(sheet,7,21,rs.getString("spec"));
            matlname_id=rs.getInt("matlname_id_matlname");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
        ps.setInt(1,matlname_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,7,14,rs.getString("matlname"));
        }
        rs.close();
        ps.close();






        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "化学分析报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }


    public void setma(ArrayList<String> as , String a , ResultSet rs) throws SQLException {
        int f = 0;
        for (int i = 0;i<as.size();i++){
            if(as.get(i).equals(a)){
                f=1;
                break;
            }
        }
        if(f==0 && !(rs.getString(a)==null || rs.getString(a).equals(""))){
            as.add(a);
        }

    }
}
