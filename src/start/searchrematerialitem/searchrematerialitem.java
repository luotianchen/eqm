package start.searchrematerialitem;

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
public class searchrematerialitem {                                 //材料复验申请查询
    @RequestMapping(value = "searchrematerialitem")
    public @ResponseBody searchrematerialitemresult searchrematerialitem(@RequestBody searchrematerialitempost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchrematerialitemresult result = new searchrematerialitemresult();
        ArrayList<searchrematerialitemdata> as = new ArrayList<searchrematerialitemdata>();
        searchrematerialitemdata data = null;

        String sql = "SELECT * FROM rematerialitem WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getCodedmarking() == null || sp.getCodedmarking().equals(""))){
                sql = sql + "AND codedmarking = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ?";
            }
            ps = conn.prepareStatement(sql);
            if(!(sp.getCodedmarking() == null || sp.getCodedmarking().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getCodedmarking());
            }
            if(sp.getStatus() != -1){
                num = num + 1;
                ps.setInt(num,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchrematerialitemdata();
                data.setCodedmarking(rs.getString("codedmarking"));
                data.setExplain(rs.getString("why"));
                data.setForceperformance(rs.getString("forceperformance"));
                data.setChemicalcomposition(rs.getString("chemicalcomposition"));
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
