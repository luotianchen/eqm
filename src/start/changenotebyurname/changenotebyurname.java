package start.changenotebyurname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changenotebyurname {                                   //根据用户名修改备注
    @RequestMapping(value = "changenotebyurname")
    public @ResponseBody changenotebyurnameresult changenotebyurname(@RequestBody changenotebyurnamepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changenotebyurnameresult result = new changenotebyurnameresult();

        try {
            ps = conn.prepareStatement("UPDATE userform SET note = ? WHERE username = ?");
            ps.setString(1,cp.getNote());
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
