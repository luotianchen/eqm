package start.searchspecanddeinpre;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.WeakHashMap;

@CrossOrigin
@Controller
public class searchspecanddeinpre {                                 //查询受压元件表所有已审核规格和牌号
    @RequestMapping(value = "searchspecanddeinpre",method = RequestMethod.GET)
    public @ResponseBody searchspecanddeinpreresult searchspecanddeinpre() throws ClassNotFoundException, SQLException {
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
            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE status = 1");
            rs = ps.executeQuery();
            while (rs.next()){
                spec.add(rs.getString("spec"));
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
