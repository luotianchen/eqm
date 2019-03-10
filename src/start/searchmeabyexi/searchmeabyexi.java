package start.searchmeabyexi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class searchmeabyexi {                                   //根据出厂编号查询测量范围
    @RequestMapping(value = "searchmeabyexi")
    public @ResponseBody searchmeabyexiresult searchmeabyexi(@RequestBody searchmeabyexipost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchmeabyexiresult result = new searchmeabyexiresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE exitno = ?");
            ps.setString(1,sp.getExitno());
            rs = ps.executeQuery();
            if (rs.next()){
                result.setMax(rs.getString("measrangemax"));
                result.setMin(rs.getString("measrangemin"));
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
