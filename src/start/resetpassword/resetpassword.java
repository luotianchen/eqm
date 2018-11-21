package start.resetpassword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class resetpassword {                                    //重置用户密码为123456
    @RequestMapping(value = "resetpassword")
    public @ResponseBody resetpasswordresult resetpassword(@RequestBody resetpasswordpost rp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        resetpasswordresult result = new resetpasswordresult();

        try {
            ps = conn.prepareStatement("UPDATE userform SET password = '123456' WHERE username = ?");
            ps.setString(1,rp.getUsername());
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
