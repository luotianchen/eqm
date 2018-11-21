package start.changeroll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changeroll {                                           //修改用户角色
    @RequestMapping(value = "changeroll")
    public @ResponseBody changerollresult changeroll(@RequestBody changerollpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        changerollresult result = new changerollresult();
        try {
            ps = conn.prepareStatement("UPDATE userform SET roll_id = ? WHERE username = ?");
            ps.setInt(1,cp.getRoll());
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
