package start.searchprodnobydwgno;

import com.mysql.cj.xdevapi.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchprodnobydwgno {
    @RequestMapping(value = "searchprodnobydwgno")
    public @ResponseBody
    searchprodnobydwgnoresult searchbydepartment(@RequestBody searchprodnobydwgnopost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchprodnobydwgno roles = null;
        ArrayList<String> as = new ArrayList<String>();
        searchprodnobydwgnoresult result = new searchprodnobydwgnoresult();

        try {
            ps = conn.prepareStatement("SELECT DISTINCT prodno FROM prenotiform WHERE dwgno = ?");
            ps.setString(1,sp.getDwgno());
            rs = ps.executeQuery();
            while(rs.next()){
                as.add(rs.getString("prodno"));
            }
            result.setResult("success");
            Collections.reverse(as);
            result.setData(as);
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();

        return result;
    }
}
