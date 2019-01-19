package start.changestatusforprotest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changestatusforprotest {                           //产品试板委托审核
    @RequestMapping(value = "changestatusforprotest")
    public @ResponseBody changestatusforprotestresult changestatusforprotest(@RequestBody changestatusforprotestpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changestatusforprotestresult result = new changestatusforprotestresult();

        try {
            ps = conn.prepareStatement("UPDATE protestboardcom SET status = ?,audit_user = ? WHERE prodno = ?");
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
