package start.searchcontraststand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class searchcontraststand {                                      //通过材料标准、牌号、产品编号（通过产品编号从产品试板委托查规格）查询上下限数据（输入标准对照表）
    @RequestMapping(value = "searchcontraststand")
    public @ResponseBody searchcontraststandresult searchcontraststand(@RequestBody searchcontraststandpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchcontraststandresult result = new searchcontraststandresult();
        searchcontraststanddata data = new searchcontraststanddata();

        String spec = null;

        try {
            ps = conn.prepareStatement("update contraststand set bending_id_utclass = null where bending_id_utclass = ''");
            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement("update contraststand set note = null where note = ''");
            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement("update contraststand set bendaxdia = null where bendaxdia = ''");
            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement("update contraststand set type = null where type = ''");
            ps.executeUpdate();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                spec = rs.getString("spec");
            }else {
                result.setResult("fail");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM contraststand WHERE matlstand = ? AND designation = ? AND spec_small<? AND spec_big>=?");
            ps.setString(1,sp.getMatlstand());
            ps.setString(2,sp.getDesignation());
            ps.setString(3,spec);
            ps.setString(4,spec);
            rs = ps.executeQuery();
            if(rs.next()){
                data.setRm(new minmax(rs.getString("rm1_small"),rs.getString("rm1_big")));
                data.setElong(new minmax(rs.getString("elong1_small"),rs.getString("elong1_big")));
                data.setImpacttemp(rs.getInt("impacttemp"));
                data.setImpactpa1(new minmax(rs.getString("impactpa1_small"),rs.getString("impactpa1_big")));
                data.setImpactpa2(new minmax(rs.getString("impactpa2_small"),rs.getString("impactpa2_big")));
                data.setImpactpa3(new minmax(rs.getString("impactpa3_small"),rs.getString("impactpa3_big")));
                data.setImpactpb1(new minmax(rs.getString("impactpb1_small"),rs.getString("impactpb1_big")));
                data.setImpactpb2(new minmax(rs.getString("impactpb2_small"),rs.getString("impactpb2_big")));
                data.setImpactpb3(new minmax(rs.getString("impactpb3_small"),rs.getString("impactpb3_big")));
                data.setBendaxdia(rs.getString("bendaxdia"));
            }else {
                result.setResult("fail");
            }
            rs.close();
            ps.close();
            result.setData(data);
            result.setResult("seccess");

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
