package start.getreport;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
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
import java.util.*;

import static start.excel.excel.*;

@Controller
@CrossOrigin
public class getpromatlreport {                                 //产品材料清单报表
    @RequestMapping(value = "getpromatlreport")
    public @ResponseBody ResponseEntity<byte[]> getpromatlreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps_x = null;
        ResultSet rs_x=null;

        int partsname_id = 0;
        int designation_id = 0;
        int millunit_id = 0;
        int utclass_id = 0;
        int f = 2;
        int i = 0;
        int t = 0;
        String dwgno = null;
        String exworkdate = null;
        String hardness = null;


        ArrayList<String> material = null;
        ArrayList<String> codedmarking_f = null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM.dd.yyyy", Locale.US);

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"产品材料清单.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String pdfname = getUploadFileName("产品材料清单.pdf");
        String url1 = uploadPath +"/"+ filename;
        String url2 = uploadPath +"/"+ pdfname;




        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);


        putsheet(sheet,1,26,prodno);

        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            dwgno = rs.getString("dwgno");
            putsheet(sheet,1,2,dwgno);
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            calendar.setTime(rs.getDate("exworkdate"));
            exworkdate = simpleDateFormat1.format(calendar.getTime());
            putsheet(sheet,30+32*t,29,exworkdate);
        }
        rs.close();

        ps1 = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status = 1 AND ispresspart = 1 ORDER BY partno is null,partno ASC  ");
        ps1.setString(1,prodno);
        rs1 = ps1.executeQuery();
        while (rs1.next()){
            if(i==0){
                material = new ArrayList<String>();
                codedmarking_f = new ArrayList<String>();
            }
            if(i>=10){
                i=0;
                t++;
                putsheet(sheet,1+32*t,26,prodno);
                putsheet(sheet,1+32*t,2,dwgno);
                putsheet(sheet,30+32*t,29,exworkdate);
                workBook.setPrintArea(
                        0,0,36,0,31+t*32
                );
                material = new ArrayList<String>();
                codedmarking_f = new ArrayList<String>();
            }
            partsname_id = rs1.getInt("parts_id_name");
            ps = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
            ps.setInt(1,partsname_id);
            rs = ps.executeQuery();
            if(rs.next()){
                putsheet(sheet,8+i*2+t*32,1,rs.getString("partsname"));
                putsheet(sheet,9+i*2+t*32,1,rs.getString("enpartsname"));
            }
            rs.close();
            ps.close();

            putsheet(sheet,8+i*2+t*32,2,rs1.getString("partno"));

            designation_id = rs1.getInt("contraststand_id_designation");
            ps = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
            ps.setInt(1,designation_id);
            rs = ps.executeQuery();
            if(rs.next()){
                putsheet(sheet,8+i*2+t*32,3,rs.getString("designation"));
            }
            rs.close();
            ps.close();

            putsheet(sheet,9+i*2+t*32,3,rs1.getString("spec"));
            putsheet(sheet,8+i*2+t*32,8,rs1.getString("codedmarking"));
            codedmarking_f.add(rs1.getString("codedmarking"));

            ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
            ps.setString(1,rs1.getString("codedmarking"));
            rs = ps.executeQuery();
            while (rs.next()){
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

                putsheet(sheet,8+i*2+t*32,4,rs.getString("heatbatchno"));

                millunit_id = rs.getInt("millunit_id_millunit");
                ps_x = conn.prepareStatement("SELECT * FROM millunit WHERE id = ?");
                ps_x.setInt(1,millunit_id);
                rs_x = ps_x.executeQuery();
                if(rs_x.next()){
                    putsheet(sheet,8+i*2+t*32,5,rs_x.getString("millunit"));
                    putsheet(sheet,9+i*2+t*32,5,rs_x.getString("millunitename"));
                }
                rs_x.close();
                ps_x.close();

                putsheet(sheet,8+i*2+t*32,6,rs.getString("heattreatcondition_id_heatcondi"));
                putsheet(sheet,8+i*2+t*32,22,xiegangrel(rs));
                putsheet(sheet,8+i*2+t*32,24,xiegangrm(rs));
                putsheet(sheet,8+i*2+t*32,26,xiegangelong(rs));
                putsheet(sheet,8+i*2+t*32,28,xieganghardness(rs));
                putsheet(sheet,8+i*2+t*32,29,rs.getString("bending_id_impacttemp"));
                putsheet(sheet,8+i*2+t*32,30,xiegangimpactp(rs));
                putsheet(sheet,8+i*2+t*32,33,rs.getString("bending_id_bendangle"));
                putsheet(sheet,8+i*2+t*32,35,rs.getString("bendaxdia"));

                utclass_id = rs.getInt("bending_id_utclass");
                ps_x = conn.prepareStatement("SELECT * FROM bending WHERE id = ?");
                ps_x.setInt(1,utclass_id);
                rs_x = ps_x.executeQuery();
                if(rs_x.next()){
                    putsheet(sheet,8+i*2+t*32,36,change(rs_x.getInt("utclass")));
                }
                rs_x.close();
                ps_x.close();
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
            ps.setString(1,rs1.getString("codedmarking"));
            rs = ps.executeQuery();
            while (rs.next()){
                putsheet(sheet,9+i*2+t*32,22,xiegangrel(rs));
                putsheet(sheet,9+i*2+t*32,24,xiegangrm(rs));
                putsheet(sheet,9+i*2+t*32,26,xiegangelong(rs));
                putsheet(sheet,9+i*2+t*32,28,xieganghardness(rs));
                putsheet(sheet,9+i*2+t*32,29,rs.getString("impacttemp"));
                putsheet(sheet,9+i*2+t*32,30,xiegangimpactp(rs));
                putsheet(sheet,9+i*2+t*32,33,rs.getString("bendangle"));
                putsheet(sheet,9+i*2+t*32,35,rs.getString("bendaxdia"));

            }
            rs.close();
            ps.close();


            if(i == 9 || rs1.isLast()){
                for (int z = 0;z<material.size();z++){
                    if(z>=12){
                        break;
                    }
                    putsheet(sheet,4+t*32,10+z,material.get(z).substring(0, 1).toUpperCase()+material.get(z).substring(1));
                }


                for (int z=0;z<codedmarking_f.size();z++){
                    ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
                    ps.setString(1,codedmarking_f.get(z));
                    rs = ps.executeQuery();
                    while (rs.next()){
                        for (int c = 0;c<material.size();c++){
                            if(c>=12){
                                break;
                            }
                            putsheet(sheet,8+z*2+t*32,10+c,rs.getString(material.get(c)));
                        }
                    }
                    rs.close();
                    ps.close();

                    ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
                    ps.setString(1,codedmarking_f.get(z));
                    rs = ps.executeQuery();
                    while (rs.next()){
                        for (int c = 0;c<material.size();c++){
                            if(c>=12){
                                break;
                            }
                            putsheet(sheet,9+z*2+t*32,10+c,rs.getString(material.get(c)));
                        }
                    }
                    rs.close();
                    ps.close();
                }
            }


            i++;
        }
        rs1.close();
        ps1.close();












        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();




        excel2Pdf(url1,url2);                                       //转PDF
        File filepdf = new File(uploadPath, pdfname);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "产品材料清单.pdf");// 文件的属性，也就是文件叫什么吧
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

    public String xieganghardness(ResultSet rs) throws SQLException {
        String b=null;
        if(rs.getString("hardness1") != null && !rs.getString("hardness1").equals("")){
            b = rs.getString("hardness1");
        }
        if(rs.getString("hardness2") != null && !rs.getString("hardness2").equals("")){
            b = rs.getString("hardness2");
        }
        if(rs.getString("hardness3") != null && !rs.getString("hardness3").equals("")){
            b = rs.getString("hardness3");
        }

        return b;
    }


    public String xiegangimpactp(ResultSet rs) throws SQLException {
        String b=null;
        ArrayList<String> a = new ArrayList<String>();
        if(rs.getString("impactp1") != null && !rs.getString("impactp1").equals("")){
            a.add(rs.getString("impactp1"));
        }
        if(rs.getString("impactp2") != null && !rs.getString("impactp2").equals("")){
            a.add(rs.getString("impactp2"));
        }
        if(rs.getString("impactp3") != null && !rs.getString("impactp3").equals("")){
            a.add(rs.getString("impactp3"));
        }

        for(int j = 0;j<a.size();j++){
            if(j==0){
                b=a.get(j);
            }else {
                b = b + "/" +a.get(j);
            }
        }


        return b;
    }

    public String xiegangrel(ResultSet rs) throws SQLException {
        String b=null;
        ArrayList<String> a = new ArrayList<String>();
        if(rs.getString("rel1") != null && !rs.getString("rel1").equals("")){
            a.add(rs.getString("rel1"));
        }
        if(rs.getString("rel2") != null && !rs.getString("rel2").equals("")){
            a.add(rs.getString("rel2"));
        }

        for(int j = 0;j<a.size();j++){
            if(j==0){
                b=a.get(j);
            }else {
                b = b + "/" +a.get(j);
            }
        }


        return b;
    }

    public String xiegangrm(ResultSet rs) throws SQLException {
        String b=null;
        ArrayList<String> a = new ArrayList<String>();
        if(rs.getString("rm1") != null && !rs.getString("rm1").equals("")){
            a.add(rs.getString("rm1"));
        }
        if(rs.getString("rm2") != null && !rs.getString("rm2").equals("")){
            a.add(rs.getString("rm2"));
        }

        for(int j = 0;j<a.size();j++){
            if(j==0){
                b=a.get(j);
            }else {
                b = b + "/" +a.get(j);
            }
        }


        return b;
    }

    public String xiegangelong(ResultSet rs) throws SQLException {
        String b=null;
        ArrayList<String> a = new ArrayList<String>();
        if(rs.getString("elong1") != null && !rs.getString("elong1").equals("")){
            a.add(rs.getString("elong1"));
        }
        if(rs.getString("elong2") != null && !rs.getString("elong2").equals("")){
            a.add(rs.getString("elong2"));
        }

        for(int j = 0;j<a.size();j++){
            if(j==0){
                b=a.get(j);
            }else {
                b = b + "/" +a.get(j);
            }
        }


        return b;
    }


    public String change(int i){
        String ii = null;
        if(i==1){
            ii = "Ⅰ";
        }
        if(i==2){
            ii = "Ⅱ";
        }
        if(i==3){
            ii = "Ⅲ";
        }
        if(i==0){
            ii = "/";
        }
        return ii;
    }
}
