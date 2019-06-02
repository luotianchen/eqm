package start.putmessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putmessage {                                               //发布消息
    @RequestMapping(value = "putmessage")
    public @ResponseBody putmessageresult putmessage(@RequestBody putmessagepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putmessageresult result = new putmessageresult();

        try {
            ps = conn.prepareStatement("INSERT INTO message(recieve_user,title,content,send_type,send_user,date)VALUES (?,?,?,?,?,?)");
            ps.setString(1,pp.getRecieve_user());
            ps.setString(2,pp.getTitle());
            ps.setString(3,pp.getContent());
            ps.setString(4,pp.getSend_type());
            ps.setString(5,pp.getSend_user());
            ps.setTimestamp(6,new Timestamp(new java.util.Date().getTime()));
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
