package start.changestatusforpredata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changestatusforpredata {                                   //提交产品试板数据审核
    @RequestMapping(value = "changestatusforpredata")
    public @ResponseBody changestatusforpredataresult changestatusforpredata(@RequestBody changestatusforpredatapost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changestatusforpredataresult result = new changestatusforpredataresult();

        try {
            ps = conn.prepareStatement("UPDATE productplatedata SET status = ? WHERE prodno = ?");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getProdno());
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
