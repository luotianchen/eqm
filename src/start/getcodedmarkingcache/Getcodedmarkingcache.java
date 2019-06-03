package start.getcodedmarkingcache;

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
public class Getcodedmarkingcache {                                  //查询所有已审核入库编号
    @RequestMapping(value = "getcodedmarkingcache")
    public @ResponseBody
    getcodedmarkingcacheresult getcodedmarkingcache(@RequestBody getcodedmarkingcachepost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getcodedmarkingcacheresult result = new getcodedmarkingcacheresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM putmaterialcache WHERE status = 1 AND codedmarking LIKE ? limit 0,200");
            ps.setString(1,gp.getCodedmarking()+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("codedmarking"));
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
