package start.getwmedia;

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
public class getwmedia {                                        //获取所有工作介质（新建的工作介质表）
    @RequestMapping(value = "getwmedia",method = RequestMethod.GET)
    public @ResponseBody getwmediaresult getwmedia() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getwmediaresult result = new getwmediaresult();
        getwmediadata data = null;
        ArrayList<getwmediadata> ag = new ArrayList<getwmediadata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM wmedia");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getwmediadata();
                data.setName(rs.getString("wmedia"));
                data.setEname(rs.getString("wmediaen"));
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
