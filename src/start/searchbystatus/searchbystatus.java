package start.searchbystatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchbystatus {                               //根据审核码查询记录
    @RequestMapping(value = "searchbystatus")
    public @ResponseBody searchbystatusresult searchbystatus(@RequestBody searchbystatuspost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        searchbystatusdata data =null;
        ArrayList<searchbystatusdata> as = new ArrayList<searchbystatusdata>();
        searchbystatusresult result = new searchbystatusresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
            ps.setString(1,sp.getAudit());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                rs = ps.executeQuery();
                while (rs.next()){
                    data = new searchbystatusdata();
                    data.setName(rs.getString("name"));
                    data.setDesignmatl(rs.getString("designmatl"));
                    data.setDesignspec(rs.getString("designspec"));
                    data.setSubstitutematl(rs.getString("substitutematl"));
                    data.setSubstitutespec(rs.getString("substitutespec"));
                    data.setType(rs.getString("type"));
                    data.setDesign_status(rs.getInt("design_status"));
                    data.setMatl_status(rs.getInt("matl_status"));
                    data.setWelding_status(rs.getInt("welding_status"));
                    data.setProcess_status(rs.getInt("process_status"));
                    data.setInspection_status(rs.getInt("inspection_status"));
                    data.setStatus_b(rs.getInt("status_b"));
                    data.setStatus_c(rs.getInt("status_c"));
                    data.setDesign_note(rs.getString("design_note"));
                    data.setMatl_note(rs.getString("matl_note"));
                    data.setWelding_note(rs.getString("welding_note"));
                    data.setProcess_note(rs.getString("process_note"));
                    data.setInspection_note(rs.getString("inspection_note"));
                    data.setB_note(rs.getString("b_note"));
                    data.setC_note(rs.getString("c_note"));
                    data.setDesign_date(rs.getString("design_date"));
                    data.setMatl_date(rs.getString("matl_date"));
                    data.setWelding_date(rs.getString("welding_date"));
                    data.setProcess_date(rs.getString("process_date"));
                    data.setInspection_date(rs.getString("inspection_date"));
                    data.setB_date(rs.getString("b_date"));
                    data.setC_date(rs.getString("c_date"));
                    data.setDesign_username(rs.getString("design_username"));
                    data.setMatl_username(rs.getString("matl_username"));
                    data.setWelding_username(rs.getString("welding_username"));
                    data.setProcess_username(rs.getString("process_username"));
                    data.setInspection_username(rs.getString("inspection_username"));
                    data.setB_username(rs.getString("b_username"));
                    data.setC_username(rs.getString("c_username"));
                    as.add(data);
                }
                rs.close();
                Collections.reverse(as);                                          //将list倒序
                result.setData(as);
                result.setResult("success");
            }else {
                rs.close();
                result.setResult("fail");
            }
            ps.close();
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }

}
