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
                ps = conn.prepareStatement("DELETE FROM rematerial WHERE codedmarking = ? AND status = 1 AND num = ?");
                ps.setString(1,cp.getCodedmarking());
                ps.setInt(2,cp.getNum());
                ps.executeUpdate();
                ps.close();

                ps = conn.prepareStatement("UPDATE rematerial SET status = ?,audit_user = ? WHERE codedmarking = ? AND status = 0 AND num = ?");
                ps.setInt(1,cp.getStatus());
                ps.setString(2,cp.getAudit_user());
                ps.setString(3,cp.getCodedmarking());
                ps.setInt(4,cp.getNum());
                ps.executeUpdate();
                ps.close();
            }else {
                ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1 AND num = ?");
                if(rs.next()){
                    rs.close();
                    ps.close();
                    ps = conn.prepareStatement("DELETE FROM rematerial WHERE codedmarking = ? AND status = 0 AND num = ?");
                    ps.setString(1,cp.getCodedmarking());
                    ps.setInt(2,cp.getNum());
                    ps.executeUpdate();
                    ps.close();
                }else {
                    rs.close();
                    ps.close();
                    ps = conn.prepareStatement("UPDATE rematerial SET status = ?,audit_user = ? WHERE codedmarking = ? AND status = 0 AND num = ?");
                    ps.setInt(1,-2);
                    ps.setString(2,cp.getAudit_user());
                    ps.setString(3,cp.getCodedmarking());
                    ps.setInt(4,cp.getNum());
                    ps.executeUpdate();
                    ps.close();
                }
            }


            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
