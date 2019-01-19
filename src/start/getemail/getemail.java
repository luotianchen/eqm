package start.getemail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getemail {                                         //设备计量台帐邮箱设置查询
    @RequestMapping(value = "getemail",method = RequestMethod.GET)
    public @ResponseBody getemailresult getemail() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getemailresult result = new getemailresult();
        getemaildata data = new getemaildata();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM email");
            rs = ps.executeQuery();
            while (rs.next()){
                data.setSystem_email(rs.getString("system_email"));
                data.setAuthorization_code(rs.getString("authorization_code"));
                as.add(rs.getString("tosend_email"));
            }
            rs.close();
            ps.close();
            data.setTosend_email(as);
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
