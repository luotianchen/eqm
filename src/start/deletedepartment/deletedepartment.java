package start.deletedepartment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class deletedepartment {                                             //删除部门
    @RequestMapping(value = "deletedepartment")
    public @ResponseBody deletedepartmentresult deletedepartment(@RequestBody deletedepartmentpost dp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        deletedepartmentresult result = new deletedepartmentresult();

        try {
            ps = conn.prepareStatement("DELETE FROM department WHERE id = ?");
            ps.setInt(1,dp.getDepartment());
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
