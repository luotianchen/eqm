package start.changedepartmentname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changedepartmentname {
    @RequestMapping(value = "changedepartmentname")
    public @ResponseBody changedepartmentnameresult changedepartmentname(@RequestBody changedepartmentnamepost cp) throws SQLException, ClassNotFoundException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changedepartmentnameresult result = new changedepartmentnameresult();

        try {
            ps = conn.prepareStatement("UPDATE department SET departmentname = ? WHERE id = ?");
            ps.setString(1,cp.getDepartmentname());
            ps.setInt(2,cp.getDepartment());
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
