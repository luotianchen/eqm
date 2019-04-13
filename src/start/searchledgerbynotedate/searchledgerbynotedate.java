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
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data = new searchledgerbynotedatedata();
                    data.setGaugename(rs1.getString("gaugename"));
                    data.setGaugeno(rs1.getString("gaugeno"));
                    data.setExitno(rs1.getString("exitno"));
                    data.setType(rs1.getString("type"));
                    data.setMeasrangemax(rs1.getString("measrangemax"));
                    data.setMeasrangemin(rs1.getString("measrangemin"));
                    data.setAccuclass(rs1.getString("accuclass"));
                    data.setMillunit(rs1.getString("millunit"));
                    data.setExitdate(rs1.getString("exitdate"));
                    data.setManaglevel(rs1.getString("managlevel"));
                    data.setCalibdate(rs1.getString("calibdate"));
                    data.setRecalibdate(rs1.getString("recalibdate"));
                    data.setCalibinterval(rs1.getString("calibinterval"));
                    data.setNote(rs1.getString("note"));
                    data.setSpecialist(rs1.getString("specialist").replaceAll("#","/"));
                    as.add(data);
                }
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            result.setResult("success");
            result.setData(as);

        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;

    }
}
