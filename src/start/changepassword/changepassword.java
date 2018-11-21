package start.changepassword;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changepassword {                               //修改密码
    @RequestMapping(value = "changepassword")
    public @ResponseBody changepasswordresult changepassword(@RequestBody changepasswordpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changepasswordresult result = new changepasswordresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
            ps.setString(1,cp.getUsername());
            rs = ps.executeQuery();
            if(rs.next()){
                if(cp.getPassword().equals(rs.getString("password"))){
                    rs.close();
                    ps.close();
                    ps = conn.prepareStatement("UPDATE userform SET password = ? WHERE username = ?");
                    ps.setString(1,cp.getNewpassword());
                    ps.setString(2,cp.getUsername());
                    ps.executeUpdate();
                    ps.close();
                    result.setResult("success");
                }else {
                    rs.close();
                    ps.close();
                    result.setResult("fail");
                }
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
