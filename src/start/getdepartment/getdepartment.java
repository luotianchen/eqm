package start.getdepartment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getdepartment {                                            //获取所有部门
    @RequestMapping(value = "getdepartment",method = RequestMethod.GET)
    public @ResponseBody getdepartmentresult getdepartment() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getdepartmentresult result = new getdepartmentresult();
        getdepartmentdata data = null;
        ArrayList<getdepartmentdata> ag = new ArrayList<getdepartmentdata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM department");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getdepartmentdata();
                data.setDepartment(rs.getInt("id"));
                data.setDepartmentname(rs.getString("departmentname"));
                ag.add(data);
            }
            rs.close();
            ps.close();
            result.setData(ag);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;

    }
}
