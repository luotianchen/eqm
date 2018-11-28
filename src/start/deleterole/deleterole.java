package start.deleterole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class deleterole {                                   //删除角色
    @RequestMapping(value = "deleterole")
    public @ResponseBody deleteroleresult deleterole(@RequestBody deleterolepost dp) throws SQLException, ClassNotFoundException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        deleteroleresult result = new deleteroleresult();

        try {
            ps = conn.prepareStatement("DELETE FROM role WHERE id = ?");
            ps.setInt(1,dp.getRole());
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
