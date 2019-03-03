package start.getdwgnoaudited;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getdwgnoaudited {                                      //获取所有已审核图号
    @RequestMapping(value = "getdwgnoaudited")
    public @ResponseBody getdwgnoauditedresult getdwgnoaudited() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getdwgnoauditedresult result  = new getdwgnoauditedresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE audit = 1");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("dwgno"));
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
