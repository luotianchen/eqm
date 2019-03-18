package start.updatesafedisdevice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class updatesafedisdevice {                                  //更新已通过审核的安全泄放装置的制造单位
    @RequestMapping(value = "updatesafedisdevice")
    public @ResponseBody updatesafedisdeviceresult updatesafedisdevice(@RequestBody updatesafedisdevicepost up) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        updatesafedisdeviceresult result = new updatesafedisdeviceresult();

        try {
            ps = conn.prepareStatement("DELETE FROM safedisdevice WHERE status = 1 AND dwgno = ?");
            ps.setString(1,up.getDwgno());
            ps.executeUpdate();
            ps.close();

            for(int i = 0;i<up.getData().size();i++){
                ps = conn.prepareStatement("INSERT INTO safedisdevice(dwgno,name,model,qty,spec,mfunit,status) VALUES (?,?,?,?,?,?,1) ");
                ps.setString(1,up.getDwgno());
                ps.setString(2,up.getData().get(i).getName());
                ps.setString(3,up.getData().get(i).getModel());
                ps.setString(4,up.getData().get(i).getQty());
                ps.setString(5,up.getData().get(i).getSpec());
                ps.setString(6,up.getData().get(i).getMfunit());
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
