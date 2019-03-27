package start.searchpreandleaknew;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchpreandleaknew {                                     //新试压参数查询
    @RequestMapping(value = "searchpreandleaknew")
    public @ResponseBody
    searchpreandleaknewresult searchpreandleaknew(@RequestBody searchpreandleaknewpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchpreandleaknewresult result = new searchpreandleaknewresult();
        ArrayList<searchpreandleaknewdata> as = new ArrayList<searchpreandleaknewdata>();
        searchpreandleaknewdata data = null;
        searchpreandleaknewdated dated1 = null;
        searchpreandleaknewdated dated2 = null;
        searchpreandleaknewdated dated3 = null;
        searchpreandleaknewpre press = null;
        searchpreandleaknewleak leak = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                if(!(rs.getString("presstestp_id_ppart1") == null || rs.getString("presstestp_id_ppart1").equals(""))){
                    data = new searchpreandleaknewdata();
                    ps1 = conn.prepareStatement("SELECT * FROM presstestp WHERE id = ?");
                    ps1.setInt(1,rs.getInt("presstestp_id_ppart1"));
                    rs1 = ps1.executeQuery();
                    if(rs1.next()){
                        data.setName(rs1.getString("presstestp"));
                    }
                    rs1.close();
                    ps1.close();

                    data.setTestmedia(rs.getString("testmedia"));
                    data.setEtestmedia(rs.getString("etestmedia"));
                    data.setClcontent(rs.getString("clcontent"));

                    ps1 = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND name = ? AND status = 1");
                    ps1.setString(1,rs.getString("dwgno"));
                    ps1.setString(2,data.getName());
                    rs1 = ps1.executeQuery();
                    if(rs1.next()){
                        if(rs1.getString("leaktest").equals("气密性试验") || rs1.getString("leaktest").equals("氦检漏试验")){
                            data.setLeakagestatus(1);
                        }else {
                            data.setLeakagestatus(0);
                        }
                    }
                    rs1.close();
                    ps1.close();

                    if(rs.getString("dated1") != null && !rs.getString("dated1").equals("")){
                        dated1 = new searchpreandleaknewdated();
                        dated1.setDate(rs.getString("dated1"));

                        press = new searchpreandleaknewpre();
                        ps1 = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                        ps1.setString(1,sp.getProdno());
                        ps1.setString(2,data.getName());
                        ps1.setString(3,"1");
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            press.setDate(rs1.getString("date"));
                            press.setPgaugeno1(rs1.getString("pgaugeno1"));
                            press.setPgaugeno2(rs1.getString("pgaugeno2"));
                            press.setDewelltime(rs1.getInt("dewelltime"));
                            press.setCircutemp(rs1.getInt("circutemp"));
                            press.setMediatemp(rs1.getInt("mediatemp"));
                            press.setTestpress(rs1.getString("testpress"));
                            press.setTestmedia(rs1.getString("testmedia"));
                            dated1.setPress(press);
                        }
                        rs1.close();
                        ps1.close();


                        if(data.getLeakagestatus()==1){
                            leak = new searchpreandleaknewleak();
                            ps1 = conn.prepareStatement("SELECT * FROM leakagetest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                            ps1.setString(1,sp.getProdno());
                            ps1.setString(2,data.getName());
                            ps1.setString(3,"1");
                            rs1 = ps1.executeQuery();
                            if(rs1.next()){
                                leak.setDate(rs1.getString("date"));
                                leak.setPgaugeno1(rs1.getString("pgaugeno1"));
                                leak.setPgaugeno2(rs1.getString("pgaugeno2"));
                                leak.setDewelltime(rs1.getInt("dewelltime"));
                                leak.setCircutemp(rs1.getInt("circutemp"));
                                leak.setMediatemp(rs1.getInt("mediatemp"));
                                leak.setLeaktestp(rs1.getString("leaktestp"));
                                leak.setTestmedia(rs1.getString("testmedia"));
                                dated1.setLeak(leak);
                            }
                            rs1.close();
                            ps1.close();

                        }
                        data.setDated1(dated1);
                    }

                    if(rs.getString("dated2") != null && !rs.getString("dated2").equals("")){
                        dated2 = new searchpreandleaknewdated();
                        dated2.setDate(rs.getString("dated2"));

                        press = new searchpreandleaknewpre();
                        ps1 = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                        ps1.setString(1,sp.getProdno());
                        ps1.setString(2,data.getName());
                        ps1.setString(3,"2");
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            press.setDate(rs1.getString("date"));
                            press.setPgaugeno1(rs1.getString("pgaugeno1"));
                            press.setPgaugeno2(rs1.getString("pgaugeno2"));
                            press.setDewelltime(rs1.getInt("dewelltime"));
                            press.setCircutemp(rs1.getInt("circutemp"));
                            press.setMediatemp(rs1.getInt("mediatemp"));
                            press.setTestpress(rs1.getString("testpress"));
                            press.setTestmedia(rs1.getString("testmedia"));
                            dated2.setPress(press);
                        }
                        rs1.close();
                        ps1.close();

                        if(data.getLeakagestatus()==1){
                            leak = new searchpreandleaknewleak();
                            ps1 = conn.prepareStatement("SELECT * FROM leakagetest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                            ps1.setString(1,sp.getProdno());
                            ps1.setString(2,data.getName());
                            ps1.setString(3,"2");
                            rs1 = ps1.executeQuery();
                            if(rs1.next()){
                                leak.setDate(rs1.getString("date"));
                                leak.setPgaugeno1(rs1.getString("pgaugeno1"));
                                leak.setPgaugeno2(rs1.getString("pgaugeno2"));
                                leak.setDewelltime(rs1.getInt("dewelltime"));
                                leak.setCircutemp(rs1.getInt("circutemp"));
                                leak.setMediatemp(rs1.getInt("mediatemp"));
                                leak.setLeaktestp(rs1.getString("leaktestp"));
                                leak.setTestmedia(rs1.getString("testmedia"));
                                dated2.setLeak(leak);
                            }
                            rs1.close();
                            ps1.close();
                        }
                        data.setDated2(dated2);
                    }

                    if(rs.getString("dated3") != null && !rs.getString("dated3").equals("")){
                        dated3 = new searchpreandleaknewdated();
                        dated3.setDate(rs.getString("dated3"));

                        press = new searchpreandleaknewpre();
                        ps1 = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                        ps1.setString(1,sp.getProdno());
                        ps1.setString(2,data.getName());
                        ps1.setString(3,"3");
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            press.setDate(rs1.getString("date"));
                            press.setPgaugeno1(rs1.getString("pgaugeno1"));
                            press.setPgaugeno2(rs1.getString("pgaugeno2"));
                            press.setDewelltime(rs1.getInt("dewelltime"));
                            press.setCircutemp(rs1.getInt("circutemp"));
                            press.setMediatemp(rs1.getInt("mediatemp"));
                            press.setTestpress(rs1.getString("testpress"));
                            press.setTestmedia(rs1.getString("testmedia"));
                            dated3.setPress(press);
                        }
                        rs1.close();
                        ps1.close();

                        if(data.getLeakagestatus()==1){
                            leak = new searchpreandleaknewleak();
                            ps1 = conn.prepareStatement("SELECT * FROM leakagetest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                            ps1.setString(1,sp.getProdno());
                            ps1.setString(2,data.getName());
                            ps1.setString(3,"3");
                            rs1 = ps1.executeQuery();
                            if(rs1.next()){
                                leak.setDate(rs1.getString("date"));
                                leak.setPgaugeno1(rs1.getString("pgaugeno1"));
                                leak.setPgaugeno2(rs1.getString("pgaugeno2"));
                                leak.setDewelltime(rs1.getInt("dewelltime"));
                                leak.setCircutemp(rs1.getInt("circutemp"));
                                leak.setMediatemp(rs1.getInt("mediatemp"));
                                leak.setLeaktestp(rs1.getString("leaktestp"));
                                leak.setTestmedia(rs1.getString("testmedia"));
                                dated3.setLeak(leak);
                            }
                            rs1.close();
                            ps1.close();
                        }
                        data.setDated3(dated3);
                    }
                }
                as.add(data);
            }
            rs.close();
            ps.close();
            result.setResult("success");
            result.setData(as);



        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
