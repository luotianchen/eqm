package start.searchledgerbynotedate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class searchledgerbynotedate {                                           //根据note和时间查询计量台台账数据
    @RequestMapping(value = "searchledgerbynotedate")
    public @ResponseBody searchledgerbynotedateresult searchledgerbynotedate(@RequestBody searchledgerbynotedatepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        searchledgerbynotedateresult result = new searchledgerbynotedateresult();
        searchledgerbynotedatedata data = null;
        ArrayList<searchledgerbynotedatedata> as = new ArrayList<searchledgerbynotedatedata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM devicename WHERE note = ?");
            ps.setString(1,sp.getNote());
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE gaugename = ? AND managlevel = ? AND status = 1 AND (? BETWEEN calibdate AND recalibdate)");
                ps1.setString(1,rs.getString("name"));
                ps1.setString(2,rs.getString("kind"));
                ps1.setDate(3,new java.sql.Date(sdf.parse(sp.getDate()).getTime()));
            }
            rs.close();
            ps.close();


        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;

    }
}
