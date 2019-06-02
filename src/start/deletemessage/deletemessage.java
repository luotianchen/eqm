package start.deletemessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class deletemessage {                                                    //删除消息
    @RequestMapping(value = "deletemessage")
    public @ResponseBody deletemessageresult deletemessage(@RequestBody deletemessagepost dp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        deletemessageresult result = new deletemessageresult();

        try {
            ps = conn.prepareStatement("DELETE FROM message WHERE id = ?");
            ps.setInt(1,dp.getId());
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
