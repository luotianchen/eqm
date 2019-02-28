package start.putproductplatedata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putproductplatedata {                              //提交产品试板数据录入
    @RequestMapping(value = "putproductplatedata")
    public @ResponseBody putproductplatedataresult putproductplatedata(@RequestBody putproductplatedatapost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putproductplatedataresult result = new putproductplatedataresult();

        try {
            ps = conn.prepareStatement("INSERT INTO productplate(prodno,testno,specimenno," +
                    "specimentype,specimenmatl,specimenspec,parentmatltand," +
                    "judgestand,testdate,a," +
                    "b,so,f02," +
                    "f1,f02mpa,f1mpa,fm," +
                    "rm,lo,lu,apercent," +
                    "fractposit,hardness1,hardness2,hardness3," +
                    "bendangle,bendaxdia,bendatype,surfacebending1," +
                    "backbending1,surfacebending2,backbending2," +
                    "w1,lew1,w2,lew2,w3,lew3," +
                    "h1,leh1,h2,leh2,h3,leh3," +
                    "gapType,shocktemp,user,date) VALUES (?,?,?," +
                    "?,?,?,?," +
                    "?,?,?," +
                    "?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?,?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setString(2,pp.getTestno());
            ps.setString(3,pp.getSpecimenno());
            ps.setString(4,pp.getSpecimentype());
            ps.setString(5,pp.getSpecimenmatl());
            ps.setString(6,pp.getSpecimenspec());
            ps.setString(7,pp.getParentmatltand());
            ps.setString(8,pp.getJudgestand());
            ps.setString(9,pp.getTestdate());
            ps.setString(10,pp.getA());
            ps.setString(11,pp.getB());
            ps.setString(12,pp.getSo());
            ps.setString(13,pp.getF02());
            ps.setString(14,pp.getF1());
            ps.setString(15,pp.getF02mpa());
            ps.setString(16,pp.getF1mpa());
            ps.setString(17,pp.getFm());
            ps.setString(18,pp.getRm());
            ps.setString(19,pp.getLo());
            ps.setString(20,pp.getLu());
            ps.setString(21,pp.getApercent());
            ps.setString(22,pp.getFractposit());
            ps.setString(23,pp.getHardness1());
            ps.setString(24,pp.getHardness2());
            ps.setString(25,pp.getHardness3());
            ps.setString(26,pp.getBendangle());
            ps.setString(27,pp.getBendaxdia());
            ps.setString(28,pp.getBendatype());
            ps.setString(29,pp.getSurfacebending1());
            ps.setString(30,pp.getBackbending1());
            ps.setString(31,pp.getSurfacebending2());
            ps.setString(32,pp.getBackbending2());
            ps.setString(33,pp.getW1());
            ps.setString(34,pp.getLew1());
            ps.setString(35,pp.getW2());
            ps.setString(36,pp.getLew2());
            ps.setString(37,pp.getW3());
            ps.setString(38,pp.getLew3());
            ps.setString(39,pp.getH1());
            ps.setString(40,pp.getLeh1());
            ps.setString(41,pp.getH2());
            ps.setString(42,pp.getLeh2());
            ps.setString(43,pp.getH3());
            ps.setString(44,pp.getLeh3());
            ps.setString(45,pp.getGapType());
            ps.setString(46,pp.getShocktemp());
            ps.setString(47,pp.getUser());
            ps.setDate(48,new java.sql.Date(new java.util.Date().getTime()));
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
