package start.searchpromanparlistcache;

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
public class Searchpromanparlistcache {                              //产品制造参数查询
    @RequestMapping(value = "searchpromanparlistcache")
    public @ResponseBody
    searchpromanparlistcacheresult searchpromanparlist(@RequestBody searchpromanparlistcachepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchpromanparlistcacheresult result = new searchpromanparlistcacheresult();
        ArrayList<searchpromanparlistcachedata> as = new ArrayList<searchpromanparlistcachedata>();
        searchpromanparlistcachedata data = null;

        String sql = "SELECT `id`, `prodno`, `orderunit`, `eorderunit`, `proparlist_id_dwgno`, `ecode`, `dealter`, `submatl`, `docum`, `dedate`, `blankdate`, `matlretest`, `specmatl`, `overmatl`, `nwpq`, `nprocess`, `copsitu`, `aweldmaxangul`, `bweldmaxangul`, `aweldmaxalign`, `bweldmaxalign`, `weldreinfs`, `weldreinfd`, `exworkdate`, (select `name` from userform where username = user) as `user`, `date`, (select `name` from userform where username = audit_user) as `audit_user`, `status`, `type`, `etype`, `proheight`, `innerdia`, `roundness`, `length`, `straightness`, `thick`, `minthickstand`, `minthick`, `outward`, `concave` FROM promanparlistcache WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ? ";
            }
            sql = sql + "AND status = 1";
            ps = conn.prepareStatement(sql);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getProdno());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchpromanparlistcachedata();
                data.setProdno(rs.getString("prodno"));
                data.setDwgno(rs.getString("proparlist_id_dwgno"));
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
                data = null;
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
