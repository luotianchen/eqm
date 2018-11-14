package start.putmillunit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putmillunit {                                                                          // 添加生产单位
    @RequestMapping(value = "putmillunit")
    public @ResponseBody putmillunitresult putmillunit(@RequestBody putmillunitpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        putmillunitresult result = new putmillunitresult();

        try{
            ps=conn.prepareStatement("INSERT INTO millunit (millunit,millunitename)values(?,?)");
            ps.setString(1,pp.getMillunit());
            ps.setString(2,pp.getMillunitename());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        return result;
    }
}
