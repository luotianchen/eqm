package start.putsafedisdevicecache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class Putsafedisdevicecache {                                         //提交安全泄放装置信息缓冲
    @RequestMapping(value = "putsafedisdevicecache")
    public @ResponseBody
    putsafedisdevicecacheresult putsafedisdevicecache(@RequestBody putsafedisdevicecachepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putsafedisdevicecacheresult result = new putsafedisdevicecacheresult();

        try {
            ps = conn.prepareStatement("DELETE FROM safedisdevicecache WHERE status = 1");
            ps.executeUpdate();
            ps.close();

            for(int i = 0;i<pp.getData().size();i++){
                ps = conn.prepareStatement("INSERT INTO safedisdevicecache(dwgno,name,model,qty,spec) VALUES (?,?,?,?,?) ");
                ps.setString(1,pp.getDwgno());
                ps.setString(2,pp.getData().get(i).getName());
                ps.setString(3,pp.getData().get(i).getModel());
                ps.setString(4,pp.getData().get(i).getQty());
                ps.setString(5,pp.getData().get(i).getSpec());
                ps.executeUpdate();
                ps.close();
            }
            result.setResult("success");
        }catch (Exception E){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
