package start.searchcodbydes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class searchcodbydes {                                           //根据牌号查询入库编号
    @RequestMapping(value = "searchcodbydes")
    public @ResponseBody searchcodbydesresult searchcodbydes(@RequestBody searchcodbydespost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchcodbydesresult result = new searchcodbydesresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM contraststand WHERE designation = ?");
            ps.setString(1,sp.getDesignation());
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE contraststand_id_designation = ?");
                ps1.setInt(1,rs.getInt("id"));
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    as.add(rs1.getString("codedmarking"));
                }
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            for (int i = 0; i < as.size() - 1; i++) {
                for (int q = as.size() - 1; q > i; q--) {
                    if (as.get(q).equals(as.get(i))) {
                        as.remove(q);
                    }
                }
            }
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
