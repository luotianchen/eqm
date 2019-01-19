package start.changeprenotiformstatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changeprenotiformstatus {                                      //提交试压通知单审核
    @RequestMapping(value = "changeprenotiformstatus")
    public @ResponseBody changeprenotiformstatusresult changeprenotiformstatus(@RequestBody changeprenotiformstatuspost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changeprenotiformstatusresult result = new changeprenotiformstatusresult();

        int ppart_id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ?");
            ps.setString(1,cp.getPpart());
            rs = ps.executeQuery();
            if(rs.next()){
                ppart_id = rs.getInt("id");
            }
            rs.close();
            ps.close();
            ps = conn.prepareStatement("UPDATE prenotiform SET status = ? WHERE prodno = ? AND presstestp_id_ppart1 = ?");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getProdno());
            ps.setInt(3,ppart_id);
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
