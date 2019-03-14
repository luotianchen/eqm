package start.putemail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putemail {                                         //设备计量台帐邮箱设置
    @RequestMapping(value = "putemail")
    public @ResponseBody putemailresult putemail(@RequestBody putemailpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putemailresult result = new putemailresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("UPDATE email SET system_email = ?,authorization_code = ?");
                ps.setString(1,pp.getSystem_email());
                ps.setString(2,pp.getAuthorization_code());
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO email(system_email,authorization_code) VALUES (?,?)");
                ps.setString(1,pp.getSystem_email());
                ps.setString(2,pp.getAuthorization_code());
                ps.executeUpdate();
                ps.close();
            }

            ps = conn.prepareStatement("DELETE FROM email WHERE id != 1");
            ps.executeUpdate();
            ps.close();

            for(int i = 0;i<pp.getTosend_email().size();i++){
                ps = conn.prepareStatement("INSERT INTO email(system_email,authorization_code,tosend_email) VALUES (?,?,?)");
                ps.setString(1,pp.getSystem_email());
                ps.setString(2,pp.getAuthorization_code());
                ps.setString(3,pp.getTosend_email().get(i));
                ps.executeUpdate();
                ps.close();
            }
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }

        conn.close();
        return result;
    }
}
