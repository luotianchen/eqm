package start.searchproductplatedata;

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
public class searchproductplatedata {                                               //查询产品试板数据
    @RequestMapping(value = "searchproductplatedata")
    public @ResponseBody searchproductplatedataresult searchproductplatedata(@RequestBody searchproductplatedatapost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchproductplatedataresult result = new searchproductplatedataresult();
        ArrayList<searchproductplatedatadata> as = new ArrayList<searchproductplatedatadata>();
        searchproductplatedatadata data = null;

        String sql = "SELECT * FROM productplatedata WHERE 1=1 ";
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
                data = new searchproductplatedatadata();
                data.setProdno(rs.getString("prodno"));
                data.setTestboardstand(rs.getString("testboardstand"));
                data.setMatlstand(rs.getString("matlstand"));
                data.setDesignation(rs.getString("designation"));
                data.setRm(rs.getString("rm"));
                data.setElong(rs.getString("elong"));
                data.setImpacttemp(rs.getInt("impacttemp"));
                data.setImpactpa1(rs.getString("impactpa1"));
                data.setImpactpa2(rs.getString("impactpa2"));
                data.setImpactpa3(rs.getString("impactpa3"));
                data.setImpactpb1(rs.getString("impactpb1"));
                data.setImpactpb2(rs.getString("impactpb2"));
                data.setImpactpb3(rs.getString("impactpb3"));
                data.setBendpara(rs.getString("bendpara"));
                data.setEbendpara(rs.getString("ebendpara"));
                data.setBenddia(rs.getString("benddia"));
                data.setFractposit(rs.getString("fractposit"));
                data.setEfractposit(rs.getString("efractposit"));
                data.setEntrustdate(rs.getString("entrustdate"));
                data.setUser(rs.getString("user"));
                data.setAudit_user(rs.getString("audit_user"));
                data.setDate(rs.getString("date"));
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
