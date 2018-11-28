package start.putrole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putrole {                                  //增加角色
    @RequestMapping(value = "putrole")
    public @ResponseBody
    putroleresult putrole(@RequestBody putrolepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putroleresult result = new putroleresult();

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

            ps = conn.prepareStatement("INSERT INTO role(rolename,department_id) values (?,?)");
            ps.setString(1,pp.getRolename());
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