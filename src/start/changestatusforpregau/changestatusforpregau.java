package start.changestatusforpregau;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changestatusforpregau {                                //提交审核(压力表计量台帐)
    @RequestMapping(value = "changestatusforpregau")
    public @ResponseBody changestatusforpregauresult changestatusforpregau(@RequestBody changestatusforpregaupost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changestatusforpregauresult result = new changestatusforpregauresult();

        try {
            ps = conn.prepareStatement("UPDATE pregaumeatable SET status = ?, audit_user = ? WHERE id = ?");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getAudit_user());
            ps.setInt(3,cp.getId());
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
