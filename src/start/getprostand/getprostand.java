package start.getprostand;

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
public class getprostand {                                          //获取所有产品标准（新的产品标准表）
    @RequestMapping(value = "getprostand",method = RequestMethod.GET)
    public @ResponseBody getprostandresult getprostand() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getprostandresult result = new getprostandresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM prostand");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("stand"));
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
