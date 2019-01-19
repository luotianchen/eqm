package start.puttestboardstand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class puttestboardstand {                                    //添加试板标准
    @RequestMapping(value = "puttestboardstand")
    public @ResponseBody puttestboardstandresult puttestboardstand(@RequestBody puttestboardstandpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        puttestboardstandresult result = new puttestboardstandresult();

        try {
            ps = conn.prepareStatement("INSERT INTO testboardstand(testboardstand) VALUES (?)");
            ps.setString(1,pp.getTestboardstand());
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
