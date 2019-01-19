package start.putpromanparlist2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putpromanparlist2 {                                 //产品制造参数2提交
    @RequestMapping(value = "putpromanparlist2")
    public @ResponseBody putpromanparlist2result putpromanparlist2(@RequestBody putpromanparlist2post pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putpromanparlist2result result = new putpromanparlist2result();

        try {
            ps = conn.prepareStatement("INSERT INTO promanparlist2(prodno,dwgno," +
                            "exworkdate," +
                            "aweldmaxangul,bweldmaxangul," +
                            "aweldmaxalign,bweldmaxalign," +
                            "weldreinfs,weldreinfd," +
                            "user,date,proheight,innerdia," +
                            "roundness,length,straightness,thick," +
                            "minthickstand,minthick,outward,concave" +
                            ") VALUES (?,?," +
                            "?," +
                            "?,?," +
                            "?,?," +
                            "?,?," +
                            "?,?,?,?," +
                            "?,?,?,?," +
                            "?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setString(2,pp.getDwgno());
            ps.setString(3,pp.getExworkdate());
            ps.setString(4,pp.getAweldmaxangul());
            ps.setString(5,pp.getBweldmaxangul());
            ps.setString(6,pp.getAweldmaxalign());
            ps.setString(7,pp.getBweldmaxalign());
            ps.setString(8,pp.getWeldreinfs());
            ps.setString(9,pp.getWeldreinfd());
            ps.setString(10,pp.getUser());
            ps.setDate(11,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(12,pp.getProheight());
            ps.setString(13,pp.getInnerdia());
            ps.setString(14,pp.getRoundness());
            ps.setString(15,pp.getLength());
            ps.setString(16,pp.getStraightness());
            ps.setString(17,pp.getThick());
            ps.setString(18,pp.getMinthickstand());
            ps.setString(19,pp.getMinthick());
            ps.setString(20,pp.getOutward());
            ps.setString(21,pp.getConcave());
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
