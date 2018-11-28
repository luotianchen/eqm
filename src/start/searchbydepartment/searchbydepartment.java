package start.searchbydepartment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchbydepartment {                                           //通过部门查询所有职位
    @RequestMapping(value = "searchbydepartment")
    public @ResponseBody searchbydepartmentresult searchbydepartment(@RequestBody searchbydepartmentpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchbydepartmentroles roles = null;
        ArrayList<searchbydepartmentroles> ar = new ArrayList<searchbydepartmentroles>();
        searchbydepartmentresult result = new searchbydepartmentresult();

        int department_id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE departmentname = ?");
            ps.setString(1,sp.getDepartmentname());
            rs = ps.executeQuery();
            if(rs.next()){
                department_id = rs.getInt("id");
                rs.close();
                ps.close();
                ps = conn.prepareStatement("SELECT * FROM role WHERE department_id = ?");
                ps.setInt(1,department_id);
                rs = ps.executeQuery();
                while (rs.next()){
                    roles = new searchbydepartmentroles();
                    roles.setRole(rs.getInt("id"));
                    roles.setRolename(rs.getString("rolename"));
                    ar.add(roles);
                }
                rs.close();
                ps.close();
                result.setRoles(ar);
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
