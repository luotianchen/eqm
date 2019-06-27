package start.putrematerial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putrematerial {                            //材料复验登记提交
    @RequestMapping(value = "putrematerial")
    public @ResponseBody putrematerialresult putrematerial(@RequestBody putrematerialpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putrematerialresult result = new putrematerialresult();

//        try {
            ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND (status = 0 OR status =-2) AND num = ?");
            ps.setString(1,pp.getCodedmarking());
            ps.setInt(2,pp.getNum());
            rs = ps.executeQuery();
            if(rs.next()){
                ps = conn.prepareStatement("UPDATE rematerial SET codedmarking=?,designation=?,stand=?,spec=?," +
                        "c=?,si=?,mn=?,cu=?,ni=?,cr=?,mo=?,nb=?,v=?,ti=?,alt=?,n=?,mg=?,p=?,s=?," +
                        "als=?,fe=?,zn=?,b=?,w=?,sb=?,al=?,zr=?,ca=?,be=?," +
                        "rel1=?,rel2=?,rm1=?,rm2=?,elong1=?,elong2=?,hardness1=?,hardness2=?,hardness3=?,impactp1=?,impactp2=?,impactp3=?," +
                        "impacttemp=?,bendangle=?,bendaxdia=?,indate=?,user=?,date=? WHERE codedmarking = ? AND status = 0");
                ps.setString(1,pp.getCodedmarking());
                ps.setString(2,pp.getDesignation());
                ps.setString(3,pp.getStand());
                ps.setString(4,pp.getSpec());
                ps.setString(5,pp.getC());
                ps.setString(6,pp.getSi());
                ps.setString(7,pp.getMn());
                ps.setString(8,pp.getCu());
                ps.setString(9,pp.getNi());
                ps.setString(10,pp.getCr());
                ps.setString(11,pp.getMo());
                ps.setString(12,pp.getNb());
                ps.setString(13,pp.getV());
                ps.setString(14,pp.getTi());
                ps.setString(15,pp.getAlt());
                ps.setString(16,pp.getN());
                ps.setString(17,pp.getMg());
                ps.setString(18,pp.getP());
                ps.setString(19,pp.getS());
                ps.setString(20,pp.getAls());
                ps.setString(21,pp.getFe());
                ps.setString(22,pp.getZn());
                ps.setString(23,pp.getB());
                ps.setString(24,pp.getW());
                ps.setString(25,pp.getSb());
                ps.setString(26,pp.getAl());
                ps.setString(27,pp.getZr());
                ps.setString(28,pp.getCa());
                ps.setString(29,pp.getBe());
                ps.setString(30,pp.getRel1());
                ps.setString(31,pp.getRel2());
                ps.setString(32,pp.getRm1());
                ps.setString(33,pp.getRm2());
                ps.setString(34,pp.getElong1());
                ps.setString(35,pp.getElong2());
                ps.setString(36,pp.getHardness1());
                ps.setString(37,pp.getHardness2());
                ps.setString(38,pp.getHardness3());
                ps.setString(39,pp.getImpactp1());
                ps.setString(40,pp.getImpactp2());
                ps.setString(41,pp.getImpactp3());
                ps.setString(42, pp.getImpacttemp());
                ps.setString(43,pp.getBendangle());
                ps.setString(44,pp.getBendaxdia());
                ps.setString(45,pp.getIndate());
                ps.setString(46,pp.getUser());
                ps.setDate(47,new java.sql.Date(new java.util.Date().getTime()));
                ps.setString(48,pp.getCodedmarking());
                ps.executeUpdate();
                ps.close();
            }else {
                ps = conn.prepareStatement("INSERT INTO rematerial(codedmarking,designation,stand,spec," +
                        "c,si,mn,cu,ni,cr,mo,nb,v,ti,alt,n,mg,p,s," +
                        "als,fe,zn,b,w,sb,al,zr,ca,be," +
                        "rel1,rel2,rm1,rm2,elong1,elong2,hardness1,hardness2,hardness3,impactp1,impactp2,impactp3," +
                        "impacttemp,bendangle,bendaxdia,indate,user,date,num) VALUES (?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?)");
                ps.setString(1,pp.getCodedmarking());
                ps.setString(2,pp.getDesignation());
                ps.setString(3,pp.getStand());
                ps.setString(4,pp.getSpec());
                ps.setString(5,pp.getC());
                ps.setString(6,pp.getSi());
                ps.setString(7,pp.getMn());
                ps.setString(8,pp.getCu());
                ps.setString(9,pp.getNi());
                ps.setString(10,pp.getCr());
                ps.setString(11,pp.getMo());
                ps.setString(12,pp.getNb());
                ps.setString(13,pp.getV());
                ps.setString(14,pp.getTi());
                ps.setString(15,pp.getAlt());
                ps.setString(16,pp.getN());
                ps.setString(17,pp.getMg());
                ps.setString(18,pp.getP());
                ps.setString(19,pp.getS());
                ps.setString(20,pp.getAls());
                ps.setString(21,pp.getFe());
                ps.setString(22,pp.getZn());
                ps.setString(23,pp.getB());
                ps.setString(24,pp.getW());
                ps.setString(25,pp.getSb());
                ps.setString(26,pp.getAl());
                ps.setString(27,pp.getZr());
                ps.setString(28,pp.getCa());
                ps.setString(29,pp.getBe());
                ps.setString(30,pp.getRel1());
                ps.setString(31,pp.getRel2());
                ps.setString(32,pp.getRm1());
                ps.setString(33,pp.getRm2());
                ps.setString(34,pp.getElong1());
                ps.setString(35,pp.getElong2());
                ps.setString(36,pp.getHardness1());
                ps.setString(37,pp.getHardness2());
                ps.setString(38,pp.getHardness3());
                ps.setString(39,pp.getImpactp1());
                ps.setString(40,pp.getImpactp2());
                ps.setString(41,pp.getImpactp3());
                ps.setString(42, pp.getImpacttemp());
                ps.setString(43,pp.getBendangle());
                ps.setString(44,pp.getBendaxdia());
                ps.setString(45,pp.getIndate());
                ps.setString(46,pp.getUser());
                ps.setDate(47,new java.sql.Date(new java.util.Date().getTime()));
                ps.setInt(48,pp.getNum());
                ps.executeUpdate();
                ps.close();
            }
            result.setResult("success");
//        }catch (Exception e){
//            result.setResult("fail");
//        }
        conn.close();
        return result;
    }
}
