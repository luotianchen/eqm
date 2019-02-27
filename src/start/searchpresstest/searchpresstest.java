package start.searchpresstest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class searchpresstest {                                      //压力试验通知单
    @RequestMapping(value = "searchpresstest")
    public @ResponseBody searchpresstestresult searchpresstest(@RequestBody searchpresstestpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchpresstestresult result = new searchpresstestresult();
        searchpresstestdata data = new searchpresstestdata();
        ArrayList<String> pttype=new ArrayList<String>();
        ArrayList<String> name=new ArrayList<String>();
        ArrayList<String> depress=new ArrayList<String>();
        ArrayList<String> detemp=new ArrayList<String>();
        ArrayList<String> wmedia=new ArrayList<String>();
        ArrayList<String> testpress=new ArrayList<String>();

        int dwgno_id=0;
        int prodname_id=0;

        try {
            ps=conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno=?");
            ps.setString(1,sp.getProdno());
            rs=ps.executeQuery();
            while (rs.next()){
                dwgno_id=rs.getInt("proparlist_id_dwgno");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM proparlist WHERE id=?");
            ps.setInt(1,dwgno_id);
            rs=ps.executeQuery();
            while (rs.next()){
                data.setDwgno(rs.getString("dwgno"));
                prodname_id=rs.getInt("productname_id_prodname");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM productname WHERE id=?");
            ps.setInt(1,prodname_id);
            rs=ps.executeQuery();
            while (rs.next()){
                data.setProdname(rs.getString("prodname"));
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno=?");
            ps.setString(1,data.getDwgno());
            rs=ps.executeQuery();
            while (rs.next()){
                pttype.add(rs.getString("pttype"));
                name.add(rs.getString("name"));
                depress.add(rs.getString("depress"));
                detemp.add(rs.getString("detemp"));
                wmedia.add(rs.getString("wmedia"));
                testpress.add(rs.getString("testpress"));
            }
            rs.close();
            ps.close();
            for (int i = 0; i < pttype.size() - 1; i++) {
                for (int q = pttype.size() - 1; q > i; q--) {
                    if (pttype.get(q).equals(pttype.get(i))) {
                        pttype.remove(q);
                    }
                }
            }
            for (int i = 0; i < name.size() - 1; i++) {
                for (int q = name.size() - 1; q > i; q--) {
                    if (name.get(q).equals(name.get(i))) {
                        name.remove(q);
                    }
                }
            }
            for (int i = 0; i < depress.size() - 1; i++) {
                for (int q = depress.size() - 1; q > i; q--) {
                    if (depress.get(q).equals(depress.get(i))) {
                        depress.remove(q);
                    }
                }
            }
            for (int i = 0; i < detemp.size() - 1; i++) {
                for (int q = detemp.size() - 1; q > i; q--) {
                    if (detemp.get(q).equals(detemp.get(i))) {
                        detemp.remove(q);
                    }
                }
            }
            for (int i = 0; i < wmedia.size() - 1; i++) {
                for (int q = wmedia.size() - 1; q > i; q--) {
                    if (wmedia.get(q).equals(wmedia.get(i))) {
                        wmedia.remove(q);
                    }
                }
            }
            for (int i = 0; i < testpress.size() - 1; i++) {
                for (int q = testpress.size() - 1; q > i; q--) {
                    if (testpress.get(q).equals(testpress.get(i))) {
                        testpress.remove(q);
                    }
                }
            }
            data.setPttype(pttype);
            data.setName(name);
            data.setDepress(depress);
            data.setDetemp(detemp);
            data.setWmedia(wmedia);
            data.setTestpress(testpress);

            ps=conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno=?");
            ps.setString(1,sp.getProdno());
            rs=ps.executeQuery();
            while (rs.next()){
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
                data.setDate(rs.getString("date"));
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
