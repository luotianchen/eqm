package start.getroll;

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
public class getroll {                              //获取所有角色
    @RequestMapping(value = "getroll",method = RequestMethod.GET)
    public @ResponseBody getrollresult getroll() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getrollresult result = new getrollresult();
        getrolldata data = null;
        ArrayList<getrolldata> ag = new ArrayList<getrolldata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM roll");
            rs= ps.executeQuery();
            while (rs.next()){
                data = new getrolldata();
                data.setRoll(rs.getInt("id"));
                data.setRollname(rs.getString("rollname"));
                data.setDepartment(rs.getInt("department_id"));
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
