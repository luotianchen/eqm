package start.isread;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class isread {                                                   //设置已读
    @RequestMapping(value = "isread")
    public @ResponseBody isreadresult isread(@RequestBody isreadpost ip) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        isreadresult result = new isreadresult();

        try {
            ps = conn.prepareStatement("UPDATE message SET isread = 1 WHERE id = ?");
            ps.setInt(1,ip.getId());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;
    }
}
