package start.getuserform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

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
                data.setRole(rs.getInt("role_id"));
                data.setRole2(rs.getInt("role2_id"));
                data.setRole3(rs.getInt("role3_id"));
                data.setRole4(rs.getInt("role4_id"));
                data.setRole5(rs.getInt("role5_id"));
                data.setUsername(rs.getString("username"));
                data.setSign(rs.getString("signature"));
                data.setEmail(rs.getString("email"));
                data.setNote(rs.getString("note"));
                ag.add(data);
            }
            rs.close();
            ps.close();
            Collections.reverse(ag);                                          //将list倒序
            result.setData(ag);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }

        return result;
    }
}
