package start.deleteproparlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class deleteproparlist {                                     //解除试压表图号与产品编号连接
    @RequestMapping(value = "deleteproparlist")
    public @ResponseBody deleteproparlistresult deleteproparlist(@RequestBody deleteproparlistpost dp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        deleteproparlistresult result = new deleteproparlistresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ? AND dwgno = ?");
            ps.setString(1,dp.getProdno());
            ps.setString(2,dp.getDwgno());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("DELETE FROM prenotiform WHERE prodno = ? AND dwgno = ?");
                ps.setString(1,dp.getProdno());
                ps.setString(2,dp.getDwgno());
                ps.executeUpdate();
                ps.close();

//                ps = conn.prepareStatement("DELETE FROM pretest WHERE prodno = ?");
//                ps.setString(1,dp.getProdno());
//                ps.executeUpdate();
//                ps.close();

//                ps = conn.prepareStatement("DELETE FROM leakagetest WHERE prodno = ?");
//                ps.setString(1,dp.getProdno());
//                ps.executeUpdate();
//                ps.close();
                result.setResult("success");
            }else {
                rs.close();
                ps.close();
                result.setResult("未查到数据");
            }

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
