package start.getwatertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getwatertest {                                         //水质检测查询
    @RequestMapping(value = "getwatertest",method = RequestMethod.GET)
    public @ResponseBody getwatertestresult getwatertest() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        getwatertestresult result = new getwatertestresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM watertest WHERE testdate = (SELECT max(testdate) FROM watertest)");
            rs = ps.executeQuery();
            if(rs.next()){
                result.setUnit(rs.getString("unit"));
                result.setIndate(rs.getString("indate"));
                result.setQty(rs.getInt("qty"));
                result.setName(rs.getString("name"));
                result.setTestno(rs.getString("testno"));
                result.setRoomno(rs.getString("roomno"));
                result.setTestcont(rs.getString("testcont"));
                result.setTestrst(rs.getString("testrst"));
                result.setStand(rs.getString("stand"));
                result.setTestdate(rs.getString("testdate"));
                result.setDate(rs.getString("date"));
                result.setUser(rs.getString("user"));
                result.setResult("success");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
