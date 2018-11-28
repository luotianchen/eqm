package start.changerolename;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changerolename {                                               //修改角色名称
    @RequestMapping(value = "changerolename")
    public @ResponseBody
    changerolenameresult changerolename(@RequestBody changerolenamepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changerolenameresult result = new changerolenameresult();

        try {
            ps = conn.prepareStatement("UPDATE role SET rolename = ? WHERE id = ?");
            ps.setString(1,cp.getRolename());
            ps.setInt(2,cp.getRole());
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
