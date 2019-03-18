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

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getpredatasheetreport {                            //压力容器产品数据表报表
    @RequestMapping(value = "getpredatasheetreport")
    public @ResponseBody ResponseEntity<byte[]> getpredatasheetreport(String prodno, HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        String prodname = null;
        int prodname_id = 0;
        String ename = null;
        String dwgno = null;
        String type = null;                     //设备品种*
        String stand = "TSG 21-2016";          //产品标准*
        String ecode = null;                    //设备代码*
        String deservicelife = null;            //设备使用年限*
        String volume = null;                   //容器容积;*
        String innerdia = null;                 //容器内径;*
        String proheight = null;                //产品总高*
        String t_mat = null;                    //筒体材料;*
        String f_mat = null;                    //封头材料*
        String c_mat = null;                    //衬里材料*
        String j_mat = null;                    //夹套材料*
        String k_thi = null;                    //壳体厚度;*
        String f_thi = null;                    //封头厚度*
        String c_thi = null;                    //衬里厚度*
        String j_thi = null;                    //夹套厚度*
        String weight = null;                   //容器自重*
        String chweight = null;                 //盛装介质重量*
        String k_depress = null;                //壳体设计压力
        String g_depress = null;                //管程设计压力
        String j_depress = null;                //夹套设计压力
        String k_detemp = null;                //壳体设计温度
        String g_detemp = null;                //管程设计温度
        String j_detemp = null;                //夹套设计温度
        String k_maxwpress = null;                //壳体最高允许工作压力
        String g_maxwpress = null;                //管程最高允许工作压力
        String j_maxwpress = null;                //夹套最高允许工作压力
        String k_wmedia = null;                //壳体工作介质
        String k_ewmedia = null;                //壳体工作介质英文
        String g_wmedia = null;                //管程工作介质
        String j_wmedia = null;                //夹套工作介质
        String m_type = null;                   //主体结构型式*
        String m_etype = null;                  //主体结构型式英文*
        String installtype = null;              //安装型式
        String einstalltype = null;             //安装型式英文
        String supptype = null;                 //支座型式
        String esupptype = null;                //支座型式英文
        String insultype = null;                //保温绝热方式
        String einsultype = null;               //保温绝热方式英文
        String ndetype = null;                  //无损检测方法
        String nderatio = null;                 //无损检测比例
        String pttype = null;                   //耐压试验种类;
        String epttype = null;                  //耐压试验种类英文;
        String testpress = null;                //耐压试验压力;
        String leaktest = null;                 //泄漏试验种类;
        String eleaktest = null;                 //泄漏试验种类英文;
        String leaktestp = null;                //泄漏试验压力;
        String httype = null;                   //热处理种类
        String httemp = null;                   //热处理温度
        String zj_name = null;                  //监督检验机构
        String zj_ename = null;                 //ename
        String zj_uniformcode = null;           //统一社会信用代码
        String zj_manuno = null;                //机构核准证编号
        int i = 0;

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"压力容器产品数据表.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "压力容器产品数据表_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
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
            prodname_id = rs.getInt("productname_id_prodname");
            type = rs.getString("type");
            if(!(rs.getString("mainstand") == null || rs.getString("mainstand").equals(""))){
                stand = stand + "、" + rs.getString("mainstand");
            }
            if(!(rs.getString("minorstand") == null || rs.getString("minorstand").equals(""))){
                stand = stand + "、" + rs.getString("minorstand");
            }
            deservicelife = rs.getString("deservicelife");
            proheight = rs.getString("proheight");
            weight = rs.getString("weight");
            chweight = rs.getString("chweight");
            installtype = rs.getString("installtype");
            einstalltype = rs.getString("einstalltype");
            supptype = rs.getString("supptype");
            esupptype = rs.getString("esupptype");
            insultype = rs.getString("insultype");
            einsultype = rs.getString("einsultype");
            ndetype = rs.getString("ndetype");
            nderatio = rs.getString("nderatio");
            httype = rs.getString("httype");
            httemp = rs.getString("httemp");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            ecode = rs.getString("ecode");
            m_type = rs.getString("type");
            m_etype = rs.getString("etype");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1");
        ps.setString(1,dwgno);
        rs= ps.executeQuery();
        while (rs.next()){

            if(i==0){
                volume = rs.getString("volume");
                innerdia = rs.getString("innerdia");
                pttype = rs.getString("pttype");
                epttype = rs.getString("epttype");
                testpress = rs.getString("testpress");
                leaktest = rs.getString("leaktest");
                eleaktest = rs.getString("eleaktest");
                leaktestp = rs.getString("leaktestp");
            }else {

                getme(volume,rs);
                getme(innerdia,rs);
                getme(pttype,rs);
                getme(epttype,rs);
                getme(testpress,rs);
                getme(leaktest,rs);
                getme(eleaktest,rs);
                getme(leaktestp,rs);

            }

            t_mat = rs.getString("shmatl1");
            if(rs.getString("shmatl2")!= null && rs.getString("shmatl2").equals("")){
                t_mat = t_mat + "/" + rs.getString("shmatl2");
            }
            if(rs.getString("shmatl3")!= null && rs.getString("shmatl3").equals("")){
                t_mat = t_mat + "/" + rs.getString("shmatl3");
            }
            if(i==0){
                c_mat = rs.getString("liningmatl");
            }
            if(i==1){
                j_mat = rs.getString("liningmatl");
            }
            if(i==2){
                f_mat = rs.getString("liningmatl");
            }

            k_thi = rs.getString("shthick1");
            if(rs.getString("shthick2")!= null && rs.getString("shthick2").equals("")){
                k_thi = k_thi + "/" + rs.getString("shthick2");
            }
            if(rs.getString("shthick3")!= null && rs.getString("shthick3").equals("")){
                k_thi = k_thi + "/" + rs.getString("shthick3");
            }
            if(i==0){
                c_thi = rs.getString("liningthick");
            }
            if(i==1){
                j_thi = rs.getString("liningthick");
            }
            if(i==2){
                f_thi = rs.getString("liningthick");
            }

            if(rs.getString("name").equals("壳程")){
                k_depress = rs.getString("depress");
                k_detemp = rs.getString("detemp");
                k_maxwpress = rs.getString("maxwpress");
                k_wmedia = rs.getString("wmedia");
            }
            if(rs.getString("name").equals("管程")){
                g_depress = rs.getString("depress");
                g_detemp = rs.getString("detemp");
                g_maxwpress = rs.getString("maxwpress");
                g_wmedia = rs.getString("wmedia");
            }
            if(rs.getString("name").equals("夹套")){
                j_depress = rs.getString("depress");
                j_detemp = rs.getString("detemp");
                j_maxwpress = rs.getString("maxwpress");
                j_wmedia = rs.getString("wmedia");
            }

            i++;
        }
        i=0;
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM supervisionunit WHERE id = 1");
        rs = ps.executeQuery();


        ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
        ps.setInt(1,prodname_id);
        rs = ps.executeQuery();
        if(rs.next()){
            prodname = rs.getString("prodname");
            ename = rs.getString("ename");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM wmedia WHERE wmedia = ?");
        ps.setString(1,k_wmedia);
        rs = ps.executeQuery();
        if(rs.next()){
            k_ewmedia = rs.getString("wmediaen");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM safedisdevice WHERE status = 1 AND dwgno = ?");
        ps.setString(1,dwgno);
        rs = ps.executeQuery();
        while (rs.next()){
            if(i<5){
                putsheet(sheet,44+i*2,0,rs.getString("name"));
                putsheet(sheet,44+i*2,3,rs.getString("model"));
                putsheet(sheet,44+i*2,8,rs.getString("spec"));
                putsheet(sheet,44+i*2,13,rs.getString("qty"));
                putsheet(sheet,44+i*2,17,rs.getString("mfunit"));
            }
            i++;
        }
        i = 0;
        rs.close();
        ps.close();

        System.out.println(prodname);
        putsheet(sheet,4,5,prodname);
        putsheet(sheet,5,5,ename);
        putsheet(sheet,4,19,type);
        putsheet(sheet,6,5,stand);
        putsheet(sheet,6,19,prodno);
        putsheet(sheet,8,5,ecode);
        putsheet(sheet,8,19,deservicelife);
        putsheet(sheet,10,6,volume);
        putsheet(sheet,10,14,innerdia);
        putsheet(sheet,10,20,proheight);
        putsheet(sheet,12,6,t_mat);
        putsheet(sheet,12,14,k_thi);
        putsheet(sheet,12,20,weight);
        putsheet(sheet,14,6,f_mat);
        putsheet(sheet,14,14,f_thi);
        putsheet(sheet,14,20,chweight);
        putsheet(sheet,16,6,c_mat);
        putsheet(sheet,16,14,c_thi);
        putsheet(sheet,18,6,j_mat);
        putsheet(sheet,18,14,j_thi);
        putsheet(sheet,20,6,k_depress);
        putsheet(sheet,20,14,k_detemp);
        putsheet(sheet,20,20,k_maxwpress);
        putsheet(sheet,22,6,g_depress);
        putsheet(sheet,22,14,g_detemp);
        putsheet(sheet,22,20,g_maxwpress);
        putsheet(sheet,24,6,j_depress);
        putsheet(sheet,24,14,j_detemp);
        putsheet(sheet,24,20,j_maxwpress);
        putsheet(sheet,26,6,k_wmedia);
        putsheet(sheet,27,6,k_ewmedia);
        putsheet(sheet,26,14,g_wmedia);
        putsheet(sheet,26,20,j_wmedia);
        putsheet(sheet,28,7,m_type);
        putsheet(sheet,29,7,m_etype);
        putsheet(sheet,28,19,installtype);
        putsheet(sheet,29,19,einstalltype);
        putsheet(sheet,30,7,supptype);
        putsheet(sheet,31,7,esupptype);
        putsheet(sheet,30,19,insultype);
        putsheet(sheet,31,19,einsultype);
        putsheet(sheet,32,7,ndetype);
        putsheet(sheet,32,19,nderatio);
        putsheet(sheet,34,7,pttype);
        putsheet(sheet,35,7,epttype);
        putsheet(sheet,34,19,testpress);
        putsheet(sheet,36,7,leaktest);
        putsheet(sheet,37,7,eleaktest);
        putsheet(sheet,36,19,leaktestp);
        putsheet(sheet,38,7,httype);
        putsheet(sheet,38,19,httemp);
        putsheet(sheet,54,11,zj_name);
        putsheet(sheet,55,11,zj_ename);
        putsheet(sheet,56,11,zj_uniformcode);
        putsheet(sheet,56,20,zj_manuno);



        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "压力容器产品数据表.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }

    public void getme(String x , ResultSet rs) throws SQLException {
        if(x == null){
            x = rs.getString("volume");
        }else {
            if(rs.getString("volume") != null){
                x = x + "/" + rs.getString("volume");
            }
        }
    }
}
