package start.searchprenotiform;

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
public class searchprenotiform {                                        //试压通知单查询
    @RequestMapping(value = "searchprenotiform")
    public @ResponseBody searchprenotiformresult searchprenotiform(@RequestBody searchprenotiformpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        searchprenotiformresult result = new searchprenotiformresult();
        searchprenotiformdata data = null;
        ArrayList<searchprenotiformdata> as =new ArrayList<searchprenotiformdata>();

        int ppart_id = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE status = ? AND prodno = ?");
                ps.setInt(1,sp.getStatus());
                ps.setString(2,sp.getProdno());
            }else {
                ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE status = ?");
                ps.setInt(1,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchprenotiformdata();
                data.setProdno(rs.getString("prodno"));
                data.setDwgno(rs.getString("dwgno"));
                ppart_id = rs.getInt("presstestp_id_ppart1");
                data.setDated1(rs.getString("dated1"));
                data.setDated2(rs.getString("dated2"));
                data.setDated3(rs.getString("dated3"));
                data.setTestmedia(rs.getString("testmedia"));
                data.setEtestmedia(rs.getString("etestmedia"));
                data.setClcontent(rs.getString("clcontent"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));

                ps1 = conn.prepareStatement("SELECT * FROM presstestp WHERE id = ?");
                ps1.setInt(1,ppart_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data.setPpart(rs1.getString("presstestp"));
                    data.setEppart(rs1.getString("ename"));
                }
                rs1.close();
                ps1.close();

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
