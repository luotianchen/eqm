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
import start.getreportprecontainer.getreportprecontainer;
import start.getreportprecontainer.searchdatacontraststandresult;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getprecontainerreport {                                            //压力容器外观及几何尺寸检验报告报表
    @RequestMapping(value = "getprecontainerreport")
    public @ResponseBody ResponseEntity<byte[]> getprecontainerreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;


        String dwgno = null;
        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            dwgno = rs.getString("dwgno");
        }
        rs.close();
        ps.close();

        getreportprecontainer g = new getreportprecontainer();              //标准值计算
        searchdatacontraststandresult sr = g.searchdatacontraststand(dwgno);

        String codedmarking = null;
        int matlname_id = 0;

        String aweldmaxangul = null;                                //A类焊缝最大棱角度
        String bweldmaxangul = null;                                //B类焊缝最大棱角度
        String aweldmaxalign = null;                                //A类焊缝最大错边量
        String bweldmaxalign = null;                                //B类焊缝最大错边量
        String weldreinfs = null;                                   //焊缝余高（单面坡口）
        String weldreinfd = null;                                   //焊缝余高（双面坡口）
        String proheight = null;                                               //产品总高实测值
        String innerdia = null;                                     //筒体内径实测值
        String roundness = null;                                    //筒体圆度实测值
        String length = null;                                                  //筒体长度标准值实测值
        String straightness = null;                                            //筒体直线度实测值

        String shthick = null;                                      //冷卷筒节投料的钢材厚度标准
        String thick = null;                                        //冷卷筒体投料厚度实测值
        String minthickstand = null;                                //封头成形最小厚度标准值
        String minthick = null;                                     //封头成形最小厚度实测值
        String exworkdate1 = null;                                   //完工日期
        String exworkdate2 = null;                                   //完工日期

        String outward = null;                                      //封头形状偏差外凸实测值
        String concave = null;                                      //封头形状偏差内凹实测值

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMM.dd.yyyy", Locale.US);

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"压力容器外观及几何尺寸检验报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = UUID.randomUUID().toString()+".xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if (rs.next()){
            aweldmaxangul = rs.getString("aweldmaxangul");                                //A类焊缝最大棱角度
            aweldmaxangul = aweldmaxangul.substring(1,aweldmaxangul.length()-1);
            aweldmaxangul=aweldmaxangul.replaceAll(",","/");

            bweldmaxangul = rs.getString("bweldmaxangul");                                //B类焊缝最大棱角度
            bweldmaxangul = bweldmaxangul.substring(1,bweldmaxangul.length()-1);
            bweldmaxangul=bweldmaxangul.replaceAll(",","/");

            aweldmaxalign = rs.getString("aweldmaxalign");                                //A类焊缝最大错边量
            aweldmaxalign = aweldmaxalign.substring(1,aweldmaxalign.length()-1);
            aweldmaxalign=aweldmaxalign.replaceAll(",","/");

            bweldmaxalign = rs.getString("bweldmaxalign");                                //B类焊缝最大错边量
            bweldmaxalign = bweldmaxalign.substring(1,bweldmaxalign.length()-1);
            bweldmaxalign=bweldmaxalign.replaceAll(",","/");

            weldreinfs = rs.getString("weldreinfs");                                   //焊缝余高（单面坡口）
            weldreinfs = weldreinfs.substring(1,weldreinfs.length()-1);
            weldreinfs=weldreinfs.replaceAll(",","/");

            weldreinfd = rs.getString("weldreinfd");                                   //焊缝余高（双面坡口）
            weldreinfd = weldreinfd.substring(1,weldreinfd.length()-1);
            weldreinfd=weldreinfd.replaceAll(",","/");

            proheight = rs.getString("proheight");                                               //产品总高实测值

            innerdia = rs.getString("innerdia");                                     //筒体内径实测值
            innerdia = innerdia.substring(1,innerdia.length()-1);
            innerdia=innerdia.replaceAll(",","/");

            roundness = rs.getString("roundness");                                    //筒体圆度实测值
            roundness = roundness.substring(1,roundness.length()-1);
            roundness=roundness.replaceAll(",","/");

            outward = rs.getString("outward");                                    //筒体圆度实测值
            outward = outward.substring(1,outward.length()-1);
            outward=outward.replaceAll(",","/");

            concave = rs.getString("concave");                                    //筒体圆度实测值
            concave = concave.substring(1,concave.length()-1);
            concave=concave.replaceAll(",","/");


            length = rs.getString("length");                                                  //筒体长度标准值实测值
            straightness = rs.getString("straightness");                                            //筒体直线度实测值


            thick = rs.getString("thick");                                        //冷卷筒体投料厚度实测值
            minthickstand = rs.getString("minthickstand");                                //封头成形最小厚度标准值
            minthick = rs.getString("minthick");                                     //封头成形最小厚度实测值
            calendar.setTime(rs.getDate("exworkdate"));
            exworkdate1 = simpleDateFormat1.format(calendar.getTime());
            exworkdate2 = simpleDateFormat2.format(calendar.getTime());

        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1 ORDER BY tongdaoshu ASC ");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        while (rs.next()){
            String sh = null;

            sh = rs.getString("shthick1");                                      //冷卷筒节投料的钢材厚度标准

            if(rs.getString("shthick2")!=null && !rs.getString("shthick2").equals("") && sh.indexOf(rs.getString("shthick2"))== -1 && shthick.indexOf(rs.getString("shthick2"))==-1){
                sh = sh + "/" + rs.getString("shthick2");
            }
            if(rs.getString("shthick3")!=null && !rs.getString("shthick3").equals("") && sh.indexOf(rs.getString("shthick3"))== -1 && shthick.indexOf(rs.getString("shthick3"))==-1){
                sh = sh + "/" + rs.getString("shthick3");
            }

            if(shthick == null || shthick.equals("")){
                shthick = sh;
            }else {
                shthick = shthick + "/" +sh;
            }


        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        while (rs.next()){
            ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
            ps1.setString(1,rs.getString("codedmarking"));
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                matlname_id = rs1.getInt("matlname_id_matlname");
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
            ps1.setInt(1,matlname_id);
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                if(rs1.getString("matlname").contains("法兰")){
                    putsheet(sheet,45,5,"符  合\nConforming");
                    putsheet(sheet,45,7,"合  格\nAcceptable");
                    putsheet(sheet,47,5,"符  合\nConforming");
                    putsheet(sheet,47,7,"合  格\nAcceptable");
                    putsheet(sheet,49,5,"符  合\nConforming");
                    putsheet(sheet,49,7,"合  格\nAcceptable");
                    break;
                }else {
                    putsheet(sheet,45,5,"------");
                    putsheet(sheet,45,7,"------");
                    putsheet(sheet,47,5,"------");
                    putsheet(sheet,47,7,"------");
                    putsheet(sheet,49,5,"------");
                    putsheet(sheet,49,7,"------");
                }
            }
            rs1.close();
            ps1.close();

        }
        rs.close();
        ps.close();


        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        while (rs.next()){
            if(rs.getString("name").equals("补强圈")){
                putsheet(sheet,55,5,"符  合\nConforming");
                putsheet(sheet,55,7,"合  格\nAcceptable");
                break;
            }else{
                putsheet(sheet,55,5,"------");
                putsheet(sheet,55,7,"------");
            }
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,65,0,"结论：  本台产品外观及几何尺寸经检验符合"+rs.getString("minorstand")+"之要求，结论合格。");
            putsheet(sheet,66,0,"Conlusion: The profile and geometric size of product has been inspected and is conforming to the requirements in "+rs.getString("minorstand")+", the result is Acceptable. ");
        }
        rs.close();
        ps.close();




        putsheet(sheet,3,0,dwgno);
        putsheet(sheet,3,6,prodno);
        putsheet(sheet,7,4,String.valueOf(sr.getData().getProheight()));
        putsheet(sheet,7,5,proheight);
        putsheet(sheet,9,4,listtost(sr.getData().getInnerdia()));
        putsheet(sheet,9,5,innerdia);
        putsheet(sheet,11,4,String.valueOf(sr.getData().getLength()));
        putsheet(sheet,11,5,length);
        putsheet(sheet,13,4,String.valueOf(sr.getData().getStraightness()));
        putsheet(sheet,13,5,straightness);
        putsheet(sheet,15,4,listtost(sr.getData().getRoundness()));
        putsheet(sheet,15,5,roundness);

        putsheet(sheet,17,4,shthick);
        putsheet(sheet,17,5,thick);
        putsheet(sheet,19,4,minthickstand);
        putsheet(sheet,19,5,minthick);

        putsheet(sheet,21,5,outward);
        putsheet(sheet,22,5,concave);

        putsheet(sheet,25,4,listtost(sr.getData().getAweldmaxangul()));
        putsheet(sheet,25,5,aweldmaxangul);
        putsheet(sheet,27,4,listtost(sr.getData().getBweldmaxangul()));
        putsheet(sheet,27,5,bweldmaxangul);
        putsheet(sheet,29,4,listtost(sr.getData().getAweldmaxalign()));
        putsheet(sheet,29,5,aweldmaxalign);
        putsheet(sheet,31,4,listtost(sr.getData().getBweldmaxalign()));
        putsheet(sheet,31,5,bweldmaxalign);

        putsheet(sheet,35,4,listtost(sr.getData().getWeldreinfs()));
        putsheet(sheet,35,5,weldreinfs);
        putsheet(sheet,37,4,listtost(sr.getData().getWeldreinfd()));
        putsheet(sheet,37,5,weldreinfd);


        putsheet(sheet,67,5,exworkdate1);
        putsheet(sheet,68,5,exworkdate2);






        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();

        conn.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "压力容器外观及几何尺寸检验报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }

    public String listtost(ArrayList<Double> as){
        String s = null;
        double x = 0;
        for (int i = 0;i<as.size();i++){
            if(i==0){
                s = String.valueOf(as.get(i));
            }else {

                if(as.get(i)==0){
                    continue;
                }

                if(s.indexOf(String.valueOf(as.get(i)))==-1){
                    s =s + "/" + String.valueOf(as.get(i));
                }
            }
        }
        return s;
    }
}
