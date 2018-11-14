package start.putaudit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putaudit {
    @RequestMapping(value = "putaudit")
    public @ResponseBody putauditresult putaudit(@RequestBody putauditpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;
        putauditresult result = new putauditresult();
        try {
            ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking=?");
            ps.setString(1,pp.getCodedmarking());
            rs=ps.executeQuery();
            if(!rs.next()){
                result.setResult("fail");
            }else {
                ps.close();
                rs.close();
                ps=conn.prepareStatement("UPDATE putmaterial SET status=? WHERE codedmarking=?");
                ps.setInt(1,pp.getStatus());
                ps.setString(2,pp.getCodedmarking());
                ps.executeUpdate();
                ps.close();
                result.setResult("success");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        return result;
    }
}
