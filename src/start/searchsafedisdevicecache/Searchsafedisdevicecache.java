package start.searchsafedisdevicecache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class Searchsafedisdevicecache {                                          //根据图号查询安全泄放装置缓存
    @RequestMapping(value = "searchsafedisdevicecache")
    public @ResponseBody
    searchsafedisdevicecacheresult searchsafedisdevicecache(@RequestBody searchsafedisdevicecachepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchsafedisdevicecacheresult result = new searchsafedisdevicecacheresult();
        ArrayList<searchsafedisdevicecachedata> as = new ArrayList<searchsafedisdevicecachedata>();
        searchsafedisdevicecachedata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM safedisdevicecache WHERE dwgno = ?");
            ps.setString(1,sp.getDwgno());
            rs= ps.executeQuery();
            while (rs.next()){
                data = new searchsafedisdevicecachedata();
                data.setName(rs.getString("name"));
                data.setModel(rs.getString("model"));
                data.setQty(rs.getString("qty"));
                data.setSpec(rs.getString("spec"));
                as.add(data);
            }
            rs.close();
            ps.close();
            Collections.reverse(as);                                          //将list倒序
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
