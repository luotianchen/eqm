package start.putmatlcoderules;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;

@Controller
@CrossOrigin
public class putmatlcoderules {                                                     //设置材料代码规则（每次刷新代码规则表的那一条记录）
    @RequestMapping(value = "putmatlcoderules")
    public @ResponseBody putmatlcoderulesresult putmatlcoderules(@RequestBody putmatlcoderulespost pp) throws ClassNotFoundException, SQLException, ParseException {          //生存单位，在单位名称内
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putmatlcoderulesresult result = new putmatlcoderulesresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlcoderules WHERE id = 1");
            rs = ps.executeQuery();
            if(!rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO matlcoderules(id) VALUES (1)");
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
            }

            ps = conn.prepareStatement("UPDATE matlcoderules SET indexx=?,plank=?,pipe=?,flange=?,head=?,welding=? WHERE id=1");
            ps.setInt(1,pp.getIndex());
            ps.setString(2,pp.getPlank());
            ps.setString(3,pp.getPipe());
            ps.setString(4,pp.getFlange());
            ps.setString(5,pp.getHead());
            ps.setString(6,pp.getWelding());
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
