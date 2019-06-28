package start.getexitno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getexitno {                                        //查询所有出厂编号
    @RequestMapping(value = "getexitno",method = RequestMethod.GET)
    public @ResponseBody getexitnoresult getexitno() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getexitnoresult result = new getexitnoresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT distinct exitno FROM pregaumeatable");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("exitno"));
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
