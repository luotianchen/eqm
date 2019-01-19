package start.putstatusintosubstitution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putstatusintosubstitution {                                        //提交代用审核
    @RequestMapping(value = "putstatusintosubstitution")
    public @ResponseBody putstatusintosubstitutionresult putstatusintosubstitution(@RequestBody putstatusintosubstitutionpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putstatusintosubstitutionresult result = new putstatusintosubstitutionresult();

        java.util.Date date=new java.util.Date();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
            ps.setString(1,pp.getAudit());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                if(pp.getDesign_status()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET design_status = ?,design_note=?,design_username=?,design_date=? WHERE audit=?");
                    ps.setInt(1,pp.getDesign_status());
                    ps.setString(2,pp.getDesign_note());
                    ps.setString(3,pp.getDesign_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getMatl_status()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET matl_status = ?,matl_note=?,matl_username=?,matl_date=? WHERE audit=?");
                    ps.setInt(1,pp.getMatl_status());
                    ps.setString(2,pp.getMatl_note());
                    ps.setString(3,pp.getMatl_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getWelding_status()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET welding_status = ?,welding_note=?,welding_username=?,welding_date=? WHERE audit=?");
                    ps.setInt(1,pp.getWelding_status());
                    ps.setString(2,pp.getWelding_note());
                    ps.setString(3,pp.getWelding_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getProcess_status()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET process_status = ?,process_note=?,process_username=?,process_date=? WHERE audit=?");
                    ps.setInt(1,pp.getProcess_status());
                    ps.setString(2,pp.getProcess_note());
                    ps.setString(3,pp.getProcess_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getInspection_status()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET inspection_status = ?,inspection_note=?,inspection_username=?,inspection_date=? WHERE audit=?");
                    ps.setInt(1,pp.getInspection_status());
                    ps.setString(2,pp.getInspection_note());
                    ps.setString(3,pp.getInspection_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getStatus_b()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET status_b = ?,b_note=?,b_username=?,b_date=? WHERE audit=?");
                    ps.setInt(1,pp.getStatus_b());
                    ps.setString(2,pp.getB_note());
                    ps.setString(3,pp.getB_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                }
                if(pp.getStatus_c()==1){
                    ps = conn.prepareStatement("UPDATE matlsubstitution SET status_c = ?,c_note=?,c_username=?,c_date=? WHERE audit=?");
                    ps.setInt(1,pp.getStatus_c());
                    ps.setString(2,pp.getC_note());
                    ps.setString(3,pp.getC_username());
                    ps.setDate(4,new java.sql.Date(date.getTime()));
                    ps.setString(5,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();

                }
                result.setResult("success");
            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
