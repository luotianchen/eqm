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
            ps = conn.prepareStatement("SELECT * FROM weldingrecord WHERE prodno = ?");
            ps.setString(1,pp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                System.out.println(1);
                ps = conn.prepareStatement("DELETE FROM weldingrecord WHERE prodno = ?");
                ps.setString(1,pp.getProdno());
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
            }

            for (int i=0;i<pp.getData().size();i++){
                ps = conn.prepareStatement("INSERT INTO weldingrecord(prodno,dwgno,prodname,weldno," +
                        "weldevano,weldmethod,usernote,welddate," +
                        "inspector,entrustdate,ndtdate,user,date) VALUES (?,?,?,?," +
                        "?,?,?,?," +
                        "?,?,?,?,?)");
                ps.setString(1,pp.getProdno());
                ps.setString(2,pp.getDwgno());
                ps.setString(3,pp.getProdname());
                ps.setString(4,pp.getData().get(i).getWeldno());
                ps.setString(5,pp.getData().get(i).getWeldevano());
                ps.setString(6,pp.getData().get(i).getWeldmethod());
                ps.setString(7,pp.getData().get(i).getUsernote());
                ps.setString(8,pp.getData().get(i).getWelddate());
                ps.setString(9,pp.getData().get(i).getInspector());
                ps.setString(10,pp.getData().get(i).getEntrustdate());
                ps.setString(11,pp.getData().get(i).getNdtdate());
                ps.setString(12,pp.getUser());
                ps.setDate(13,new java.sql.Date(new java.util.Date().getTime()));
                ps.executeUpdate();
                ps.close();

            }
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
