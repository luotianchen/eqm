package start.putdeco;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putdeco {                                      //添加设计单位
    @RequestMapping(value = "putdeco")
    public @ResponseBody putdecoresult putdeco(@RequestBody putdecopost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putdecoresult result = new putdecoresult();

        try {
            ps = conn.prepareStatement("INSERT INTO designunit(deconame,edeconame," +
                    "delicense,time,orgcode,code) VALUES (?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getDeconame());
            ps.setString(2,pp.getEdeconame());
            ps.setString(3,pp.getDelicense());
            ps.setString(4,pp.getTime());
            ps.setString(5,pp.getOrgcode());
            ps.setString(6,pp.getCode());
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
