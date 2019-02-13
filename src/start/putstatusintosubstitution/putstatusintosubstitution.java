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
        String sql = null;
        int num = 1;

        java.util.Date date=new java.util.Date();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
            ps.setString(1,pp.getAudit());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                if(pp.getDesign_status()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET design_status = ?,design_note=?,design_username=?,design_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET design_status = ?,design_username=?,design_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getDesign_status());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        ps.setString(num++,pp.getDesign_note());
                    }
                    ps.setString(num++,pp.getDesign_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getMatl_status()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET matl_status = ?,matl_note=?,matl_username=?,matl_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET matl_status = ?,matl_username=?,matl_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getMatl_status());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getMatl_note());
                    }
                    ps.setString(num++,pp.getMatl_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getWelding_status()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET welding_status = ?,welding_note=?,welding_username=?,welding_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET welding_status = ?,welding_username=?,welding_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getWelding_status());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getWelding_note());
                    }
                    ps.setString(num++,pp.getWelding_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getProcess_status()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET process_status = ?,process_note=?,process_username=?,process_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET process_status = ?,process_username=?,process_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getProcess_status());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getProcess_note());
                    }
                    ps.setString(num++,pp.getProcess_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getInspection_status()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET inspection_status = ?,inspection_note=?,inspection_username=?,inspection_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET inspection_status = ?,inspection_username=?,inspection_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getInspection_status());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getInspection_note());
                    }
                    ps.setString(num++,pp.getInspection_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getStatus_b()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET status_b = ?,b_note=?,b_username=?,b_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET status_b = ?,b_username=?,b_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getStatus_b());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getB_note());
                    }
                    ps.setString(num++,pp.getB_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
                }
                if(pp.getStatus_c()!=0){
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))){
                        sql = "UPDATE matlsubstitution SET status_c = ?,c_note=?,c_username=?,c_date=? WHERE audit=?";
                    }else {
                        sql = "UPDATE matlsubstitution SET status_c = ?,c_username=?,c_date=? WHERE audit=?";
                    }
                    ps = conn.prepareStatement(sql);
                    ps.setInt(num++,pp.getStatus_c());
                    if(!(pp.getDesign_note()==null || pp.getDesign_note().equals(""))) {
                        ps.setString(num++, pp.getC_note());
                    }
                    ps.setString(num++,pp.getC_username());
                    ps.setDate(num++,new java.sql.Date(date.getTime()));
                    ps.setString(num++,pp.getAudit());
                    ps.executeUpdate();
                    ps.close();
                    sql = null;
                    num = 1;
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
