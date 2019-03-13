package start.putroutepower;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class putroutepower {
    @RequestMapping(value = "putroutepower")
    public @ResponseBody putroutepowerresult putroutepower(@RequestBody putroutepowerpost pp) throws ClassNotFoundException, SQLException {         //提交页面访问权限
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(), j.getDBUSER(), j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        putroutepowerresult result = new putroutepowerresult();

        try {
            ps = conn.prepareStatement("UPDATE email SET power = ? WHERE id =1");
            ps.setString(1,pp.getData());
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
