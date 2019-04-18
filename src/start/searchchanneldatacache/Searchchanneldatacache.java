package start.searchchanneldatacache;

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
public class Searchchanneldatacache {                                                //根据图号查通道数据缓冲
    @RequestMapping(value = "searchchanneldatacache")
    public @ResponseBody
    searchchanneldatacacheresult searchchanneldatacache(@RequestBody searchchanneldatacachepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchchanneldatacacheresult result = new searchchanneldatacacheresult();
        ArrayList<searchchanneldatacachedata> as = new ArrayList<searchchanneldatacachedata>();
        searchchanneldatacachedata data = null;

        try {
            ps = conn.prepareStatement("SELECT * FROM channeldatacache WHERE dwgno = ? ORDER BY tongdaoshu ASC");
            ps.setString(1,sp.getDwgno());
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchchanneldatacachedata();
                data.setName(rs.getString("name"));
                data.setEname(rs.getString("ename"));
                data.setVolume(rs.getString("volume"));
                data.setInnerdia(rs.getString("innerdia"));
                data.setShmatl1(rs.getString("shmatl1"));
                data.setShmatl2(rs.getString("shmatl2"));
                data.setShmatl3(rs.getString("shmatl3"));
                data.setShthick1(rs.getString("shthick1"));
                data.setShthick2(rs.getString("shthick2"));
                data.setShthick3(rs.getString("shthick3"));
                data.setLiningmatl(rs.getString("liningmatl"));
                data.setLiningthick(rs.getString("liningthick"));
                data.setWmedia(rs.getString("wmedia"));
                data.setHdthick1(rs.getString("hdthick1"));
                data.setHdthick2(rs.getString("hdthick2"));
                data.setMaxwpress(rs.getString("maxwpress"));
                data.setDepress(rs.getString("depress"));
                data.setDetemp(rs.getString("detemp"));
                data.setWpress(rs.getString("wpress"));
                data.setWtemp(rs.getString("wtemp"));
                data.setTestpress(rs.getString("testpress"));
                data.setLeaktest(rs.getString("leaktest"));
                data.setEleaktest(rs.getString("eleaktest"));
                data.setLeaktestp(rs.getString("leaktestp"));
                data.setPttype(rs.getString("pttype"));
                data.setEpttype(rs.getString("epttype"));
                as.add(data);
            }
            rs.close();
            ps.close();
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
