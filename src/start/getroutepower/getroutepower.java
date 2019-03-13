package start.getroutepower;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getroutepower {                                    //获取页面访问权限
    @RequestMapping(value = "getroutepower",method = RequestMethod.GET)
    public @ResponseBody getroutepowerresult getroutepower() throws ClassNotFoundException, SQLException {         //提交页面访问权限
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(), j.getDBUSER(), j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        getroutepowerresult result = new getroutepowerresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                result.setData(rs.getString("power"));
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
