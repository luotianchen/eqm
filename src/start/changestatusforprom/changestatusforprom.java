package start.changestatusforprom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changestatusforprom {                                  //产品制造参数审核
    @RequestMapping(value = "changestatusforprom")
    public @ResponseBody changestatusforpromresult changestatusforprom(@RequestBody changestatusforprompost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changestatusforpromresult result = new changestatusforpromresult();

        try {
            if(cp.getStatus()==1){
                ps = conn.prepareStatement("UPDATE promanparlist SET status = ?,audit_user = ? WHERE prodno = ? AND status = 1");
                ps.setInt(1,3);
                ps.setString(2,cp.getAudit_user());
                ps.setString(3,cp.getProdno());
                ps.executeUpdate();
                ps.close();
            }

            ps = conn.prepareStatement("UPDATE promanparlist SET status = ?,audit_user = ? WHERE prodno = ? AND status = 0");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getAudit_user());
            ps.setString(3,cp.getProdno());
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
