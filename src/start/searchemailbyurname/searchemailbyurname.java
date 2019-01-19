package start.searchemailbyurname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchemailbyurname {                                  //通过用户名查询邮箱
    @RequestMapping(value = "searchemailbyurname")
    public @ResponseBody searchemailbyurnameresult searchemailbyurname(@RequestBody searchemailbyurnamepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchemailbyurnameresult result = new searchemailbyurnameresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
            ps.setString(1,sp.getUsername());
            rs = ps.executeQuery();
            if(rs.next()){
                result.setEmail(rs.getString("email"));
                result.setResult("success");
            }else {
                result.setResult("fail");
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }

}
