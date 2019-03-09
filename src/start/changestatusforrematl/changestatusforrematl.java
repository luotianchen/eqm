package start.changestatusforrematl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class changestatusforrematl {                                    //材料复验登记审核提交
    @RequestMapping(value = ("changestatusforrematl"))
    public @ResponseBody changestatusforrematlresult changestatusforrematl(@RequestBody changestatusforrematlpost cp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        changestatusforrematlresult result = new changestatusforrematlresult();

        try {
            if(cp.getStatus()==1){
                ps = conn.prepareStatement("UPDATE rematerial SET status = ?,audit_user = ? WHERE codedmarking = ? AND status = 1");
                ps.setInt(1,3);
                ps.setString(2,cp.getAudit_user());
                ps.setString(3,cp.getCodedmarking());
                ps.executeUpdate();
            }

            ps = conn.prepareStatement("UPDATE rematerial SET status = ?,audit_user = ? WHERE codedmarking = ? AND status = 0");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getAudit_user());
            ps.setString(3,cp.getCodedmarking());
            ps.executeUpdate();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
