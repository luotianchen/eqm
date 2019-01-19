package start.putprodname;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putprodname {                                                      //添加产品名称
    @RequestMapping(value = "putprodname")
    public @ResponseBody putprodnameresult putprodname(@RequestBody putprodnamepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putprodnameresult result = new putprodnameresult();

        try {
            ps = conn.prepareStatement("INSERT INTO productname(prodname,ename) values (?,?)");
            ps.setString(1,pp.getProdname());
            ps.setString(2,pp.getEname());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
