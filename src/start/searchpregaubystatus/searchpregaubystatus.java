package start.searchpregaubystatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;
import start.liststring.liststring;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchpregaubystatus {                                         //查询所有计量台帐数据
    @RequestMapping(value = "searchpregaubystatus")
    public @ResponseBody searchpregaubystatusresult searchpregaubystatus(@RequestBody searchpregaubystatuspost sp)throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchpregaubystatusresult result = new searchpregaubystatusresult();
        searchpregaubystatusdata data =null;
        ArrayList<searchpregaubystatusdata> as = new ArrayList<searchpregaubystatusdata>();

        liststring ls = new liststring();

        String sql = "SELECT `id`, `gaugename`, `gaugeno`, `exitno`, `type`, `measrangemin`, `measrangemax`, `accuclass`, `millunit`, `exitdate`, `managlevel`, `calibdate`, `recalibdate`, `calibinterval`, `specialist`, `alarm`, `note`, (select `name` from userform where username = user) as `user`, `status`,  (select `name` from userform where username = audit_user) as `audit_user`, `date` FROM pregaumeatable WHERE status = ?";
        int num = 1;

        try {
            if(!(sp.getGaugeno() == null || sp.getGaugeno().equals(""))){
                sql = sql + " AND gaugeno = ?";
            }
            if(!(sp.getExitno() == null || sp.getExitno().equals(""))){
                sql = sql + " AND exitno = ?";
            }
            if(!(sp.getCalibdate() == null || sp.getCalibdate().equals(""))){
                sql = sql + " AND calibdate < ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setInt(num,sp.getStatus());
            num = num+1;
            if(!(sp.getGaugeno() == null || sp.getGaugeno().equals(""))){
                ps.setString(num,sp.getGaugeno());
                num = num+1;
            }
            if(!(sp.getExitno() == null || sp.getExitno().equals(""))){
                ps.setString(num,sp.getExitno());
                num = num+1;
            }
            if(!(sp.getCalibdate() == null || sp.getCalibdate().equals(""))){
                ps.setString(num,sp.getCalibdate());
                num = num+1;
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchpregaubystatusdata();
                data.setId(rs.getInt("id"));
                data.setGaugename(rs.getString("gaugename"));
                data.setGaugeno(rs.getString("gaugeno"));
                data.setExitno(rs.getString("exitno"));
                data.setType(rs.getString("type"));
                data.setMeasrangemin(rs.getDouble("measrangemin"));
                data.setMeasrangemax(rs.getDouble("measrangemax"));
                data.setAccuclass(rs.getString("accuclass"));
                data.setMillunit(rs.getString("millunit"));
                data.setExitdate(rs.getString("exitdate"));
                data.setManaglevel(rs.getString("managlevel"));
                data.setCalibdate(rs.getString("calibdate"));
                data.setRecalibdate(rs.getString("recalibdate"));
                data.setSpecialist(ls.stringtolist(rs.getString("specialist"),"#"));
                data.setCalibinterval(rs.getString("calibinterval"));
                data.setNote(rs.getString("note"));
                data.setDate(rs.getString("date"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));

                as.add(data);
            }
            rs.close();
            ps.close();
            Collections.reverse(as);                                          //将list倒序
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }

}
