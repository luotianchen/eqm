package start.putvacuum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putvacuum {                                    //提交真空参数
    @RequestMapping(value = "putvacuum")
    public @ResponseBody putvacuumresult putvacuum(@RequestBody putvacuumpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putvacuumresult result = new putvacuumresult();
        java.util.Date d = new java.util.Date();
        java.sql.Date time = new java.sql.Date(d.getTime());

        try {
            ps = conn.prepareStatement("INSERT INTO vacuumparameters (prodno,initnum,statnum," +
                    "initpa,statpa,htcurrent,initdate,enddate," +
                    "sealvacu,sealdate,testtemp,sealtemp," +
                    "vacuop,leakoutrate,user,date) VALUES (?,?,?," +
                    "?,?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setInt(2,pp.getInitnum());
            ps.setInt(3,pp.getStatnum());
            ps.setInt(4,pp.getInitpa());
            ps.setInt(5,pp.getStatpa());
            ps.setInt(6,pp.getHtcurrent());
            ps.setString(7,pp.getInitdate());
            ps.setString(8,pp.getEnddate());
            ps.setInt(9,pp.getSealvacu());
            ps.setString(10,pp.getSealdate());
            ps.setInt(11,pp.getTesttemp());
            ps.setInt(12,pp.getSealtemp());
            ps.setString(13,pp.getVacuop());
            ps.setString(14,pp.getLeakoutrate());
            ps.setString(15,pp.getUser());
            ps.setDate(16,new java.sql.Date(new java.util.Date().getTime()));
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
