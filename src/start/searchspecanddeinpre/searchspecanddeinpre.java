package start.searchspecanddeinpre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.WeakHashMap;

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
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchspecanddeinpreresult result = new searchspecanddeinpreresult();
        searchspecanddeinpredata data = new searchspecanddeinpredata();
        ArrayList<String> designation = new ArrayList<String>();
        ArrayList<String> spec = new ArrayList<String>();


        try {
            ps = conn.prepareStatement("SELECT * FROM pressurepartscache WHERE status = 1 AND prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                spec.add(rs.getString("spec"));
                for (int i = 0; i < spec.size() - 1; i++) {
                    for (int z = spec.size() - 1; z > i; z--) {
                        if (spec.get(z).equals(spec.get(i))) {
                            spec.remove(z);
                        }
                    }
                }

                ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                ps1.setInt(1,rs.getInt("contraststand_id_designation"));
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    designation.add(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();

            for (int i = 0; i < spec.size() - 1; i++) {
                for (int z = spec.size() - 1; z > i; z--) {
                    if (spec.get(z).equals(spec.get(i))) {
                        spec.remove(z);
                    }
                }
            }

            for (int i = 0; i < designation.size() - 1; i++) {
                for (int z = designation.size() - 1; z > i; z--) {
                    if (designation.get(z).equals(designation.get(i))) {
                        designation.remove(z);
                    }
                }
            }

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
