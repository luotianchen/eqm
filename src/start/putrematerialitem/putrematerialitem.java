package start.putrematerialitem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putrematerialitem {                                //产品复验委托提交
    @RequestMapping(value = "putrematerialitem")
    public @ResponseBody putrematerialitemresult putrematerialitem(@RequestBody putrematerialitempost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putrematerialitemresult result = new putrematerialitemresult();

        try {
            ps = conn.prepareStatement("INSERT INTO rematerialitem(codedmarking,why," +
                    "forceperformance,chemicalcomposition,user,date) VALUES (?,?,?,?,?,?)");
            ps.setString(1,pp.getCodedmarking());
            ps.setString(2,pp.getExplain());
            ps.setString(3,pp.getForceperformance());
            ps.setString(4,pp.getChemicalcomposition());
            ps.setString(5,pp.getUser());
            ps.setDate(6,new java.sql.Date(new java.util.Date().getTime()));
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
