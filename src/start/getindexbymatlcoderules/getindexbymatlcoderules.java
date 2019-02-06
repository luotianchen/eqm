package start.getindexbymatlcoderules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getindexbymatlcoderules {                                                      //获取材料代码位数
    @RequestMapping(value = "getindexbymatlcoderules",method = RequestMethod.GET)
    public @ResponseBody getindexbymatlcoderulesresult getindexbymatlcoderules() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getindexbymatlcoderulesresult result = new getindexbymatlcoderulesresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlcoderules");
            rs = ps.executeQuery();
            if (rs.next()){
                result.setIndex(rs.getInt("indexx"));
                result.setResult("success");
                rs.close();
                ps.close();
            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
