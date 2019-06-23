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
import java.util.ArrayList;
import java.util.UUID;

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

        ArrayList<String> mat = new ArrayList<String>();                        //不为夹套的材料
        ArrayList<String> jiatao = new ArrayList<String>();                     //夹套材料
        ArrayList<String> thi = new ArrayList<String>();                        //不为夹套的厚度
        ArrayList<String> jiatao_thi = new ArrayList<String>();                     //夹套厚度
        ArrayList<String> fengtou_thi = new ArrayList<String>();            //封头厚度


        ArrayList<String> guanc_depress = new ArrayList<String>();
        ArrayList<String> guanc_detemp = new ArrayList<String>();
        ArrayList<String> guanc_maxwpress = new ArrayList<String>();
        ArrayList<String> guanc_wmedia = new ArrayList<String>();
        ArrayList<String> guanc_ewmedia = new ArrayList<String>();

        ArrayList<String> kc_depress = new ArrayList<String>();
        ArrayList<String> kc_detemp = new ArrayList<String>();
        ArrayList<String> kc_maxwpress = new ArrayList<String>();
        ArrayList<String> kc_wmedia = new ArrayList<String>();
        ArrayList<String> kc_ewmedia = new ArrayList<String>();


        String prodname = null;
        int prodname_id = 0;
        String ename = null;
        String dwgno = null;
        String type = null;                     //设备品种*
        String stand = "TSG 21-2016";          //产品标准*
        String stand_two = null;
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
        String g_ewmedia = null;                //管程工作介质英文
        String j_wmedia = null;                //夹套工作介质
        String j_ewmedia = null;                //夹套工作介质英文
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
        String ehttype = null;                   //热处理种类英文
        String httemp = null;                   //热处理温度
        String zj_name = null;                  //监督检验机构
        String zj_ename = null;                 //ename
        String zj_uniformcode = null;           //统一社会信用代码
        String zj_manuno = null;                //机构核准证编号
        int i = 0;

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

            File realfile = new File(uploadPath,"压力容器产品数据表.xlsx");
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

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps.setString(1,dwgno);
            rs = ps.executeQuery();
            if(rs.next()){
                prodname_id = rs.getInt("productname_id_prodname");
                type = rs.getString("type");
                if(!(rs.getString("mainstand") == null || rs.getString("mainstand").equals(""))){
                    stand = stand + "、" + rs.getString("mainstand");
                    stand_two = rs.getString("mainstand");
                }
                if(!(rs.getString("minorstand") == null || rs.getString("minorstand").equals("")) && !rs.getString("mainstand").equals(rs.getString("minorstand"))){
                    stand = stand + "、" + rs.getString("minorstand");
                    if(stand != null){
                        stand_two = stand_two + "、" + rs.getString("minorstand");
                    }else {
                        stand_two = rs.getString("minorstand");
                    }


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



                if(rs.getString("hdthick1")!=null && !rs.getString("hdthick1").equals("")){
                    fengtou_thi.add(rs.getString("hdthick1"));
                }

                if(rs.getString("hdthick2")!=null && !rs.getString("hdthick2").equals("")){
                    fengtou_thi.add(rs.getString("hdthick2"));
                }

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

                    volume = getme(volume,rs,"volume");
                    innerdia = getme(innerdia,rs,"innerdia");
                    pttype = getme(pttype,rs,"pttype");
                    epttype = getme(epttype,rs,"epttype");
                    testpress = getme(testpress,rs,"testpress");
                    leaktest = getme(leaktest,rs,"leaktest");
                    eleaktest = getme(eleaktest,rs,"eleaktest");
                    leaktestp = getme(leaktestp,rs,"leaktestp");

                }

                if(!rs.getString("name").equals("夹套")){
                    if(rs.getString("shmatl1")!=null && !rs.getString("shmatl1").equals("")){
                        mat.add(rs.getString("shmatl1"));
                    }
                    if(rs.getString("shmatl2")!=null && !rs.getString("shmatl2").equals("")){
                        mat.add(rs.getString("shmatl2"));
                    }
                    if(rs.getString("shmatl3")!=null && !rs.getString("shmatl3").equals("")){
                        mat.add(rs.getString("shmatl3"));
                    }


                    if(rs.getString("shthick1")!=null && !rs.getString("shthick1").equals("")){
                        thi.add(rs.getString("shthick1"));
                    }
                    if(rs.getString("shthick2")!=null && !rs.getString("shthick2").equals("")){
                        thi.add(rs.getString("shthick2"));
                    }
                    if(rs.getString("shthick3")!=null && !rs.getString("shthick3").equals("")){
                        thi.add(rs.getString("shthick3"));
                    }

                }else {
                    if(rs.getString("shmatl1")!=null && !rs.getString("shmatl1").equals("")){
                        jiatao.add(rs.getString("shmatl1"));
                    }
                    if(rs.getString("shmatl2")!=null && !rs.getString("shmatl2").equals("")){
                        jiatao.add(rs.getString("shmatl2"));
                    }
                    if(rs.getString("shmatl3")!=null && !rs.getString("shmatl3").equals("")){
                        jiatao.add(rs.getString("shmatl3"));
                    }


                    if(rs.getString("shthick1")!=null && !rs.getString("shthick1").equals("")){
                        jiatao_thi.add(rs.getString("shthick1"));
                    }
                    if(rs.getString("shthick2")!=null && !rs.getString("shthick2").equals("")){
                        jiatao_thi.add(rs.getString("shthick2"));
                    }
                    if(rs.getString("shthick3")!=null && !rs.getString("shthick3").equals("")){
                        jiatao_thi.add(rs.getString("shthick3"));
                    }
                    j_thi = getmat(jiatao_thi);
                }

                if(rs.getInt("tongdaoshu")==1){
                    c_mat = rs.getString("liningmatl");
                    c_thi = rs.getString("liningthick");
                }

//            t_mat = rs.getString("shmatl1");
//            if(rs.getString("shmatl2")!= null && !rs.getString("shmatl2").equals("")){
//                t_mat = t_mat + "/" + rs.getString("shmatl2");
//            }
//            if(rs.getString("shmatl3")!= null && !rs.getString("shmatl3").equals("")){
//                t_mat = t_mat + "/" + rs.getString("shmatl3");
//            }
//            if(i==0){
//                c_mat = rs.getString("liningmatl");
//            }
//            if(i==1){
//                j_mat = rs.getString("liningmatl");
//            }
//            if(i==2){
//                f_mat = rs.getString("liningmatl");
//            }
//
//            k_thi = rs.getString("shthick1");
//            if(rs.getString("shthick2")!= null && !rs.getString("shthick2").equals("")){
//                k_thi = k_thi + "/" + rs.getString("shthick2");
//            }
//            if(rs.getString("shthick3")!= null && !rs.getString("shthick3").equals("")){
//                k_thi = k_thi + "/" + rs.getString("shthick3");
//            }
//            if(i==0){
//                c_thi = rs.getString("liningthick");
//            }
//            if(i==1){
//                j_thi = rs.getString("liningthick");
//            }
//            if(i==2){
//                f_thi = rs.getString("liningthick");
//            }
//
//            if(rs.getString("name").equals("壳程")){
//                k_depress = rs.getString("depress");
//                k_detemp = rs.getString("detemp");
//                k_maxwpress = rs.getString("maxwpress");
//                k_wmedia = rs.getString("wmedia");
//            }


                if(rs.getString("name").contains("管程")){
                    guanc_depress.add(rs.getString("depress"));
                    guanc_detemp.add(rs.getString("detemp"));
                    guanc_maxwpress.add(rs.getString("maxwpress"));
                    guanc_wmedia.add(rs.getString("wmedia"));
                }

                if(!rs.getString("name").contains("管程") && !rs.getString("name").equals("夹套")){
                    kc_depress.add(rs.getString("depress"));
                    kc_detemp.add(rs.getString("detemp"));
                    kc_maxwpress.add(rs.getString("maxwpress"));
                    kc_wmedia.add(rs.getString("wmedia"));
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
            t_mat = getmat(mat);
            f_mat = t_mat;
            k_thi = getmat(thi);
            f_thi = getmat(fengtou_thi);
            j_mat = getmat(jiatao);
            j_thi = getmat(jiatao_thi);

            g_depress = getmat(guanc_depress);
            g_detemp = getmat(guanc_detemp);
            g_maxwpress = getmat(guanc_maxwpress);
            g_wmedia = getmat(guanc_wmedia);
            k_depress = getmat(kc_depress);
            System.out.println(kc_depress.size());
            k_detemp = getmat(kc_detemp);
            k_maxwpress = getmat(kc_maxwpress);
            k_wmedia = getmat(kc_wmedia);
            rs.close();
            ps.close();


            ps = conn.prepareStatement("SELECT * FROM supervisionunit WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                zj_name = rs.getString("name");                  //监督检验机构
                zj_ename = rs.getString("ename");                 //ename
                zj_uniformcode = rs.getString("uniformcode");           //统一社会信用代码
                zj_manuno = rs.getString("manuno");                //机构核准证编号
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


            ps = conn.prepareStatement("SELECT * FROM wmedia WHERE wmedia = ?");
            ps.setString(1,j_wmedia);
            rs = ps.executeQuery();
            if(rs.next()){
                j_ewmedia = rs.getString("wmediaen");
            }
            rs.close();
            ps.close();


            for(int z = 0;z<kc_wmedia.size();z++){
                ps = conn.prepareStatement("SELECT * FROM wmedia WHERE wmedia = ?");
                ps.setString(1,kc_wmedia.get(z));
                rs = ps.executeQuery();
                if(rs.next()){
                    kc_ewmedia.add(rs.getString("wmediaen"));
                }
                rs.close();
                ps.close();
            }

            k_ewmedia = getmat(kc_ewmedia);

            for(int z = 0;z<guanc_wmedia.size();z++){
                ps = conn.prepareStatement("SELECT * FROM wmedia WHERE wmedia = ?");
                ps.setString(1,guanc_wmedia.get(z));
                rs = ps.executeQuery();
                if(rs.next()){
                    guanc_ewmedia.add(rs.getString("wmediaen"));
                }
                rs.close();
                ps.close();
            }

            g_ewmedia = getmat(guanc_ewmedia);



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


                    putsheet(sheet,44+i*2+61,0,rs.getString("name"));
                    putsheet(sheet,44+i*2+61,3,rs.getString("model"));
                    putsheet(sheet,44+i*2+61,8,rs.getString("spec"));
                    putsheet(sheet,44+i*2+61,13,rs.getString("qty"));
                    putsheet(sheet,44+i*2+61,17,rs.getString("mfunit"));
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
            if(!chweight.equals("0")){
                putsheet(sheet,14,20,chweight);
            }
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
            putsheet(sheet,27,14,g_ewmedia);
            putsheet(sheet,26,20,j_wmedia);
            putsheet(sheet,27,20,j_ewmedia);
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
            putsheet(sheet,39,7,ceng(httype));
            if(!httemp.equals("0")){
                putsheet(sheet,38,19,httemp);
            }
            putsheet(sheet,54,11,zj_name);
            putsheet(sheet,55,11,zj_ename);
            putsheet(sheet,56,11,zj_uniformcode);
            putsheet(sheet,56,20,zj_manuno);




            putsheet(sheet,4+61,5,prodname);
            putsheet(sheet,5+61,5,ename);
            putsheet(sheet,4+61,19,type);
            putsheet(sheet,6+61,5,stand_two);
            putsheet(sheet,6+61,19,prodno);
            putsheet(sheet,8+61,5,ecode);
            putsheet(sheet,8+61,19,deservicelife);
            putsheet(sheet,10+61,6,volume);
            putsheet(sheet,10+61,14,innerdia);
            putsheet(sheet,10+61,20,proheight);
            putsheet(sheet,12+61,6,t_mat);
            putsheet(sheet,12+61,14,k_thi);
            putsheet(sheet,12+61,20,weight);
            putsheet(sheet,14+61,6,f_mat);
            putsheet(sheet,14+61,14,f_thi);
            if(!chweight.equals("0")){
                putsheet(sheet,14+61,20,chweight);
            }
            putsheet(sheet,16+61,6,c_mat);
            putsheet(sheet,16+61,14,c_thi);
            putsheet(sheet,18+61,6,j_mat);
            putsheet(sheet,18+61,14,j_thi);
            putsheet(sheet,20+61,6,k_depress);
            putsheet(sheet,20+61,14,k_detemp);
            putsheet(sheet,20+61,20,k_maxwpress);
            putsheet(sheet,22+61,6,g_depress);
            putsheet(sheet,22+61,14,g_detemp);
            putsheet(sheet,22+61,20,g_maxwpress);
            putsheet(sheet,24+61,6,j_depress);
            putsheet(sheet,24+61,14,j_detemp);
            putsheet(sheet,24+61,20,j_maxwpress);
            putsheet(sheet,26+61,6,k_wmedia);
            putsheet(sheet,27+61,6,k_ewmedia);
            putsheet(sheet,26+61,14,g_wmedia);
            putsheet(sheet,27+61,14,g_ewmedia);
            putsheet(sheet,26+61,20,j_wmedia);
            putsheet(sheet,27+61,20,j_ewmedia);
            putsheet(sheet,28+61,7,m_type);
            putsheet(sheet,29+61,7,m_etype);
            putsheet(sheet,28+61,19,installtype);
            putsheet(sheet,29+61,19,einstalltype);
            putsheet(sheet,30+61,7,supptype);
            putsheet(sheet,31+61,7,esupptype);
            putsheet(sheet,30+61,19,insultype);
            putsheet(sheet,31+61,19,einsultype);
            putsheet(sheet,32+61,7,ndetype);
            putsheet(sheet,32+61,19,nderatio);
            putsheet(sheet,34+61,7,pttype);
            putsheet(sheet,35+61,7,epttype);
            putsheet(sheet,34+61,19,testpress);
            putsheet(sheet,36+61,7,leaktest);
            putsheet(sheet,37+61,7,eleaktest);
            putsheet(sheet,36+61,19,leaktestp);
            putsheet(sheet,38+61,7,httype);
            putsheet(sheet,39+61,7,ceng(httype));
            if(!httemp.equals("0")){
                putsheet(sheet,38+61,19,httemp);
            }




            OutputStream out = new FileOutputStream(url1);
            workBook.write(out);
            out.close();

            conn.close();


            filepdf = new File(uploadPath, filename);
            HttpHeaders headers = new HttpHeaders();// 设置一个head
            headers.setContentDispositionFormData("attachment", "压力容器产品数据表.xlsx");// 文件的属性，也就是文件叫什么吧
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
            download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
            file.delete();
            filepdf.delete();
        }catch (Exception e){
            file.delete();
        }

        return download;
    }

    public String getmat(ArrayList<String> as){
        for(int i=0;i<as.size();i++){
            for (int j=0;j<as.size();j++){
                if(i!=j && as.get(i).equals(as.get(j))){
                    as.remove(as.get(j));
                }
            }
        }
        String a = null;
        if(!as.isEmpty()){
            a = as.get(0);
            for(int i=1;i<as.size();i++){
                if(null==as.get(i)|| as.get(i).equals("0") || as.get(i).equals("") ){
                    continue;
                }
                a = a+"/"+as.get(i);
            }
        }

        return a;
    }

    public String getme(String x , ResultSet rs,String a) throws SQLException {
        if(x == null){
            x = rs.getString(a);
        }else {
            if(rs.getString(a) != null){
                if(x.indexOf(rs.getString(a))==-1){
                    x = x + "/" + rs.getString(a);
                }
            }
        }

        return x;
    }

    public String ceng(String chinese){
        String eng = null;
        if(chinese.equals("退火")){
            eng = "A";
        }
        if(chinese.equals("固溶")){
            eng = "S";
        }
        if(chinese.equals("正火")){
            eng = "N";
        }

        return eng;
    }
}
