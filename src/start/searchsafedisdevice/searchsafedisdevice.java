package start.searchsafedisdevice;

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
public class searchsafedisdevice {                                          //根据图号查询安全泄放装置
    @RequestMapping(value = "searchsafedisdevice")
    public @ResponseBody searchsafedisdeviceresult searchsafedisdevice(@RequestBody searchsafedisdevicepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchsafedisdeviceresult result = new searchsafedisdeviceresult();
        ArrayList<searchsafedisdevicedata> as = new ArrayList<searchsafedisdevicedata>();
        searchsafedisdevicedata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM safedisdevice WHERE dwgno = ? AND status = ?");
            ps.setString(1,sp.getDwgno());
            ps.setInt(2,sp.getStatus());
            rs= ps.executeQuery();
            while (rs.next()){
                data = new searchsafedisdevicedata();
                data.setName(rs.getString("name"));
                data.setModel(rs.getString("model"));
                data.setQty(rs.getString("qty"));
                data.setSpec(rs.getString("spec"));
                data.setMfunit(rs.getString("mfunit"));
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
