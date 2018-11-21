package start.changename;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changename {                                   //修改真实姓名
    @RequestMapping(value = "changename")
    public @ResponseBody changenameresult changename(@RequestBody changenamepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changenameresult result = new changenameresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
            ps.setString(1,cp.getUsername());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("UPDATE userform SET name = ? WHERE username = ?");
                ps.setString(1,cp.getNewname());
                ps.setString(2,cp.getUsername());
                ps.executeUpdate();
                ps.close();
                result.setResult("success");
            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult("fail");
        }

        return result;
    }
}
