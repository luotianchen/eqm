package start.getcodedmarking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getcodedmarking {                                  //查询所有已审核入库编号
    @RequestMapping(value = "getcodedmarking")
    public @ResponseBody getcodedmarkingresult getcodedmarking(@RequestBody getcodedmarkingpost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getcodedmarkingresult result = new getcodedmarkingresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE status = 1 AND codedmarking LIKE ? limit 0,200");
            ps.setString(1,gp.getCodedmarking()+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("codedmarking"));
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
