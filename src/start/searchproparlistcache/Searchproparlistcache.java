package start.searchproparlistcache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class Searchproparlistcache {                                         //根据图号查产品参数（查询总图号）缓冲
    @RequestMapping(value = "searchproparlistcache")
    public @ResponseBody
    searchproparlistcacheresult searchproparlistcache(@RequestBody searchproparlistcachepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchproparlistcacheresult result = new searchproparlistcacheresult();
        searchproparlistcachedata data = null;

        int prodname_id=0;

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlistcache WHERE dwgno = ?");
            ps.setString(1,sp.getDwgno());
            rs=ps.executeQuery();
            if(rs.next()){
                data = new searchproparlistcachedata();
                data.setDwgno1(rs.getString("dwgno1"));
                data.setDwgno2(rs.getString("dwgno2"));
                data.setType(rs.getString("type"));
                data.setMainstand(rs.getString("mainstand"));
                data.setMinorstand(rs.getString("minorstand"));
                data.setDeservicelife(rs.getString("deservicelife"));
                data.setWeight(rs.getInt("weight"));
                data.setChweight(rs.getInt("chweight"));
                data.setInstalltype(rs.getString("installtype"));
                data.setEinstalltype(rs.getString("einstalltype"));
                data.setSupptype(rs.getString("supptype"));
                data.setEsupptype(rs.getString("esupptype"));
                data.setInsultype(rs.getString("insultype"));
                data.setEinsultype(rs.getString("einsultype"));
                data.setNdetype(rs.getString("ndetype"));
                data.setNderatio(rs.getString("nderatio"));
                data.setCrytank(rs.getString("crytank"));
                data.setTestplatesitu(rs.getString("testplatesitu"));
                data.setHttype(rs.getString("httype"));
                data.setHttsetplate(rs.getString("httsetplate"));
                data.setHttemp(rs.getInt("httemp"));
                data.setSaferel(rs.getString("saferel"));
                data.setAnalyde(rs.getString("analyde"));
                data.setPvclass(rs.getDouble("pvclass"));
                data.setUnit(rs.getString("unit"));
                data.setProheight(rs.getString("proheight"));
                data.setLength(rs.getString("length"));
                data.setDesigndate(rs.getString("designdate"));
                data.setDeconame(rs.getString("deconame"));
                data.setProdname(rs.getString("productname_id_prodname"));

                rs.close();
                ps.close();
            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }
            result.setResult("success");
            result.setData(data);
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
