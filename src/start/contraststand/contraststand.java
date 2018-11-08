package start.contraststand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class contraststand {
    @RequestMapping(value = "contraststand")
    public @ResponseBody contraststandresult contraststand(@RequestBody contraststandpost cp) throws ClassNotFoundException, SQLException{
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        contraststandresult result=new contraststandresult();
        contraststanddata data = new contraststanddata();
        minmax c = new minmax();
        minmax si = new minmax();
        minmax mn = new minmax();
        minmax cu = new minmax();
        minmax ni = new minmax();
        minmax cr = new minmax();
        minmax mo = new minmax();
        minmax nb = new minmax();
        minmax v = new minmax();
        minmax ti = new minmax();
        minmax alt = new minmax();
        minmax n = new minmax();
        minmax mg = new minmax();
        minmax p = new minmax();
        minmax s = new minmax();
        String heatcondi=null;
        minmax rel1 = new minmax();
        minmax rel2 = new minmax();
        minmax rm1 = new minmax();
        minmax rm2 = new minmax();
        minmax elong1 = new minmax();
        minmax elong2 = new minmax();
        minmax hardness1 = new minmax();
        minmax hardness2 = new minmax();
        minmax hardness3 = new minmax();
        minmax impactp1 = new minmax();
        minmax impactp2 = new minmax();
        minmax impactp3 = new minmax();
        String impacttemp=null;
        String note=null;

        try{
            ps=conn.prepareStatement("SELECT * FROM contraststand WHERE matlstand=? and designation=? and thickness=?");
            ps.setString(1,cp.getMatlstand());
            ps.setString(2,cp.getDesignation());
            ps.setString(3,cp.getThickness());
            rs=ps.executeQuery();
            while (rs.next()){
                c.setMin(rs.getString("c_small"));
                c.setMax(rs.getString("c_big"));
                si.setMin(rs.getString("si_small"));
                si.setMax(rs.getString("si_big"));
                mn.setMin(rs.getString("mn_small"));
                mn.setMax(rs.getString("mn_big"));
                cu.setMin(rs.getString("cu_small"));
                cu.setMax(rs.getString("cu_big"));
                ni.setMin(rs.getString("ni_small"));
                ni.setMax(rs.getString("ni_big"));
                cr.setMin(rs.getString("cr_small"));
                cr.setMax(rs.getString("cr_big"));
                mo.setMin(rs.getString("mo_small"));
                mo.setMax(rs.getString("mo_big"));
                nb.setMin(rs.getString("nb_small"));
                nb.setMax(rs.getString("nb_big"));
                v.setMin(rs.getString("v_small"));
                v.setMax(rs.getString("v_big"));
                ti.setMin(rs.getString("ti_small"));
                ti.setMax(rs.getString("ti_big"));
                alt.setMin(rs.getString("alt_small"));
                alt.setMax(rs.getString("alt_big"));
                n.setMin(rs.getString("n_small"));
                n.setMax(rs.getString("n_big"));
                mg.setMin(rs.getString("mg_small"));
                mg.setMax(rs.getString("mg_big"));
                p.setMin(rs.getString("p_small"));
                p.setMax(rs.getString("p_big"));
                s.setMin(rs.getString("s_small"));
                s.setMax(rs.getString("s_big"));
                heatcondi = rs.getString("heatcondi");
                rel1.setMin(rs.getString("rel1_small"));
                rel1.setMax(rs.getString("rel1_big"));
                rel2.setMin(rs.getString("rel2_small"));
                rel2.setMax(rs.getString("rel2_big"));
                rm1.setMin(rs.getString("rm1_small"));
                rm1.setMax(rs.getString("rm1_big"));
                rm2.setMin(rs.getString("rm2_small"));
                rm2.setMax(rs.getString("rm2_big"));
                elong1.setMin(rs.getString("elong1_small"));
                elong1.setMax(rs.getString("elong1_big"));
                elong2.setMin(rs.getString("elong2_small"));
                elong2.setMax(rs.getString("elong2_big"));
                hardness1.setMin(rs.getString("hardness1_small"));
                hardness1.setMax(rs.getString("hardness1_big"));
                hardness2.setMin(rs.getString("hardness2_small"));
                hardness2.setMax(rs.getString("hardness2_big"));
                hardness3.setMin(rs.getString("hardness3_small"));
                hardness3.setMax(rs.getString("hardness3_big"));
                impactp1.setMin(rs.getString("impactp1_small"));
                impactp1.setMax(rs.getString("impactp1_big"));
                impactp2.setMin(rs.getString("impactp2_small"));
                impactp2.setMax(rs.getString("impactp2_big"));
                impactp3.setMin(rs.getString("impactp3_small"));
                impactp3.setMax(rs.getString("impactp3_big"));
                impacttemp=rs.getString("impacttemp");
                note=rs.getString("note");
            }



            data.setC(c);
            data.setSi(si);
            data.setMn(mn);
            data.setCu(cu);
            data.setNi(ni);
            data.setCr(cr);
            data.setMo(mo);
            data.setNb(nb);
            data.setV(v);
            data.setTi(ti);
            data.setAlt(alt);
            data.setN(n);
            data.setMg(mg);
            data.setP(p);
            data.setS(s);
            data.setHeatcondi(heatcondi);
            data.setRel1(rel1);
            data.setRel2(rel2);
            data.setRm1(rm1);
            data.setRm2(rm2);
            data.setElong1(elong1);
            data.setElong2(elong2);
            data.setHardness1(hardness1);
            data.setHardness2(hardness2);
            data.setHardness3(hardness3);
            data.setImpactp1(impactp1);
            data.setImpactp2(impactp2);
            data.setImpactp3(impactp3);
            data.setImpacttemp(impacttemp);
            data.setNote(note);


            result.setResult("success");
            result.setData(data);
        }catch (Exception e ){
            result.setResult("fail");
        }

        return result;
    }
}
