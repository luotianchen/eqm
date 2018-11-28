package start.getrole;

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
public class getrole {                              //获取所有角色
    @RequestMapping(value = "getrole",method = RequestMethod.GET)
    public @ResponseBody getroleresult getrole() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getroleresult result = new getroleresult();
        getroledata data = null;
        ArrayList<getroledata> ag = new ArrayList<getroledata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM role");
            rs= ps.executeQuery();
            while (rs.next()){
                data = new getroledata();
                if(rs.getInt("id")==0){
                    continue;
                }else {
                    data.setRole(rs.getInt("id"));
                    data.setRolename(rs.getString("rolename"));
                    data.setDepartment(rs.getInt("department_id"));
                    ag.add(data);
                }
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
