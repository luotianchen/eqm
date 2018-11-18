package start.getprodno;

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
public class getprodno {
    @RequestMapping(value = "getprodno" ,method = RequestMethod.GET)
    public @ResponseBody getprodnoresult getprodno() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        ArrayList<String> data =new ArrayList<String>();
        getprodnoresult result = new getprodnoresult();

        try {
            ps=conn.prepareStatement("SELECT * FROM promanparlist");
            rs=ps.executeQuery();
            while (rs.next()){
                data.add(rs.getString("prodno"));
            }
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        return result;
    }
}
