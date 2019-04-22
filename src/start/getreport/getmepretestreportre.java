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
public class getmepretestreportre {
    @RequestMapping(value = "getmepretestreportre")                             //力学性能试验报告复验
    public @ResponseBody
    ResponseEntity<byte[]> getmepretestreportre(String department, String codedmarking, String area, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        int i=0;
        String num=null;
        int num_p=0;
        int matlname_id=0;
        int matlstand_id = 0;
        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"材料力学性能试验报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        putsheet(sheet,4,4,department);
        putsheet(sheet,10,0,codedmarking);
        putsheet(sheet,10,3,area);

        ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
            ps1.setString(1,codedmarking);
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                calendar.setTime(rs1.getDate("indate"));
                calendar.add(calendar.DATE, 2);
                putsheet(sheet,4,14,simpleDateFormat1.format(calendar.getTime()));

                if(rs.getInt("retimes")<10){
                    num="0"+rs.getString("retimes");
                }else {
                    num=rs.getString("retimes");
                }
                putsheet(sheet,4,29,"力学第 "+rs1.getString("indate").substring(0,4)+"-y"+num+" 号");
                num_p=rs.getInt("retimes");
            }
            rs1.close();
            ps1.close();

            putsheet(sheet,6,16,rs.getString("designation"));
            putsheet(sheet,6,21,rs.getString("spec"));

            if(rs.getString("rel1")!=null && !rs.getString("rel1").equals("")){
                putsheet(sheet,10+i*2,6,rs.getString("rel1"));
                i++;
            }
            if(rs.getString("rel2")!=null && !rs.getString("rel2").equals("")){
                putsheet(sheet,10+i*2,6,rs.getString("rel2"));
                i++;
            }
            i=0;

            if(rs.getString("rm1")!=null && !rs.getString("rm1").equals("")){
                putsheet(sheet,10+i*2,10,rs.getString("rm1"));
                i++;
            }
            if(rs.getString("rm2")!=null && !rs.getString("rm2").equals("")){
                putsheet(sheet,10+i*2,10,rs.getString("rm2"));
                i++;
            }
            i=0;

            if(rs.getString("elong1")!=null && !rs.getString("elong1").equals("")){
                putsheet(sheet,10+i*2,14,rs.getString("elong1"));
                i++;
            }
            if(rs.getString("elong2")!=null && !rs.getString("elong2").equals("")){
                putsheet(sheet,10+i*2,14,rs.getString("elong2"));
                i++;
            }
            i=0;

            if(rs.getString("bendaxdia")!=null && !rs.getString("bendaxdia").equals("")){
                putsheet(sheet,10+i*2,23,rs.getString("bendaxdia"));
                putsheet(sheet,10+i*2,27,"合格");
                putsheet(sheet,10+i*2,20,"180°");
                i++;
            }
            i=0;

            if(rs.getString("impacttemp")!=null && !rs.getString("impacttemp").equals("")){
                putsheet(sheet,10+i*2,29,rs.getString("impacttemp"));
                i++;
            }
            i=0;

            if(rs.getString("impactp1")!=null && !rs.getString("impactp1").equals("")){
                putsheet(sheet,10+i*2,31,rs.getString("impactp1"));
                i++;
            }
            if(rs.getString("impactp2")!=null && !rs.getString("impactp2").equals("")){
                putsheet(sheet,10+i*2,31,rs.getString("impactp2"));
                i++;
            }
            if(rs.getString("impactp3")!=null && !rs.getString("impactp3").equals("")){
                putsheet(sheet,10+i*2,31,rs.getString("impactp3"));
                i++;
            }
            i=0;

            calendar.setTime(rs.getDate("indate"));
            putsheet(sheet,27,29,simpleDateFormat2.format(calendar.getTime()));
        }
        rs.close();
        ps.close();


        ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            matlname_id=rs.getInt("matlname_id_matlname");
            matlstand_id = rs.getInt("contraststand_id_matlstand");
            putsheet(sheet,6,31,rs.getString("qty"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
        ps.setInt(1,matlname_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,6,4,rs.getString("matlname"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
        ps.setInt(1,matlstand_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,26,4,rs.getString("matlstand"));
        }
        rs.close();
        ps.close();






        if(num_p != 0){
            ps = conn.prepareStatement("UPDATE rematerial SET retimes = ? WHERE codedmarking = ? AND status=1");
            ps.setInt(1,num_p+1);
            ps.setString(2,codedmarking);
            ps.executeUpdate();
            ps.close();
        }






        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "材料力学性能试验报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
