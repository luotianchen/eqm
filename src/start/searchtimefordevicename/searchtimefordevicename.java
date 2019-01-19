package start.searchtimefordevicename;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchtimefordevicename {                                              //通过名称和管理类别查询周期
    @RequestMapping(value = "searchtimefordevicename")
    public @ResponseBody searchtimefordevicenameresult searchtimefordevicename(@RequestBody searchtimefordevicenamepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchtimefordevicenameresult result = new searchtimefordevicenameresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM devicename WHERE name = ? AND kind = ?");
            ps.setString(1,sp.getGaugename());
            ps.setString(2,sp.getManaglevel());
            rs = ps.executeQuery();
            if(rs.next()){
                result.setData(rs.getString("time"));
                result.setResult("success");
            }else {
                result.setResult("fail");
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
