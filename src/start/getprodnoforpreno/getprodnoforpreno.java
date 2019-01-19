package start.getprodnoforpreno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getprodnoforpreno {                                            //获取已审核试压参数表所有产品编号
    @RequestMapping(value = "getprodnoforpreno",method = RequestMethod.GET)
    public @ResponseBody getprodnoforprenoresult getprodnoforpreno() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getprodnoforprenoresult result = new getprodnoforprenoresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE status = 1");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("prodno"));
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
