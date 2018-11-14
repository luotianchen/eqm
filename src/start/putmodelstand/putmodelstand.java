package start.putmodelstand;

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
public class putmodelstand {                                                        //添加型号标准
    @RequestMapping(value = "putmodelstand")
    public @ResponseBody putmodelstandresult putmodelstand(@RequestBody putmodelstandpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        putmodelstandresult result=new putmodelstandresult();

        try{
            ps=conn.prepareStatement("INSERT INTO modelstand (modelstand)values(?)");
            ps.setString(1,pp.getModelstand());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        return result;
    }
}
