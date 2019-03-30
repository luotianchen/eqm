package start.searchdwgnoot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchdwgnoot {                                    //通过产品编号查询图号1、图号2
    @RequestMapping(value = "searchdwgnoot")
    public @ResponseBody searchdwgnootresult searchdwgnoot(@RequestBody searchdwgnootpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchdwgnootresult result = new searchdwgnootresult();
        searchdwgnootdata data = new searchdwgnootdata();

        String dwgno = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                dwgno = rs.getString("dwgno");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? and audit = 1");
            ps.setString(1,dwgno);
            rs = ps.executeQuery();
            if(rs.next()){
                data.setDwgno1(rs.getString("dwgno1"));
                data.setDwgno2(rs.getString("dwgno2"));
            }
            rs.close();
            ps.close();
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
