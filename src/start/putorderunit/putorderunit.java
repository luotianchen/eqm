package start.putorderunit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putorderunit {                                     //添加订货单位
    @RequestMapping(value = "putorderunit")
    public @ResponseBody putorderunitresult putorderunit(@RequestBody putorderunitpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        putorderunitresult result = new putorderunitresult();

        try {
            ps = conn.prepareStatement("INSERT INTO orderunit(orderunit,eorderunit) VALUES (?,?)");
            ps.setString(1,pp.getOrderunit());
            ps.setString(2,pp.getOrderunitename());
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
