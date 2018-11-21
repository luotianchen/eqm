package start.putsupplier;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CrossOrigin
@Controller
public class putsupplier {                                                          //添加供货单位
    @RequestMapping(value = "putsupplier")
    public @ResponseBody putsupplierresult putsupplier(@RequestBody putsupplierpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        putsupplierresult result = new putsupplierresult();

        try{
            ps=conn.prepareStatement("INSERT INTO supplier (supplier)values(?)");
            ps.setString(1,pp.getSupplier());
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
