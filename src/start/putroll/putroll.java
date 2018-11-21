package start.putroll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putroll {                                  //增加角色
    @RequestMapping(value = "putroll")
    public @ResponseBody putrollresult putroll(@RequestBody putrollpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putrollresult result = new putrollresult();

        int department_id=0;

        try {
            ps= conn.prepareStatement("SELECT * FROM department WHERE departmentname = ?");
            ps.setString(1,pp.getDepartment());
            rs = ps.executeQuery();
            if(rs.next()){
                department_id = rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO roll(rollname,department_id) values (?,?)");
            ps.setString(1,pp.getRollname());
            ps.setInt(2,department_id);
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
