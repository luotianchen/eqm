package start.putwmedia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putwmedia {                                                        //添加工作介质
    @RequestMapping(value = "putwmedia")
    public @ResponseBody putwmediaresult putwmedia(@RequestBody putwmediapost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putwmediaresult result = new putwmediaresult();

        try {
            ps = conn.prepareStatement("INSERT INTO wmedia(wmedia,wmediaen) values (?,?)");
            ps.setString(1,pp.getWmedia());
            ps.setString(2,pp.getWmediaen());
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
