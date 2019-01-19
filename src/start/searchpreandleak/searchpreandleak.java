package start.searchpreandleak;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchpreandleak {                                     //通过产品编号、试压部件、开具日期查询压力试验参数、泄漏试验参数
    @RequestMapping(value = "searchpreandleak")
    public @ResponseBody searchpreandleakresult searchpreandleak(@RequestBody searchpreandleakpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchpreandleakresult result = new searchpreandleakresult();
        searchpreandleakdata data = new searchpreandleakdata();
        searchpreandleakpre press = null;
        searchpreandleakleak leak = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart = ? AND datetype = ?");
            ps.setString(1,sp.getProdno());
            ps.setString(2,sp.getPpart());
            ps.setString(3,sp.getDatetype());
            rs = ps.executeQuery();
            if(rs.next()){
                press = new searchpreandleakpre();
                press.setDate(rs.getString("date"));
                press.setPgaugeno1(rs.getString("pgaugeno1"));
                press.setPgaugeno2(rs.getString("pgaugeno2"));
                press.setDewelltime(rs.getInt("dewelltime"));
                press.setCircutemp(rs.getInt("circutemp"));
                press.setMediatemp(rs.getInt("mediatemp"));
                press.setDated(rs.getString("dated"));
                press.setTestmedia(rs.getString("testmedia"));
                rs.close();
                ps.close();
                ps = conn.prepareStatement("SELECT * FROM leakagetest WHERE prodno = ? AND ppart = ? AND datetype = ?");
                ps.setString(1,sp.getProdno());
                ps.setString(2,sp.getPpart());
                ps.setString(3,sp.getDatetype());
                rs = ps.executeQuery();
                if(rs.next()){
                    leak = new searchpreandleakleak();
                    leak.setDate(rs.getString("date"));
                    leak.setPgaugeno1(rs.getString("pgaugeno1"));
                    leak.setPgaugeno2(rs.getString("pgaugeno2"));
                    leak.setDewelltime(rs.getInt("dewelltime"));
                    leak.setCircutemp(rs.getInt("circutemp"));
                    leak.setMediatemp(rs.getInt("mediatemp"));
                    leak.setDated(rs.getString("dated"));
                    leak.setTestmedia(rs.getString("testmedia"));
                }
                rs.close();
                ps.close();
                data.setPress(press);
                data.setLeak(leak);
                result.setData(data);
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
