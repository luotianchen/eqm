package start.putchanneldatacache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class Putchanneldatacache {                                           //提交通道数据信息缓冲
    @RequestMapping(value = "putchanneldatacache")
    public @ResponseBody
    putchanneldatacacheresult putchanneldatacache(@RequestBody putchanneldatacachepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        putchanneldatacacheresult result = new putchanneldatacacheresult();

        try {
            ps = conn.prepareStatement("DELETE FROM channeldatacache WHERE status = 1");
            ps.executeUpdate();
            ps.close();

            for (int i = 0;i<pp.getData().size();i++){
                System.out.println(pp.getData().size());
                String sql = null;
                String sql1 = "";
                String sql2 = "";
                int num = 1;

                int name_p=0;
                int ename_p=0;
                int volume_p=0;
                int innerdia_p=0;
                int shmatl1_p=0;
                int shmatl2_p=0;
                int shmatl3_p=0;
                int shthick1_p=0;
                int shthick2_p=0;
                int shthick3_p=0;
                int liningmatl_p=0;
                int liningthick_p=0;
                int wmedia_p=0;
                int hdthick1_p=0;
                int hdthick2_p=0;
                int maxwpress_p=0;
                int depress_p=0;
                int detemp_p=0;
                int wpress_p=0;
                int wtemp_p=0;
                int testpress_p=0;
                int leaktest_p=0;
                int eleaktest_p=0;
                int leaktestp_p=0;
                int pttype_p=0;
                int epttype_p=0;

                ps1 = conn.prepareStatement("SELECT * FROM channeldatacache WHERE dwgno = ? AND name = ?");
                ps1.setString(1,pp.getDwgno());
                ps1.setString(2,pp.getData().get(i).getName());
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    if(!(pp.getData().get(i).getName()==null || pp.getData().get(i).getName().equals(""))){
                        sql2 = sql2+",?";
                        name_p=1;
                    }
                    if(!(pp.getData().get(i).getEname()==null || pp.getData().get(i).getEname().equals(""))){
                        sql1 = sql1+",ename = ?";
                        sql2 = sql2+",?";
                        ename_p=1;
                    }
                    if(!(pp.getData().get(i).getVolume()==null || pp.getData().get(i).getVolume().equals(""))){
                        sql1 = sql1+",volume = ?";
                        sql2 = sql2+",?";
                        volume_p=1;
                    }
                    if(!(pp.getData().get(i).getInnerdia()==null || pp.getData().get(i).getInnerdia().equals(""))){
                        sql1 = sql1+",innerdia = ?";
                        sql2 = sql2+",?";
                        innerdia_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl1()==null || pp.getData().get(i).getShmatl1().equals(""))){
                        sql1 = sql1+",shmatl1 = ?";
                        sql2 = sql2+",?";
                        shmatl1_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl2()==null || pp.getData().get(i).getShmatl2().equals(""))){
                        sql1 = sql1+",shmatl2 = ?";
                        sql2 = sql2+",?";
                        shmatl2_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl3()==null || pp.getData().get(i).getShmatl3().equals(""))){
                        sql1 = sql1+",shmatl3 = ?";
                        sql2 = sql2+",?";
                        shmatl3_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick1()==null || pp.getData().get(i).getShthick1().equals(""))){
                        sql1 = sql1+",shthick1 = ?";
                        sql2 = sql2+",?";
                        shthick1_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick2()==null || pp.getData().get(i).getShthick2().equals(""))){
                        sql1 = sql1+",shthick2 = ?";
                        sql2 = sql2+",?";
                        shthick2_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick3()==null || pp.getData().get(i).getShthick3().equals(""))){
                        sql1 = sql1+",shthick3 = ?";
                        sql2 = sql2+",?";
                        shthick3_p=1;
                    }
                    if(!(pp.getData().get(i).getLiningmatl()==null || pp.getData().get(i).getLiningmatl().equals(""))){
                        sql1 = sql1+",liningmatl = ?";
                        sql2 = sql2+",?";
                        liningmatl_p=1;
                    }
                    if(!(pp.getData().get(i).getLiningthick()==null || pp.getData().get(i).getLiningthick().equals(""))){
                        sql1 = sql1+",liningthick = ?";
                        sql2 = sql2+",?";
                        liningthick_p=1;
                    }
                    if(!(pp.getData().get(i).getWmedia()==null || pp.getData().get(i).getWmedia().equals(""))){
                        sql1 = sql1+",wmedia = ?";
                        sql2 = sql2+",?";
                        wmedia_p=1;
                    }
                    if(!(pp.getData().get(i).getHdthick1()==null || pp.getData().get(i).getHdthick1().equals(""))){
                        sql1 = sql1+",hdthick1 = ?";
                        sql2 = sql2+",?";
                        hdthick1_p=1;
                    }
                    if(!(pp.getData().get(i).getHdthick2()==null || pp.getData().get(i).getHdthick2().equals(""))){
                        sql1 = sql1+",hdthick2 = ?";
                        sql2 = sql2+",?";
                        hdthick2_p=1;
                    }
                    if(!(pp.getData().get(i).getMaxwpress()==null || pp.getData().get(i).getMaxwpress().equals(""))){
                        sql1 = sql1+",maxwpress = ?";
                        sql2 = sql2+",?";
                        maxwpress_p=1;
                    }
                    if(!(pp.getData().get(i).getDepress()==null || pp.getData().get(i).getDepress().equals(""))){
                        sql1 = sql1+",depress = ?";
                        sql2 = sql2+",?";
                        depress_p=1;
                    }
                    if(!(pp.getData().get(i).getDetemp()==null || pp.getData().get(i).getDetemp().equals(""))){
                        sql1 = sql1+",detemp = ?";
                        sql2 = sql2+",?";
                        detemp_p=1;
                    }
                    if(!(pp.getData().get(i).getWpress()==null || pp.getData().get(i).getWpress().equals(""))){
                        sql1 = sql1+",wpress = ?";
                        sql2 = sql2+",?";
                        wpress_p=1;
                    }
                    if(!(pp.getData().get(i).getWtemp()==null || pp.getData().get(i).getWtemp().equals(""))){
                        sql1 = sql1+",wtemp = ?";
                        sql2 = sql2+",?";
                        wtemp_p=1;
                    }
                    if(!(pp.getData().get(i).getTestpress()==null || pp.getData().get(i).getTestpress().equals(""))){
                        sql1 = sql1+",testpress = ?";
                        sql2 = sql2+",?";
                        testpress_p=1;
                    }
                    if(!(pp.getData().get(i).getLeaktest()==null || pp.getData().get(i).getLeaktest().equals(""))){
                        sql1 = sql1+",leaktest = ?";
                        sql2 = sql2+",?";
                        leaktest_p=1;
                    }
                    if(!(pp.getData().get(i).getEleaktest()==null || pp.getData().get(i).getEleaktest().equals(""))){
                        sql1 = sql1+",eleaktest = ?";
                        sql2 = sql2+",?";
                        eleaktest_p=1;
                    }
                    if(!(pp.getData().get(i).getLeaktestp()==null || pp.getData().get(i).getLeaktestp().equals(""))){
                        sql1 = sql1+",leaktestp = ?";
                        sql2 = sql2+",?";
                        leaktestp_p=1;
                    }
                    if(!(pp.getData().get(i).getPttype()==null || pp.getData().get(i).getPttype().equals(""))){
                        sql1 = sql1+",pttype = ?";
                        sql2 = sql2+",?";
                        pttype_p=1;
                    }
                    if(!(pp.getData().get(i).getEpttype()==null || pp.getData().get(i).getEpttype().equals(""))){
                        sql1 = sql1+",epttype = ?";
                        sql2 = sql2+",?";
                        epttype_p=1;
                    }
                    sql = "UPDATE channeldatacache SET name = ?"+sql1+" WHERE dwgno = ? AND name = ?";
                    System.out.println(sql);
                    ps = conn.prepareStatement(sql);
                    if(name_p==1){
                        ps.setString(num,pp.getData().get(i).getName());
                        num++;
                    }
                    if(ename_p==1){
                        ps.setString(num,pp.getData().get(i).getEname());
                        num++;
                    }
                    if(volume_p==1){
                        ps.setString(num,pp.getData().get(i).getVolume());
                        num++;
                    }
                    if(innerdia_p==1){
                        ps.setString(num,pp.getData().get(i).getInnerdia());
                        num++;
                    }
                    if(shmatl1_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl1());
                        num++;
                    }
                    if(shmatl2_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl2());
                        num++;
                    }
                    if(shmatl3_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl3());
                        num++;
                    }
                    if(shthick1_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick1());
                        num++;
                    }
                    if(shthick2_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick2());
                        num++;
                    }
                    if(shthick3_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick3());
                        num++;
                    }
                    if(liningmatl_p==1){
                        ps.setString(num,pp.getData().get(i).getLiningmatl());
                        num++;
                    }
                    if(liningthick_p==1){
                        ps.setString(num,pp.getData().get(i).getLiningthick());
                        num++;
                    }
                    if(wmedia_p==1){
                        ps.setString(num,pp.getData().get(i).getWmedia());
                        num++;
                    }
                    if(hdthick1_p==1){
                        ps.setString(num,pp.getData().get(i).getHdthick1());
                        num++;
                    }
                    if(hdthick2_p==1){
                        ps.setString(num,pp.getData().get(i).getHdthick2());
                        num++;
                    }
                    if(maxwpress_p==1){
                        ps.setString(num,pp.getData().get(i).getMaxwpress());
                        num++;
                    }
                    if(depress_p==1){
                        ps.setString(num,pp.getData().get(i).getDepress());
                        num++;
                    }
                    if(detemp_p==1){
                        ps.setString(num,pp.getData().get(i).getDetemp());
                        num++;
                    }
                    if(wpress_p==1){
                        ps.setString(num,pp.getData().get(i).getWpress());
                        num++;
                    }
                    if(wtemp_p==1){
                        ps.setString(num,pp.getData().get(i).getWtemp());
                        num++;
                    }
                    if(testpress_p==1){
                        ps.setString(num,pp.getData().get(i).getTestpress());
                        num++;
                    }
                    if(leaktest_p==1){
                        ps.setString(num,pp.getData().get(i).getLeaktest());
                        num++;
                    }
                    if(eleaktest_p==1){
                        ps.setString(num,pp.getData().get(i).getEleaktest());
                        num++;
                    }
                    if(leaktestp_p==1){
                        ps.setString(num,pp.getData().get(i).getLeaktestp());
                        num++;
                    }
                    if(pttype_p==1){
                        ps.setString(num,pp.getData().get(i).getPttype());
                        num++;
                    }
                    if(epttype_p==1){
                        ps.setString(num,pp.getData().get(i).getEpttype());
                        num++;
                    }
                    ps.setString(num,pp.getDwgno());
                    num++;
                    ps.setString(num++,pp.getData().get(i).getName());
                    ps.executeUpdate();
                    ps.close();
                }else {
                    if(!(pp.getData().get(i).getName()==null || pp.getData().get(i).getName().equals(""))){
                        sql1 = sql1+",name";
                        sql2 = sql2+",?";
                        name_p=1;
                    }
                    if(!(pp.getData().get(i).getEname()==null || pp.getData().get(i).getEname().equals(""))){
                        sql1 = sql1+",ename";
                        sql2 = sql2+",?";
                        ename_p=1;
                    }
                    if(!(pp.getData().get(i).getVolume()==null || pp.getData().get(i).getVolume().equals(""))){
                        sql1 = sql1+",volume";
                        sql2 = sql2+",?";
                        volume_p=1;
                    }
                    if(!(pp.getData().get(i).getInnerdia()==null || pp.getData().get(i).getInnerdia().equals(""))){
                        sql1 = sql1+",innerdia";
                        sql2 = sql2+",?";
                        innerdia_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl1()==null || pp.getData().get(i).getShmatl1().equals(""))){
                        sql1 = sql1+",shmatl1";
                        sql2 = sql2+",?";
                        shmatl1_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl2()==null || pp.getData().get(i).getShmatl2().equals(""))){
                        sql1 = sql1+",shmatl2";
                        sql2 = sql2+",?";
                        shmatl2_p=1;
                    }
                    if(!(pp.getData().get(i).getShmatl3()==null || pp.getData().get(i).getShmatl3().equals(""))){
                        sql1 = sql1+",shmatl3";
                        sql2 = sql2+",?";
                        shmatl3_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick1()==null || pp.getData().get(i).getShthick1().equals(""))){
                        sql1 = sql1+",shthick1";
                        sql2 = sql2+",?";
                        shthick1_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick2()==null || pp.getData().get(i).getShthick2().equals(""))){
                        sql1 = sql1+",shthick2";
                        sql2 = sql2+",?";
                        shthick2_p=1;
                    }
                    if(!(pp.getData().get(i).getShthick3()==null || pp.getData().get(i).getShthick3().equals(""))){
                        sql1 = sql1+",shthick3";
                        sql2 = sql2+",?";
                        shthick3_p=1;
                    }
                    if(!(pp.getData().get(i).getLiningmatl()==null || pp.getData().get(i).getLiningmatl().equals(""))){
                        sql1 = sql1+",liningmatl";
                        sql2 = sql2+",?";
                        liningmatl_p=1;
                    }
                    if(!(pp.getData().get(i).getLiningthick()==null || pp.getData().get(i).getLiningthick().equals(""))){
                        sql1 = sql1+",liningthick";
                        sql2 = sql2+",?";
                        liningthick_p=1;
                    }
                    if(!(pp.getData().get(i).getWmedia()==null || pp.getData().get(i).getWmedia().equals(""))){
                        sql1 = sql1+",wmedia";
                        sql2 = sql2+",?";
                        wmedia_p=1;
                    }
                    if(!(pp.getData().get(i).getHdthick1()==null || pp.getData().get(i).getHdthick1().equals(""))){
                        sql1 = sql1+",hdthick1";
                        sql2 = sql2+",?";
                        hdthick1_p=1;
                    }
                    if(!(pp.getData().get(i).getHdthick2()==null || pp.getData().get(i).getHdthick2().equals(""))){
                        sql1 = sql1+",hdthick2";
                        sql2 = sql2+",?";
                        hdthick2_p=1;
                    }
                    if(!(pp.getData().get(i).getMaxwpress()==null || pp.getData().get(i).getMaxwpress().equals(""))){
                        sql1 = sql1+",maxwpress";
                        sql2 = sql2+",?";
                        maxwpress_p=1;
                    }
                    if(!(pp.getData().get(i).getDepress()==null || pp.getData().get(i).getDepress().equals(""))){
                        sql1 = sql1+",depress";
                        sql2 = sql2+",?";
                        depress_p=1;
                    }
                    if(!(pp.getData().get(i).getDetemp()==null || pp.getData().get(i).getDetemp().equals(""))){
                        sql1 = sql1+",detemp";
                        sql2 = sql2+",?";
                        detemp_p=1;
                    }
                    if(!(pp.getData().get(i).getWpress()==null || pp.getData().get(i).getWpress().equals(""))){
                        sql1 = sql1+",wpress";
                        sql2 = sql2+",?";
                        wpress_p=1;
                    }
                    if(!(pp.getData().get(i).getWtemp()==null || pp.getData().get(i).getWtemp().equals(""))){
                        sql1 = sql1+",wtemp";
                        sql2 = sql2+",?";
                        wtemp_p=1;
                    }
                    if(!(pp.getData().get(i).getTestpress()==null || pp.getData().get(i).getTestpress().equals(""))){
                        sql1 = sql1+",testpress";
                        sql2 = sql2+",?";
                        testpress_p=1;
                    }
                    if(!(pp.getData().get(i).getLeaktest()==null || pp.getData().get(i).getLeaktest().equals(""))){
                        sql1 = sql1+",leaktest";
                        sql2 = sql2+",?";
                        leaktest_p=1;
                    }
                    if(!(pp.getData().get(i).getEleaktest()==null || pp.getData().get(i).getEleaktest().equals(""))){
                        sql1 = sql1+",eleaktest";
                        sql2 = sql2+",?";
                        eleaktest_p=1;
                    }
                    if(!(pp.getData().get(i).getLeaktestp()==null || pp.getData().get(i).getLeaktestp().equals(""))){
                        sql1 = sql1+",leaktestp";
                        sql2 = sql2+",?";
                        leaktestp_p=1;
                    }
                    if(!(pp.getData().get(i).getPttype()==null || pp.getData().get(i).getPttype().equals(""))){
                        sql1 = sql1+",pttype";
                        sql2 = sql2+",?";
                        pttype_p=1;
                    }
                    if(!(pp.getData().get(i).getEpttype()==null || pp.getData().get(i).getEpttype().equals(""))){
                        sql1 = sql1+",epttype";
                        sql2 = sql2+",?";
                        epttype_p=1;
                    }
                    sql = "INSERT INTO channeldatacache(dwgno" +sql1+
                            ",tongdaoshu) VALUES (?" +sql2+
                            ",?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(num,pp.getDwgno());
                    num++;
                    if(name_p==1){
                        ps.setString(num,pp.getData().get(i).getName());
                        num++;
                    }
                    if(ename_p==1){
                        ps.setString(num,pp.getData().get(i).getEname());
                        num++;
                    }
                    if(volume_p==1){
                        ps.setString(num,pp.getData().get(i).getVolume());
                        num++;
                    }
                    if(innerdia_p==1){
                        ps.setString(num,pp.getData().get(i).getInnerdia());
                        num++;
                    }
                    if(shmatl1_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl1());
                        num++;
                    }
                    if(shmatl2_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl2());
                        num++;
                    }
                    if(shmatl3_p==1){
                        ps.setString(num,pp.getData().get(i).getShmatl3());
                        num++;
                    }
                    if(shthick1_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick1());
                        num++;
                    }
                    if(shthick2_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick2());
                        num++;
                    }
                    if(shthick3_p==1){
                        ps.setString(num,pp.getData().get(i).getShthick3());
                        num++;
                    }
                    if(liningmatl_p==1){
                        ps.setString(num,pp.getData().get(i).getLiningmatl());
                        num++;
                    }
                    if(liningthick_p==1){
                        ps.setString(num,pp.getData().get(i).getLiningthick());
                        num++;
                    }
                    if(wmedia_p==1){
                        ps.setString(num,pp.getData().get(i).getWmedia());
                        num++;
                    }
                    if(hdthick1_p==1){
                        ps.setString(num,pp.getData().get(i).getHdthick1());
                        num++;
                    }
                    if(hdthick2_p==1){
                        ps.setString(num,pp.getData().get(i).getHdthick2());
                        num++;
                    }
                    if(maxwpress_p==1){
                        ps.setString(num,pp.getData().get(i).getMaxwpress());
                        num++;
                    }
                    if(depress_p==1){
                        ps.setString(num,pp.getData().get(i).getDepress());
                        num++;
                    }
                    if(detemp_p==1){
                        ps.setString(num,pp.getData().get(i).getDetemp());
                        num++;
                    }
                    if(wpress_p==1){
                        ps.setString(num,pp.getData().get(i).getWpress());
                        num++;
                    }
                    if(wtemp_p==1){
                        ps.setString(num,pp.getData().get(i).getWtemp());
                        num++;
                    }
                    if(testpress_p==1){
                        ps.setString(num,pp.getData().get(i).getTestpress());
                        num++;
                    }
                    if(leaktest_p==1){
                        ps.setString(num,pp.getData().get(i).getLeaktest());
                        num++;
                    }
                    if(eleaktest_p==1){
                        ps.setString(num,pp.getData().get(i).getEleaktest());
                        num++;
                    }
                    if(leaktestp_p==1){
                        ps.setString(num,pp.getData().get(i).getLeaktestp());
                        num++;
                    }
                    if(pttype_p==1){
                        ps.setString(num,pp.getData().get(i).getPttype());
                        num++;
                    }
                    if(epttype_p==1){
                        ps.setString(num,pp.getData().get(i).getEpttype());
                        num++;
                    }
                    ps.setInt(num,i+1);
                    ps.executeUpdate();
                    ps.close();
                }
                rs1.close();
                ps1.close();



            }
            result.setResult("success");
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;
    }
}
