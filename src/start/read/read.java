package start.read;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class read {                                             //读取消息
    @RequestMapping(value = "read")
    public @ResponseBody readresult read(@RequestBody readpost rp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        readresult result = new readresult();
        ArrayList<readdata> as = new ArrayList<readdata>();
        readdata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM message WHERE recieve_user = ? order by id desc");
            ps.setString(1,rp.getUsername());
            rs = ps.executeQuery();
            while (rs.next()){
                data = new readdata();
                data.setId(rs.getInt("id"));
                data.setRecieve_user(rp.getUsername());
                data.setTitle(rs.getString("title"));
                data.setContent(rs.getString("content"));
                data.setIsread(rs.getInt("isread"));
                data.setSend_type(rs.getString("send_type"));
                data.setSend_user(rs.getString("send_user"));
                data.setDate(rs.getString("date"));
                as.add(data);
                data = null;
            }
            rs.close();
            ps.close();
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;

    }
}
