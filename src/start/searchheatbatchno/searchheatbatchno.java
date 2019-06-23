package start.searchheatbatchno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchheatbatchno {                                            //查询炉批号
    @RequestMapping(value = "searchheatbatchno")
    public @ResponseBody searchheatbatchnoresult searchheatbatchno(@RequestBody searchheatbatchnopost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchheatbatchnoresult result = new searchheatbatchnoresult();
        ArrayList<String> data = new ArrayList<String>();

        try {
            if(!(sp.getHeatbatchno() == null || sp.getHeatbatchno().equals(""))){
                ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE status = ? AND heatbatchno LIKE ? GROUP BY heatbatchno ORDER BY id DESC limit 0, 200");                             //查询炉批号
                ps.setInt(1,sp.getStatus());
                ps.setString(2,"%"+sp.getHeatbatchno()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    data.add(rs.getString("heatbatchno"));

                }
                result.setData(data);
                result.setResult("success");
                rs.close();
                ps.close();
            }else {
                ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE status = ? GROUP BY heatbatchno ORDER BY id DESC limit 0, 200");                             //查询炉批号
                ps.setInt(1,sp.getStatus());
                rs=ps.executeQuery();
                while(rs.next()){
                    data.add(rs.getString("heatbatchno"));

                }
                result.setData(data);
                result.setResult("success");
                rs.close();
                ps.close();
            }



        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;


    }
}
