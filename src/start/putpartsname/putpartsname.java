package start.putpartsname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class putpartsname {                                                 //添加零件名称
    @RequestMapping(value = "putpartsname")
    public @ResponseBody putpartsnameresult putpartsname(@RequestBody putpartsnamepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        putpartsnameresult result = new putpartsnameresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM parts WHERE partsname = ?");
            ps.setString(1,pp.getPartsname());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("UPDATE parts SET enpartsname = ? WHERE partsname = ?");
                ps.setString(1,pp.getEnpartsname());
                ps.setString(2,pp.getPartsname());
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO parts(partsname,enpartsname) VALUES (?,?)");
                ps.setString(1,pp.getPartsname());
                ps.setString(2,pp.getEnpartsname());
                ps.executeUpdate();
                ps.close();
            }
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
