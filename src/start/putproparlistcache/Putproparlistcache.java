package start.putproparlistcache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class Putproparlistcache {                                                //提交产品参数缓冲
    @RequestMapping(value = "putproparlistcache")
    public @ResponseBody
    putproparlistcacheresult putproparlistcache(@RequestBody putproparlistcachepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putproparlistcacheresult result = new putproparlistcacheresult();

        java.util.Date  date=new java.util.Date();

        int prodname_id = 0;

        try {
            ps = conn.prepareStatement("DELETE  FROM proparlistcache WHERE audit = 1");
            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO proparlistcache(user,dwgno,dwgno1,dwgno2,productname_id_prodname," +
                    "type,mainstand,minorstand,deservicelife,weight,chweight," +
                    "installtype,einstalltype,supptype,esupptype,insultype,einsultype," +
                    "ndetype,nderatio,crytank,testplatesitu,httype,httsetplate," +
                    "httemp,saferel,analyde,pvclass,unit,date," +
                    "proheight,length,designdate,deconame) values (?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getUser());
            ps.setString(2,pp.getDwgno());
            ps.setString(3,pp.getDwgno1());
            ps.setString(4,pp.getDwgno2());
            ps.setString(5,pp.getProdname());
            ps.setString(6,pp.getType());
            ps.setString(7,pp.getMainstand());
            ps.setString(8,pp.getMinorstand());
            ps.setString(9,pp.getDeservicelife());
            ps.setInt(10,pp.getWeight());
            ps.setInt(11,pp.getChweight());
            ps.setString(12,pp.getInstalltype());
            ps.setString(13,pp.getEinstalltype());
            ps.setString(14,pp.getSupptype());
            ps.setString(15,pp.getEsupptype());
            ps.setString(16,pp.getInsultype());
            ps.setString(17,pp.getEinsultype());
            ps.setString(18,pp.getNdetype());
            ps.setString(19,pp.getNderatio());
            ps.setString(20,pp.getCrytank());
            ps.setString(21,pp.getTestplatesitu());
            ps.setString(22,pp.getHttype());
            ps.setString(23,pp.getHttsetplate());
            ps.setInt(24,pp.getHttemp());
            ps.setString(25,pp.getSaferel());
            ps.setString(26,pp.getAnalyde());
            ps.setDouble(27,pp.getPvclass());
            ps.setString(28,pp.getUnit());
            ps.setDate(29,new Date(date.getTime()));
            ps.setString(30,pp.getProheight());
            ps.setString(31,pp.getLength());
            ps.setString(32,pp.getDesigndate());
            ps.setString(33,pp.getDeconame());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");


        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
