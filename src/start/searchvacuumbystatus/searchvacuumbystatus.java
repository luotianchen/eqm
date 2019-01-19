package start.searchvacuumbystatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchvacuumbystatus {                                     //查询真空参数
    @RequestMapping(value = "searchvacuumbystatus")
    public @ResponseBody searchvacuumbystatusresult searchvacuumbystatus(@RequestBody searchvacuumbystatuspost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchvacuumbystatusresult result = new searchvacuumbystatusresult();
        ArrayList<searchvacuumbystatusdata> as = new ArrayList<searchvacuumbystatusdata>();
        searchvacuumbystatusdata data = null;

        String sql = "SELECT * FROM vacuumparameters WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ?";
            }
            ps = conn.prepareStatement(sql);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getProdno());
            }
            if(sp.getStatus() != -1){
                num = num + 1;
                ps.setInt(num,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchvacuumbystatusdata();
                data.setProdno(rs.getString("prodno"));
                data.setInitnum(rs.getInt("initnum"));
                data.setStatnum(rs.getInt("statnum"));
                data.setInitpa(rs.getInt("initpa"));
                data.setStatpa(rs.getInt("statpa"));
                data.setHtcurrent(rs.getInt("htcurrent"));
                data.setInitdate(rs.getString("initdate"));
                data.setEnddate(rs.getString("enddate"));
                data.setSealvacu(rs.getInt("sealvacu"));
                data.setSealdate(rs.getString("sealdate"));
                data.setTesttemp(rs.getInt("testtemp"));
                data.setSealtemp(rs.getInt("sealtemp"));
                data.setVacuop(rs.getString("vacuop"));
                data.setLeakoutrate(rs.getString("leakoutrate"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
                data.setDate(rs.getString("date"));
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
