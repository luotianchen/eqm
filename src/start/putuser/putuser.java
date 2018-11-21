package start.putuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putuser {                                                  //新增用户
    @RequestMapping(value = "putuser")
    public @ResponseBody putuserresult putuser(@RequestBody putuserpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putuserresult result = new putuserresult();

        try {
            ps = conn.prepareStatement("INSERT INTO userform(username,roll_id,name) values (?,?,?)");
            ps.setString(1,pp.getUsername());
            ps.setInt(2,pp.getRoll());
            ps.setString(3,pp.getName());
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
