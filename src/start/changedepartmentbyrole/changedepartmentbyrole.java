package start.changedepartmentbyrole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CrossOrigin
@Controller
public class changedepartmentbyrole {                               //修改角色部门
    @RequestMapping(value = "changedepartmentbyrole")
    public @ResponseBody changedepartmentbyroleresult changedepartmentbyrole(@RequestBody changedepartmentbyrolepost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        changedepartmentbyroleresult result = new changedepartmentbyroleresult();

        try {
            ps = conn.prepareStatement("UPDATE role SET department_id = ? WHERE id = ?");
            ps.setInt(1,cp.getDepartment());
            ps.setInt(2,cp.getRole());
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
