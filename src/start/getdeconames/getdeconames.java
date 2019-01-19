package start.getdeconames;

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
public class getdeconames {                                 //查询所有设计单位
    @RequestMapping(value = "getdeconames",method = RequestMethod.GET)
    public @ResponseBody getdeconamesresult getdeconames() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getdeconamesresult result = new getdeconamesresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM designunit");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("deconame"));
            }
            rs.close();
            ps.close();
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
