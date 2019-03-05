package start.searchdesbydec;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class searchdesbydec {                               //根据设计单位名称查询设计单位信息
    @RequestMapping(value = "searchdesbydec")
    public @ResponseBody searchdesbydecresult searchdesbydec(@RequestBody searchdesbydecpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchdesbydecresult result = new searchdesbydecresult();
        searchdesbydecdata data = new searchdesbydecdata();

        try {
            ps = conn.prepareStatement("SELECT * FROM designunit WHERE deconame = ?");
            ps.setString(1,sp.getDeconame());
            rs = ps.executeQuery();
            while (rs.next()){
                data.setEdeconame(rs.getString("edeconame"));
                data.setDelicense(rs.getString("delicense"));
                data.setTime(rs.getString("time"));
                data.setOrgcode(rs.getString("orgcode"));
                data.setCode(rs.getString("code"));
            }
            rs.close();
            ps.close();
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
