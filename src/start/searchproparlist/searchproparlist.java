package start.searchproparlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchproparlist {                                         //根据图号查产品参数（查询总图号）
    @RequestMapping(value = "searchproparlist")
    public @ResponseBody searchproparlistresult searchproparlist(@RequestBody searchproparlistpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchproparlistresult result = new searchproparlistresult();
        searchproparlistdata data = null;

        int prodname_id=0;

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = ?");
            ps.setString(1,sp.getDwgno());
            ps.setInt(2,sp.getStatus());
            rs=ps.executeQuery();
            if(rs.next()){
                data = new searchproparlistdata();
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

                prodname_id = rs.getInt("productname_id_prodname");

                rs.close();
                ps.close();

                ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
                ps.setInt(1,prodname_id);
                rs = ps.executeQuery();
                while (rs.next()){
                    data.setProdname(rs.getString("prodname"));
                }
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
