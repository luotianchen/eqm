package start.changerollname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changerollname {                                               //修改角色名称
    @RequestMapping(value = "changerollname")
    public @ResponseBody changerollnameresult changerollname(@RequestBody changerollnamepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changerollnameresult result = new changerollnameresult();

        try {
            ps = conn.prepareStatement("UPDATE roll SET rollname = ? WHERE id = ?");
            ps.setString(1,cp.getRollname());
            ps.setInt(2,cp.getRoll());
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
