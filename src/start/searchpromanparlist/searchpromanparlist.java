package start.searchpromanparlist;

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
public class searchpromanparlist {                              //产品制造参数查询
    @RequestMapping(value = "searchpromanparlist")
    public @ResponseBody searchpromanparlistresult searchpromanparlist(@RequestBody searchpromanparlistpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchpromanparlistresult result = new searchpromanparlistresult();
        ArrayList<searchpromanparlistdata> as = new ArrayList<searchpromanparlistdata>();
        searchpromanparlistdata data = null;

        String sql = "SELECT * FROM promanparlist WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ?";
            }
            ps = conn.prepareStatement(sql);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getProdno());
            }
            if(sp.getStatus() != -1){
                num = num + 1;
                ps.setInt(num,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchpromanparlistdata();
                data.setProdno(rs.getString("prodno"));
                ps1 = conn.prepareStatement("SELECT * FROM proparlist WHERE id = ?");
                ps1.setInt(1,rs.getInt("proparlist_id_dwgno"));
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    data.setDwgno(rs1.getString("dwgno"));
                }
                rs1.close();
                ps1.close();
                data.setOrderunit(rs.getString("orderunit"));
                data.setEorderunit(rs.getString("eorderunit"));
                data.setEcode(rs.getString("ecode"));
                data.setDealter(rs.getString("dealter"));
                data.setSubmatl(rs.getString("submatl"));
                data.setDocum(rs.getString("docum"));
                data.setDedate(rs.getString("dedate"));
                data.setBlankdate(rs.getString("blankdate"));
                data.setMatlretest(rs.getString("matlretest"));
                data.setSpecmatl(rs.getString("specmatl"));
                data.setOvermatl(rs.getString("overmatl"));
                data.setNwpq(rs.getString("nwpq"));
                data.setNprocess(rs.getString("nprocess"));
                data.setCopsitu(rs.getString("copsitu"));
                data.setExworkdate(rs.getString("exworkdate"));
                data.setAweldmaxangul(rs.getString("aweldmaxangul"));
                data.setBweldmaxangul(rs.getString("bweldmaxangul"));
                data.setAweldmaxalign(rs.getString("aweldmaxalign"));
                data.setBweldmaxalign(rs.getString("bweldmaxalign"));
                data.setWeldreinfs(rs.getString("weldreinfs"));
                data.setWeldreinfd(rs.getString("weldreinfd"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
                data.setDate(rs.getString("date"));
                data.setProheight(rs.getString("proheight"));
                data.setInnerdia(rs.getString("innerdia"));
                data.setRoundness(rs.getString("roundness"));
                data.setLength(rs.getString("length"));
                data.setStraightness(rs.getString("straightness"));
                data.setThick(rs.getString("thick"));
                data.setMinthickstand(rs.getString("minthickstand"));
                data.setMinthick(rs.getString("minthick"));
                data.setOutward(rs.getString("outward"));
                data.setConcave(rs.getString("concave"));
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
