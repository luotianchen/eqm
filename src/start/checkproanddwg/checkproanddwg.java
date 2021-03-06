package start.checkproanddwg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class checkproanddwg {                                           //确定prodno和dwgno是否存在
    @RequestMapping(value = "checkproanddwg")
    public @ResponseBody checkproanddwgresult checkproanddwg(@RequestBody checkproanddwgpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        checkproanddwgresult result = new checkproanddwgresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno=?");
            ps.setString(1,cp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                result.setDwgno(rs.getString("dwgno"));
                result.setResult("success");
            }else {
                result.setResult("error");
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;
    }
}
