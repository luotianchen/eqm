package start.searchprebystatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchprebystatus {                                //查询审核有关记录
    @RequestMapping(value = "searchprebystatus")
    public @ResponseBody searchprebystatusresult searchprebystatus(@RequestBody searchprebystatuspost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        searchprebystatusresult result = new searchprebystatusresult();
        searchprebystatusdata data=null;
        ArrayList<searchprebystatusdata> as = new ArrayList<searchprebystatusdata>();

        try {
            ps=conn.prepareStatement("SELECT * FROM pressureparts WHERE status=?");
            ps.setInt(1,sp.getStatus());
            rs=ps.executeQuery();
            while (rs.next()){
                data = new searchprebystatusdata();
                data.setAudit(rs.getString("audit"));
                data.setProdno(rs.getString("prodno"));
                data.setUpdatetime(sdf.format(rs.getTimestamp("updatetime")));
                as.add(data);
            }
            rs.close();
            ps.close();

            Collections.reverse(as);
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;

    }
}
