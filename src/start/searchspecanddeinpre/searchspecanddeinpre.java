package start.searchspecanddeinpre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchspecanddeinpre {                                 //查询受压元件表所有已审核规格和牌号
    @RequestMapping(value = "searchspecanddeinpre")
    public @ResponseBody searchspecanddeinpreresult searchspecanddeinpre(@RequestBody searchspecanddeinprepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchspecanddeinpreresult result = new searchspecanddeinpreresult();
        searchspecanddeinpredata data = new searchspecanddeinpredata();
        ArrayList<String> designation = new ArrayList<String>();
        ArrayList<String> spec = new ArrayList<String>();


        try {
            ps = conn.prepareStatement("SELECT spec,contraststand_id_designation as designation FROM pressurepartscache WHERE status = 1 AND prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                if(rs.getString("spec")!=null) spec.add(rs.getString("spec"));
                if(rs.getString("designation")!=null) designation.add(rs.getString("designation"));
            }
            rs.close();
            ps.close();
            data.setDesignation(designation);
            data.setSpec(spec);
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
