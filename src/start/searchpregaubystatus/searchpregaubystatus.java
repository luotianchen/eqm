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

        String sql = "SELECT * FROM pregaumeatable WHERE status = ?";

        try {
            if(!(sp.getGaugeno() == null || sp.getGaugeno().equals(""))){
                sql = sql + " AND gaugeno = ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setInt(1,sp.getStatus());
            if(!(sp.getGaugeno() == null || sp.getGaugeno().equals(""))){
                ps.setString(2,sp.getGaugeno());
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
                data.setUser(rs.getString("user"));
                data.setDate(rs.getString("date"));
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
