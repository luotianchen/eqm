package start.getuserform;

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
public class getuserform {                                                  //获取所有用户信息
    @RequestMapping(value = "getuserform" ,method = RequestMethod.GET)
    public @ResponseBody getuserformresult getuserform() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getuserformresult result = new getuserformresult();
        getuserformdata data= null;
        ArrayList<getuserformdata> ag = new ArrayList<getuserformdata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getuserformdata();
                data.setName(rs.getString("name"));
                data.setRoll(rs.getInt("roll_id"));
                data.setUsername(rs.getString("username"));
                ag.add(data);
            }
            rs.close();
            ps.close();
            result.setData(ag);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }

        return result;
    }
}
