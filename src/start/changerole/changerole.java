package start.changerole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CrossOrigin
@Controller
public class changerole {                                           //修改用户角色
    @RequestMapping(value = "changerole")
    public @ResponseBody
    changeroleresult changerole(@RequestBody changerolepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        changeroleresult result = new changeroleresult();

        String sql = null;
        int role_p=0;
        int role2_p=0;
        int role3_p=0;
        int role4_p=0;
        int role5_p=0;
        int num = 2;

        try {
            sql = "UPDATE userform SET username = ?,role_id=?,role2_id=?,role3_id=?,role4_id=?,role5_id=? WHERE username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,cp.getUsername());
            ps.setInt(2,cp.getRole());
            ps.setInt(3,cp.getRole2());
            ps.setInt(4,cp.getRole3());
            ps.setInt(5,cp.getRole4());
            ps.setInt(6,cp.getRole5());
            ps.setString(7,cp.getUsername());

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
