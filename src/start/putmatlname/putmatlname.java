package start.putmatlname;

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
public class putmatlname {                                      //添加材料名称
    @RequestMapping(value = "putmatlname")
    public @ResponseBody putmatlnameresult putmatlname(@RequestBody putmatlnamepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        putmatlnameresult result= new putmatlnameresult();

        try{
            ps=conn.prepareStatement("INSERT INTO matlname (matlname)values(?)");
            ps.setString(1,pp.getMatlname());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }

        return result;
    }
}
