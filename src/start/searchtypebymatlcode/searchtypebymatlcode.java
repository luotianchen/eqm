package start.searchtypebymatlcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class searchtypebymatlcode {                                         //通过材料代码查询材料类别
    @RequestMapping(value = "searchtypebymatlcode")
    public @ResponseBody searchtypebymatlcoderesult searchtypebymatlcode(@RequestBody searchtypebymatlcodepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchtypebymatlcoderesult result = new searchtypebymatlcoderesult();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlcoderules WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString("plank").charAt(0) == sp.getMatlcode().charAt(0)){
                    result.setData(1);
                }else
                if(rs.getString("pipe").charAt(0) == sp.getMatlcode().charAt(0)){
                    result.setData(2);
                }else
                if(rs.getString("flange").charAt(0) == sp.getMatlcode().charAt(0)){
                    result.setData(3);
                }else
                if(rs.getString("head").charAt(0) == sp.getMatlcode().charAt(0)){
                    result.setData(4);
                }else
                if(rs.getString("welding").charAt(0) == sp.getMatlcode().charAt(0)){
                    result.setData(5);
                }else {
                    result.setData(0);
                }
                result.setResult("success");
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
