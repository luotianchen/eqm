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
            ps = conn.prepareStatement("UPDATE productplatedata SET status = ?,audit_user = ? WHERE id = ?");
            ps.setInt(1,cp.getStatus());
            ps.setString(2,cp.getAudit_user());
            ps.setInt(3,cp.getId());
            ps.executeUpdate();
            ps.close();
            if(cp.getStatus()==1){
                ps = conn.prepareStatement("UPDATE productplatedata SET status = ? WHERE id != ? AND prodno=? AND specimenno=? AND status=?");
                ps.setInt(1,3);
                ps.setInt(2,cp.getId());
                ps.setString(3,cp.getProdno());
                ps.setString(4,cp.getSpecimenno());
                ps.setInt(5,cp.getStatus());
                ps.executeUpdate();
                ps.close();
                result.setResult("success");
            }else if(cp.getStatus()==2){
                result.setResult("success");
            }else {
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult("fail");
        }

        conn.close();
        return result;
    }
}
