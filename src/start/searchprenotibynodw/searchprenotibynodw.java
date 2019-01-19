package start.searchprenotibynodw;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchprenotibynodw {                                          //通过产品编号、图号查询试压通知单
    @RequestMapping(value = "searchprenotibynodw")
    public @ResponseBody searchprenotibynodwresult searchprenotibynodw(@RequestBody searchprenotibynodwpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchprenotibynodwresult result = new searchprenotibynodwresult();
        searchprenotibynodwdata data = null;

        String sql = "SELECT * FROM prenotiform WHERE dwgno = ? AND presstestp_id_ppart1 = ? ";
        int ppart_id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ?");
            ps.setString(1,sp.getPpart());
            rs = ps.executeQuery();
            if(rs.next()){
                ppart_id = rs.getInt("id");
            }
            rs.close();
            ps.close();

            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ?";
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1,sp.getDwgno());
            ps.setInt(2,ppart_id);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                ps.setString(3,sp.getProdno());
            }
            rs = ps.executeQuery();
            if(rs.next()){
                data = new searchprenotibynodwdata();
                data.setProdno(rs.getString("prodno"));
                data.setDwgno(rs.getString("dwgno"));
                ppart_id = rs.getInt("presstestp_id_ppart1");
                data.setDated1(rs.getString("dated1"));
                data.setDated2(rs.getString("dated2"));
                data.setDated3(rs.getString("dated3"));
                data.setTestmedia(rs.getString("testmedia"));
                data.setEtestmedia(rs.getString("testmedia"));
                data.setClcontent(rs.getString("clcontent"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM presstestp WHERE id = ?");
            ps.setInt(1,ppart_id);
            rs = ps.executeQuery();
            if(rs.next()){
                data.setPpart(rs.getString("presstestp"));
                data.setEppart(rs.getString("ename"));
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
