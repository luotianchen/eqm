package start.getlogo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getlogo {
    @RequestMapping(value = "getlogo",method = RequestMethod.GET)
    public @ResponseBody getlogoresult getlogo() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getlogoresult result = new getlogoresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM email WHERE id=1");
            rs = ps.executeQuery();
            if(rs.next()){
                result.setUrl(rs.getString("logo"));
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
