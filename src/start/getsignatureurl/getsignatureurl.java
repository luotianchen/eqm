package start.getsignatureurl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class getsignatureurl {                                                      //获取用户签名下载地址
    @RequestMapping(value = "getsignatureurl")
    public @ResponseBody getsignatureurlresult getsignatureurl(@RequestBody getsignatureurlpost gp) throws SQLException, ClassNotFoundException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getsignatureurlresult result = new getsignatureurlresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
            ps.setString(1,gp.getUsername());
            rs = ps.executeQuery();
            if(rs.next()){
                result.setUrl(rs.getString("signature"));
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
