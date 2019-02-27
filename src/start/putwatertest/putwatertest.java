package start.putwatertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class putwatertest {                                         //水质检测提交
    @RequestMapping(value = "putwatertest")
    public @ResponseBody putwatertestresult putwatertest(@RequestBody putwatertestpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putwatertestresult result = new putwatertestresult();

        try {
            ps = conn.prepareStatement("INSERT INTO watertest(indate,name," +
                    "qty,roomno,stand,testcont," +
                    "testdate,testno,testrst,unit," +
                    "user,date) VALUES (?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?)");
            ps.setString(1,pp.getIndate());
            ps.setString(2,pp.getName());
            ps.setInt(3,pp.getQty());
            ps.setString(4,pp.getRoomno());
            ps.setString(5,pp.getStand());
            ps.setString(6,pp.getTestcont());
            ps.setString(7,pp.getTestdate());
            ps.setString(8,pp.getTestno());
            ps.setString(9,pp.getTestrst());
            ps.setString(10,pp.getUnit());
            ps.setString(11,pp.getUser());
            ps.setDate(12,new java.sql.Date(new java.util.Date().getTime()));
            ps.executeUpdate();
            ps.close();
            result.setResult("success");

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
