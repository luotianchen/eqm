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

        String sql = "SELECT `id`, (SELECT name FROM userform WHERE username = user) as user, `status`, `date`, (SELECT name FROM userform WHERE username = audit_user) as audit_user, `prodno`, `testno`, `specimenno`, `specimentype`, `specimenmatl`, `specimenspec`, `parentmatltand`, `judgestand`, `testdate`, `a`, `b`, `so`, `f02`, `f1`, `f02mpa`, `f1mpa`, `fm`, `rm`, `lo`, `lu`, `apercent`, `fractposit`, `hardness1`, `hardness2`, `hardness3`, `bendangle`, `bendaxdia`, `bendatype`, `surfacebending1`, `backbending1`, `surfacebending2`, `backbending2`, `w1`, `lew1`, `w2`, `lew2`, `w3`, `lew3`, `h1`, `leh1`, `h2`, `leh2`, `h3`, `leh3`, `gaptype`, `shocktemp` FROM productplate WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ? ";
            }
            if(!(sp.getYear() == null || sp.getYear().equals(""))){
                sql = sql + "AND testdate Like ? ";
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
            if(!(sp.getYear() == null || sp.getYear().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getYear()+"%");
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchproductplatedatadata();
                data.setId(rs.getInt("id"));
                data.setProdno(rs.getString("prodno"));
                data.setTestno(rs.getString("testno"));
                data.setSpecimenno(rs.getString("specimenno"));
                data.setSpecimentype(rs.getString("specimentype"));
                data.setSpecimenmatl(rs.getString("specimenmatl"));
                data.setSpecimenspec(rs.getString("specimenspec"));
                data.setParentmatltand(rs.getString("parentmatltand"));
                data.setJudgestand(rs.getString("judgestand"));
                data.setTestdate(rs.getString("testdate"));
                data.setA(rs.getString("a"));
                data.setB(rs.getString("b"));
                data.setSo(rs.getString("so"));
                data.setF02(rs.getString("f02"));
                data.setF1(rs.getString("f1"));
                data.setF02mpa(rs.getString("f02mpa"));
                data.setF1mpa(rs.getString("f1mpa"));
                data.setFm(rs.getString("fm"));
                data.setRm(rs.getString("rm"));
                data.setLo(rs.getString("lo"));
                data.setLu(rs.getString("lu"));
                data.setApercent(rs.getString("apercent"));
                data.setFractposit(rs.getString("fractposit"));
                data.setHardness1(rs.getString("hardness1"));
                data.setHardness2(rs.getString("hardness2"));
                data.setHardness3(rs.getString("hardness3"));
                data.setBendangle(rs.getString("bendangle"));
                data.setBendaxdia(rs.getString("bendaxdia"));
                data.setBendatype(rs.getString("bendatype"));
                data.setSurfacebending1(rs.getString("surfacebending1"));
                data.setBackbending1(rs.getString("backbending1"));
                data.setSurfacebending2(rs.getString("surfacebending2"));
                data.setBackbending2(rs.getString("backbending2"));
                data.setW1(rs.getString("w1"));
                data.setLew1(rs.getString("lew1"));
                data.setW2(rs.getString("w2"));
                data.setLew2(rs.getString("lew2"));
                data.setW3(rs.getString("w3"));
                data.setLew3(rs.getString("lew3"));
                data.setH1(rs.getString("h1"));
                data.setLeh1(rs.getString("leh1"));
                data.setH2(rs.getString("h2"));
                data.setLeh2(rs.getString("leh2"));
                data.setH3(rs.getString("h3"));
                data.setLeh3(rs.getString("leh3"));
                data.setGapType(rs.getString("gapType"));
                data.setShocktemp(rs.getString("shocktemp"));
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
