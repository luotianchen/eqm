package start.searchrematerial;

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
public class searchrematerial {                                         //材料复验登记查询
    @RequestMapping(value = "searchrematerial")
    public @ResponseBody searchrematerialresult searchrematerial(@RequestBody searchrematerialpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchrematerialresult result = new searchrematerialresult();
        ArrayList<searchrematerialdata> as = new ArrayList<searchrematerialdata>();
        searchrematerialdata data = null;

        String sql = "SELECT * FROM rematerial WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getCodedmarking() == null || sp.getCodedmarking().equals(""))){
                sql = sql + "AND codedmarking = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ?";
            }
            ps = conn.prepareStatement(sql);
            if(!(sp.getCodedmarking() == null || sp.getCodedmarking().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getCodedmarking());
            }
            if(sp.getStatus() != -1){
                num = num + 1;
                ps.setInt(num,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchrematerialdata();
                data.setCodedmarking(rs.getString("codedmarking"));
                data.setDesignation(rs.getString("designation"));
                data.setStand(rs.getString("stand"));
                data.setSpec(rs.getString("spec"));
                data.setC(rs.getString("c"));
                data.setSi(rs.getString("si"));
                data.setMn(rs.getString("mn"));
                data.setCu(rs.getString("cu"));
                data.setNi(rs.getString("ni"));
                data.setCr(rs.getString("cr"));
                data.setMo(rs.getString("mo"));
                data.setNb(rs.getString("nb"));
                data.setV(rs.getString("v"));
                data.setTi(rs.getString("ti"));
                data.setAlt(rs.getString("alt"));
                data.setN(rs.getString("n"));
                data.setMg(rs.getString("mg"));
                data.setP(rs.getString("p"));
                data.setS(rs.getString("s"));
                data.setAls(rs.getString("als"));
                data.setFe(rs.getString("fe"));
                data.setZn(rs.getString("zn"));
                data.setB(rs.getString("b"));
                data.setW(rs.getString("w"));
                data.setSb(rs.getString("sb"));
                data.setAl(rs.getString("al"));
                data.setZr(rs.getString("zr"));
                data.setCa(rs.getString("ca"));
                data.setBe(rs.getString("be"));
                data.setRel1(rs.getString("rel1"));
                data.setRel2(rs.getString("rel2"));
                data.setRm1(rs.getString("rm1"));
                data.setRm2(rs.getString("rm2"));
                data.setElong1(rs.getString("elong1"));
                data.setElong2(rs.getString("elong2"));
                data.setHardness1(rs.getString("hardness1"));
                data.setHardness2(rs.getString("hardness2"));
                data.setHardness3(rs.getString("hardness3"));
                data.setImpactp1(rs.getString("impactp1"));
                data.setImpactp2(rs.getString("impactp2"));
                data.setImpactp3(rs.getString("impactp3"));
                data.setImpacttemp(rs.getString("impacttemp"));
                data.setBendangle(rs.getString("bendangle"));
                data.setBendaxdia(rs.getString("bendaxdia"));
                data.setIndate(rs.getString("indate"));
                data.setUser(rs.getString("user"));
                data.setDate(rs.getString("date"));
                data.setAudit_user(rs.getString("audit_user"));
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
