package start.searchweldingrecord;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class searchweldingrecord {                                      //焊接记录查询
    @RequestMapping(value = "searchweldingrecord")
    public @ResponseBody searchweldingrecordresult searchweldingrecord(@RequestBody searchweldingrecordpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchweldingrecordresult result = new searchweldingrecordresult();
        ArrayList<searchweldingrecorddata> as = new ArrayList<searchweldingrecorddata>();
        searchweldingrecorddata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM weldingrecord WHERE prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchweldingrecorddata();
                data.setProdno(rs.getString("prodno"));
                data.setDwgno(rs.getString("dwgno"));
                data.setProdname(rs.getString("prodname"));
                data.setWeldno(rs.getString("weldno"));
                data.setWeldevano(rs.getString("weldevano"));
                data.setWeldmethod(rs.getString("weldmethod"));
                data.setUsernote(rs.getString("usernote"));
                data.setWelddate(rs.getString("welddate"));
                data.setInspector(rs.getString("inspector"));
                data.setEntrustdate(rs.getString("entrustdate"));
                data.setNdtdate(rs.getString("ndtdate"));
                data.setUser(rs.getString("user"));
                as.add(data);
            }
            rs.close();
            ps.close();
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
