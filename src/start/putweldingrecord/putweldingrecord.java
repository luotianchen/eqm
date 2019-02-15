package start.putweldingrecord;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class putweldingrecord {                                     //焊接记录提交
    @RequestMapping(value = "putweldingrecord")
    public @ResponseBody putweldingrecordresult putweldingrecord(@RequestBody putweldingrecordpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putweldingrecordresult result = new putweldingrecordresult();

        try {
            ps = conn.prepareStatement("INSERT INTO weldingrecord(prodno,dwgno,prodname,weldno," +
                    "weldevano,weldmethod,usernote,welddate," +
                    "inspector,entrustdate,ndtdate,user,date) VALUES (?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setString(2,pp.getDwgno());
            ps.setString(3,pp.getProdname());
            ps.setString(4,pp.getWeldno());
            ps.setString(5,pp.getWeldevano());
            ps.setString(6,pp.getWeldmethod());
            ps.setString(7,pp.getUsernote());
            ps.setString(8,pp.getWelddate());
            ps.setString(9,pp.getInspector());
            ps.setString(10,pp.getEntrustdate());
            ps.setString(11,pp.getNdtdate());
            ps.setString(12,pp.getUser());
            ps.setDate(13,new java.sql.Date(new java.util.Date().getTime()));
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
