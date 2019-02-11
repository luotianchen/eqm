package start.putpromanparlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putpromanparlist {                                 //产品制造参数提交
    @RequestMapping(value = "putpromanparlist")
    public @ResponseBody putpromanparlistresult putpromanparlist(@RequestBody putpromanparlistpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putpromanparlistresult result = new putpromanparlistresult();

        int dwgno_id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ?");
            ps.setString(1,pp.getDwgno());
            rs = ps.executeQuery();
            if(rs.next()){
                dwgno_id = rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("INSERT INTO promanparlist(prodno,proparlist_id_dwgno," +
                    "orderunit,eorderunit,ecode,dealter," +
                    "submatl,docum,dedate,blankdate," +
                    "matlretest,specmatl,overmatl,nwpq," +
                    "nprocess,copsitu,exworkdate," +
                    "aweldmaxangul,bweldmaxangul," +
                    "aweldmaxalign,bweldmaxalign," +
                    "weldreinfs,weldreinfd," +
                    "user,date," +
                    "type,etype,proheight,innerdia," +
                    "roundness,length,straightness,thick," +
                    "minthickstand,minthick,outward,concave) VALUES (?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?," +
                    "?,?," +
                    "?,?," +
                    "?,?," +
                    "?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setInt(2,dwgno_id);
            ps.setString(3,pp.getOrderunit());
            ps.setString(4,pp.getEorderunit());
            ps.setString(5,pp.getEcode());
            ps.setString(6,pp.getDealter());
            ps.setString(7,pp.getSubmatl());
            ps.setString(8,pp.getDocum());
            ps.setString(9,pp.getDedate());
            ps.setString(10,pp.getBlankdate());
            ps.setString(11,pp.getMatlretest());
            ps.setString(12,pp.getSpecmatl());
            ps.setString(13,pp.getOvermatl());
            ps.setString(14,pp.getNwpq());
            ps.setString(15,pp.getNprocess());
            ps.setString(16,pp.getCopsitu());
            ps.setString(17,pp.getExworkdate());
            ps.setString(18,pp.getAweldmaxangul());
            ps.setString(19,pp.getBweldmaxangul());
            ps.setString(20,pp.getAweldmaxalign());
            ps.setString(21,pp.getBweldmaxalign());
            ps.setString(22,pp.getWeldreinfs());
            ps.setString(23,pp.getWeldreinfd());
            ps.setString(24,pp.getUser());
            ps.setDate(25,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(26,pp.getType());
            ps.setString(27,pp.getEtype());
            ps.setString(28,pp.getProheight());
            ps.setString(29,pp.getInnerdia());
            ps.setString(30,pp.getRoundness());
            ps.setString(31,pp.getLength());
            ps.setString(32,pp.getStraightness());
            ps.setString(33,pp.getThick());
            ps.setString(34,pp.getMinthickstand());
            ps.setString(35,pp.getMinthick());
            ps.setString(36,pp.getOutward());
            ps.setString(37,pp.getConcave());
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
