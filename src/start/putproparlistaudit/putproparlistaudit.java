package start.putproparlistaudit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putproparlistaudit {                                   //提交产品参数审核
    @RequestMapping(value = "putproparlistaudit")
    public @ResponseBody putproparlistauditresult putproparlistaudit(@RequestBody putproparlistauditpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putproparlistauditresult result = new putproparlistauditresult();

        try {
            ps = conn.prepareStatement("UPDATE proparlist SET audit = ?,audit_user = ? WHERE dwgno = ?");
            ps.setString(1,pp.getAudit());
            ps.setString(2,pp.getAudit_user());
            ps.setString(3,pp.getDwgno());
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
