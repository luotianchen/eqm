package start.getorderunit;

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
public class getorderunit {                                 //获取所有订货单位
    @RequestMapping(value = "getorderunit",method = RequestMethod.GET)
    public @ResponseBody getorderunitresult getorderunit() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getorderunitresult result = new getorderunitresult();
        ArrayList<getorderunitdata> as = new ArrayList<getorderunitdata>();
        getorderunitdata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM orderunit");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getorderunitdata();
                data.setName(rs.getString("orderunit"));
                data.setEname(rs.getString("eorderunit"));
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
