package start.putdepartment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putdepartment {
    @RequestMapping(value = "putdepartment")
    public @ResponseBody putdepartmentresult putdepartment(@RequestBody putdepartmentpost pp) throws SQLException, ClassNotFoundException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putdepartmentresult result = new putdepartmentresult();

        try {
            ps = conn.prepareStatement("INSERT INTO department(departmentname) values (?)");
            ps.setString(1,pp.getDepartmentname());
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
