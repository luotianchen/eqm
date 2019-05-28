package start.updateprestatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class updateprestatus {                                          //发放记录审核
    @RequestMapping(value = "updateprestatus")
    public @ResponseBody updateprestatusresult updateprestatus(@RequestBody updateprestatuspost up) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        String prodno=null;

        updateprestatusresult result = new updateprestatusresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE audit = ?");
            ps.setString(1,up.getAudit());
            rs = ps.executeQuery();
            if(rs.next()){
                prodno = rs.getString("prodno");
            }
            rs.close();
            ps.close();


            if(up.getStatus()==1){
                ps = conn.prepareStatement("UPDATE pressureparts SET status = ?,audit_user = ? WHERE prodno=? AND status = 1");
                ps.setInt(1,3);
                ps.setString(2,up.getAudit_user());
                ps.setString(3,prodno);
                ps.executeUpdate();
                ps.close();
            }

            ps = conn.prepareStatement("UPDATE pressureparts SET status = ?,audit_user = ? WHERE audit=? AND status = 0");
            ps.setInt(1,up.getStatus());
            ps.setString(2,up.getAudit_user());
            ps.setString(3,up.getAudit());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
