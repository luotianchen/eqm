package start.searchbyrole;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchbyrole {                                     //通过角色查询所属部门
    @RequestMapping(value = "searchbyrole")
    public @ResponseBody searchbyroleresult searchbyrole(@RequestBody searchbyrolepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchbyroleresult result = new searchbyroleresult();

        int department_id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM role WHERE rolename = ?");
            ps.setString(1,sp.getRolename());
            rs = ps.executeQuery();
            if(rs.next()){
                department_id = rs.getInt("department_id");
                result.setDepartmentid(department_id);
                rs.close();
                ps.close();
                ps = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
                ps.setInt(1,department_id);
                rs = ps.executeQuery();
                while (rs.next()){
                    result.setDepartmentname(rs.getString("departmentname"));
                }
                rs.close();
                ps.close();
                result.setResult("success");
            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
