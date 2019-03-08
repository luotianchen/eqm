package start.getname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getname {                                              //通过用户名查询用户姓名
    @RequestMapping(value = "getname")
    public @ResponseBody getnameresult getname(@RequestBody getnamepost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getnameresult result = new getnameresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
            ps.setString(1,gp.getUsername());
            rs = ps.executeQuery();
            if (rs.next()){
                result.setName(rs.getString("name"));
            }
            rs.close();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
