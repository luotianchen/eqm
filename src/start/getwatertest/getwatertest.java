package start.getwatertest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

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
        ArrayList<getwatertestdata> as = new ArrayList<getwatertestdata>();
        getwatertestdata data = null;

        int i=0;

        try {
            ps = conn.prepareStatement("SELECT * FROM watertest ORDER BY testdate DESC ;");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getwatertestdata();
                data.setUnit(rs.getString("unit"));
                data.setIndate(rs.getString("indate"));
                data.setQty(rs.getInt("qty"));
                data.setName(rs.getString("name"));
                data.setTestno(rs.getString("testno"));
                data.setRoomno(rs.getString("roomno"));
                data.setTestcont(rs.getString("testcont"));
                data.setTestrst(rs.getString("testrst"));
                data.setStand(rs.getString("stand"));
                data.setTestdate(rs.getString("testdate"));
                data.setDate(rs.getString("date"));
                data.setUser(rs.getString("user"));
                as.add(data);
                i++;
                if(i==4){
                    break;
                }
            }
            rs.close();
            ps.close();
            result.setResult("success");
            result.setData(as);
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
