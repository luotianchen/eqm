package start.updateprestatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class updateprestatus {
    @RequestMapping(value = "updateprestatus")
    public @ResponseBody updateprestatusresult updateprestatus(@RequestBody updateprestatuspost up) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        updateprestatusresult result = new updateprestatusresult();

        try {
            ps = conn.prepareStatement("UPDATE pressureparts SET status = ? WHERE audit=?");
            ps.setInt(1,up.getStatus());
            ps.setString(2,up.getAudit());
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
