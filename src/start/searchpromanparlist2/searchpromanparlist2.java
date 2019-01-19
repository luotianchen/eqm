package start.searchpromanparlist2;

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
public class searchpromanparlist2 {                                     //产品制造参数查询2
    @RequestMapping(value = "searchpromanparlist2")
    public @ResponseBody
    searchpromanparlist2result searchpromanparlist2(@RequestBody searchpromanparlist2post sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchpromanparlist2result result = new searchpromanparlist2result();
        ArrayList<searchpromanparlist2data> as = new ArrayList<searchpromanparlist2data>();
        searchpromanparlist2data data = null;

        String sql = "SELECT * FROM promanparlist2 WHERE 1=1 ";
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
                data = new searchpromanparlist2data();
                data.setProdno(rs.getString("prodno"));
                data.setDwgno(rs.getString("dwgno"));
                data.setExworkdate(rs.getString("exworkdate"));
                data.setAweldmaxangul(rs.getString("aweldmaxangul"));
                data.setBweldmaxangul(rs.getString("bweldmaxangul"));
                data.setAweldmaxalign(rs.getString("aweldmaxalign"));
                data.setBweldmaxalign(rs.getString("bweldmaxalign"));
                data.setWeldreinfs(rs.getString("weldreinfs"));
                data.setWeldreinfd(rs.getString("weldreinfd"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
                data.setDate(rs.getString("date"));
                data.setProheight(rs.getString("proheight"));
                data.setInnerdia(rs.getString("innerdia"));
                data.setRoundness(rs.getString("roundness"));
                data.setLength(rs.getString("length"));
                data.setStraightness(rs.getString("straightness"));
                data.setThick(rs.getString("thick"));
                data.setMinthickstand(rs.getString("minthickstand"));
                data.setMinthick(rs.getString("minthick"));
                data.setOutward(rs.getString("outward"));
                data.setConcave(rs.getString("concave"));
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
