package start.changeemail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changeemail {                              //修改邮箱
    @RequestMapping(value = "changeemail")
    public @ResponseBody changeemailresult changeemail(@RequestBody changeemailpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changeemailresult result = new changeemailresult();

        try {
            ps = conn.prepareStatement("UPDATE userform SET email = ? WHERE username = ?");
            ps.setString(1,cp.getEmail());
            ps.setString(2,cp.getUsername());
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
