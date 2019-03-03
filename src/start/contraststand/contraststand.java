package start.contraststand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class contraststand {                                                                //根据标准牌号厚度查询
    @RequestMapping(value = "contraststand")
    public @ResponseBody contraststandresult contraststand(@RequestBody contraststandpost cp) throws ClassNotFoundException, SQLException{
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        contraststandresult result=new contraststandresult();
        contraststanddata data = new contraststanddata();
        deviation deviation = new deviation();

        type ct = null;
        ArrayList<type> c1 = new ArrayList<type>();
        ArrayList<type> si1 = new ArrayList<type>();
        ArrayList<type> mn1 = new ArrayList<type>();
        ArrayList<type> cu1 = new ArrayList<type>();
        ArrayList<type> ni1 = new ArrayList<type>();
        ArrayList<type> cr1 = new ArrayList<type>();
        ArrayList<type> mo1 = new ArrayList<type>();
        ArrayList<type> nb1 = new ArrayList<type>();
        ArrayList<type> v1 = new ArrayList<type>();
        ArrayList<type> ti1 = new ArrayList<type>();
        ArrayList<type> alt1 = new ArrayList<type>();
        ArrayList<type> n1 = new ArrayList<type>();
        ArrayList<type> mg1 = new ArrayList<type>();
        ArrayList<type> p1 = new ArrayList<type>();
        ArrayList<type> s1 = new ArrayList<type>();
        ArrayList<type> als1 = new ArrayList<type>();
        ArrayList<type> fe1 = new ArrayList<type>();
        ArrayList<type> zn1 = new ArrayList<type>();
        ArrayList<type> b1 = new ArrayList<type>();
        ArrayList<type> w1 = new ArrayList<type>();
        ArrayList<type> sb1 = new ArrayList<type>();
        ArrayList<type> al1 = new ArrayList<type>();
        ArrayList<type> zr1 = new ArrayList<type>();
        ArrayList<type> ca1 = new ArrayList<type>();
        ArrayList<type> be1 = new ArrayList<type>();

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
        minmax als = new minmax();
        minmax fe = new minmax();
        minmax zn = new minmax();
        minmax b = new minmax();
        minmax w = new minmax();
        minmax sb = new minmax();
        minmax al = new minmax();
        minmax zr = new minmax();
        minmax ca = new minmax();
        minmax be = new minmax();
        String bendaxdia=null;
        String type = null;
        int utclass_id=0;
        int utclass=0;
        int a=0;                                                         //判断是否为空

        try{

            if((cp.getSpec()!=null) && (!cp.getSpec().equals(""))){
                ps=conn.prepareStatement("SELECT * FROM contraststand WHERE matlstand=? and designation=? and spec_small<? and spec_big>=?");
                ps.setString(1,cp.getMatlstand());
                ps.setString(2,cp.getDesignation());
                ps.setString(3,cp.getSpec());
                ps.setString(4,cp.getSpec());
                rs=ps.executeQuery();
                if(!rs.next()){
                    result.setResult("fail");
                    rs.close();
                    ps.close();
                }else {
                    rs.close();
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
                        heatcondi = rs.getString("heatcondi");                    //热处理状态，
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
                        als.setMin(rs.getString("als_small"));
                        als.setMax(rs.getString("als_big"));
                        fe.setMin(rs.getString("fe_small"));
                        fe.setMax(rs.getString("fe_big"));
                        zn.setMin(rs.getString("zn_small"));
                        zn.setMax(rs.getString("zn_big"));
                        b.setMin(rs.getString("b_small"));
                        b.setMax(rs.getString("b_big"));
                        w.setMin(rs.getString("w_small"));
                        w.setMax(rs.getString("w_big"));
                        sb.setMin(rs.getString("sb_small"));
                        sb.setMax(rs.getString("sb_big"));
                        al.setMin(rs.getString("al_small"));
                        al.setMax(rs.getString("al_big"));
                        zr.setMin(rs.getString("zr_small"));
                        zr.setMax(rs.getString("zr_big"));
                        ca.setMin(rs.getString("ca_small"));
                        ca.setMax(rs.getString("ca_big"));
                        be.setMin(rs.getString("be_small"));
                        be.setMax(rs.getString("be_big"));
                        bendaxdia=rs.getString("bendaxdia");
                        utclass_id=rs.getInt("bending_id_utclass");
                        type = rs.getString("type");
                    }
                    rs.close();
                    ps.close();


                    ps=conn.prepareStatement("SELECT * FROM bending WHERE id=?");
                    ps.setInt(1,utclass_id);
                    rs=ps.executeQuery();
                    while(rs.next()){
                        utclass=rs.getInt("utclass");
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
                    data.setAls(als);
                    data.setFe(fe);
                    data.setZn(zn);
                    data.setB(b);
                    data.setW(w);
                    data.setSb(sb);
                    data.setAl(al);
                    data.setZr(zr);
                    data.setCa(ca);
                    data.setBe(be);
                    data.setBendaxdia(bendaxdia);
                    data.setUtclass(utclass);
                    rs.close();
                    ps.close();

                    ps = conn.prepareStatement("SELECT * FROM compositiondev WHERE type = ?");
                    ps.setString(1,type);
                    rs = ps.executeQuery();
                    while (rs.next()){
                        ct = new type();
                        ct.setRange(rs.getString("maxrange"),rs.getString("maxrange2"),rs.getString("minrange"),rs.getString("minrange"));
                        ct.setDeviation(rs.getString("updeviation"),rs.getString("downdeviation"));
                        if(rs.getString("element").equals("C")){
                            c1.add(ct);
                        }
                        if(rs.getString("element").equals("Si")){
                            si1.add(ct);
                        }
                        if(rs.getString("element").equals("Mn")){
                            mn1.add(ct);
                        }
                        if(rs.getString("element").equals("Cu")){
                            cu1.add(ct);
                        }
                        if(rs.getString("element").equals("Ni")){
                            ni1.add(ct);
                        }
                        if(rs.getString("element").equals("Cr")){
                            cr1.add(ct);
                        }
                        if(rs.getString("element").equals("Mo")){
                            mo1.add(ct);
                        }
                        if(rs.getString("element").equals("Nb")){
                            nb1.add(ct);
                        }
                        if(rs.getString("element").equals("V")){
                            v1.add(ct);
                        }
                        if(rs.getString("element").equals("Ti")){
                            ti1.add(ct);
                        }
                        if(rs.getString("element").equals("Als")){
                            als1.add(ct);
                        }
                        if(rs.getString("element").equals("Alt")){
                            alt1.add(ct);
                        }
                        if(rs.getString("element").equals("N")){
                            n1.add(ct);
                        }
                        if(rs.getString("element").equals("Fe")){
                            fe1.add(ct);
                        }
                        if(rs.getString("element").equals("Mg")){
                            mg1.add(ct);
                        }
                        if(rs.getString("element").equals("Zn")){
                            zn1.add(ct);
                        }
                        if(rs.getString("element").equals("B")){
                            b1.add(ct);
                        }
                        if(rs.getString("element").equals("W")){
                            w1.add(ct);
                        }
                        if(rs.getString("element").equals("Sb")){
                            sb1.add(ct);
                        }
                        if(rs.getString("element").equals("Al")){
                            al1.add(ct);
                        }
                        if(rs.getString("element").equals("Zr")){
                            zr1.add(ct);
                        }
                        if(rs.getString("element").equals("Ca")){
                            ca1.add(ct);
                        }
                        if(rs.getString("element").equals("Be")){
                            be1.add(ct);
                        }
                        if(rs.getString("element").equals("P")){
                            p1.add(ct);
                        }
                        if(rs.getString("element").equals("S")){
                            s1.add(ct);
                        }
                    }
                    rs.close();
                    ps.close();
                    deviation.setC(c1);
                    deviation.setSi(si1);
                    deviation.setMn(mn1);
                    deviation.setCu(cu1);
                    deviation.setNi(ni1);
                    deviation.setCr(cr1);
                    deviation.setMo(mo1);
                    deviation.setNb(nb1);
                    deviation.setV(v1);
                    deviation.setTi(ti1);
                    deviation.setAls(als1);
                    deviation.setAlt(alt1);
                    deviation.setN(n1);
                    deviation.setFe(fe1);
                    deviation.setMg(mg1);
                    deviation.setZn(zn1);
                    deviation.setB(b1);
                    deviation.setW(w1);
                    deviation.setSb(sb1);
                    deviation.setAl(al1);
                    deviation.setZr(zr1);
                    deviation.setCa(ca1);
                    deviation.setBe(be1);
                    deviation.setP(p1);
                    deviation.setS(s1);
                    data.setDeviation(deviation);

                    result.setResult("success");
                    result.setData(data);

                }


            }else{
                ps=conn.prepareStatement("SELECT * FROM contraststand WHERE matlstand=? and designation=? ");
                ps.setString(1,cp.getMatlstand());
                ps.setString(2,cp.getDesignation());
                rs=ps.executeQuery();
                if(!rs.next()){
                    result.setResult("fail");
                    rs.close();
                    ps.close();
                }else {
                    rs.close();
                    rs=ps.executeQuery();
                    while(rs.next()){
                        if(rs.getInt("spec_small")==0 && rs.getInt("spec_big")==0){

                            a=1;
                        }else{
                            a=0;
                        }
                    }
                    rs.close();
                    rs=ps.executeQuery();
                    if(a==1){
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
                            heatcondi = rs.getString("heatcondi");                    //热处理状态，
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
                            als.setMin(rs.getString("als_small"));
                            als.setMax(rs.getString("als_big"));
                            fe.setMin(rs.getString("fe_small"));
                            fe.setMax(rs.getString("fe_big"));
                            zn.setMin(rs.getString("zn_small"));
                            zn.setMax(rs.getString("zn_big"));
                            b.setMin(rs.getString("b_small"));
                            b.setMax(rs.getString("b_big"));
                            w.setMin(rs.getString("w_small"));
                            w.setMax(rs.getString("w_big"));
                            sb.setMin(rs.getString("sb_small"));
                            sb.setMax(rs.getString("sb_big"));
                            al.setMin(rs.getString("al_small"));
                            al.setMax(rs.getString("al_big"));
                            zr.setMin(rs.getString("zr_small"));
                            zr.setMax(rs.getString("zr_big"));
                            ca.setMin(rs.getString("ca_small"));
                            ca.setMax(rs.getString("ca_big"));
                            be.setMin(rs.getString("be_small"));
                            be.setMax(rs.getString("be_big"));
                            bendaxdia=rs.getString("bendaxdia");
                            utclass_id=rs.getInt("bending_id_utclass");
                            type = rs.getString("type");

                        }
                        rs.close();
                        ps.close();


                        ps=conn.prepareStatement("SELECT * FROM bending WHERE id=?");
                        ps.setInt(1,utclass_id);
                        rs=ps.executeQuery();
                        while(rs.next()){
                            utclass=rs.getInt("utclass");
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
                        data.setAls(als);
                        data.setFe(fe);
                        data.setZn(zn);
                        data.setB(b);
                        data.setW(w);
                        data.setSb(sb);
                        data.setAl(al);
                        data.setZr(zr);
                        data.setCa(ca);
                        data.setBe(be);
                        data.setBendaxdia(bendaxdia);
                        data.setUtclass(utclass);
                        rs.close();
                        ps.close();

                        ps = conn.prepareStatement("SELECT * FROM compositiondev WHERE type = ?");
                        ps.setString(1,type);
                        rs = ps.executeQuery();
                        while (rs.next()){
                            ct = new type();
                            ct.setRange(rs.getString("maxrange"),rs.getString("maxrange2"),rs.getString("minrange"),rs.getString("minrange"));
                            ct.setDeviation(rs.getString("updeviation"),rs.getString("downdeviation"));
                            if(rs.getString("element").equals("C")){
                                c1.add(ct);
                            }
                            if(rs.getString("element").equals("Si")){
                                si1.add(ct);
                            }
                            if(rs.getString("element").equals("Mn")){
                                mn1.add(ct);
                            }
                            if(rs.getString("element").equals("Cu")){
                                cu1.add(ct);
                            }
                            if(rs.getString("element").equals("Ni")){
                                ni1.add(ct);
                            }
                            if(rs.getString("element").equals("Cr")){
                                cr1.add(ct);
                            }
                            if(rs.getString("element").equals("Mo")){
                                mo1.add(ct);
                            }
                            if(rs.getString("element").equals("Nb")){
                                nb1.add(ct);
                            }
                            if(rs.getString("element").equals("V")){
                                v1.add(ct);
                            }
                            if(rs.getString("element").equals("Ti")){
                                ti1.add(ct);
                            }
                            if(rs.getString("element").equals("Als")){
                                als1.add(ct);
                            }
                            if(rs.getString("element").equals("Alt")){
                                alt1.add(ct);
                            }
                            if(rs.getString("element").equals("N")){
                                n1.add(ct);
                            }
                            if(rs.getString("element").equals("Fe")){
                                fe1.add(ct);
                            }
                            if(rs.getString("element").equals("Mg")){
                                mg1.add(ct);
                            }
                            if(rs.getString("element").equals("Zn")){
                                zn1.add(ct);
                            }
                            if(rs.getString("element").equals("B")){
                                b1.add(ct);
                            }
                            if(rs.getString("element").equals("W")){
                                w1.add(ct);
                            }
                            if(rs.getString("element").equals("Sb")){
                                sb1.add(ct);
                            }
                            if(rs.getString("element").equals("Al")){
                                al1.add(ct);
                            }
                            if(rs.getString("element").equals("Zr")){
                                zr1.add(ct);
                            }
                            if(rs.getString("element").equals("Ca")){
                                ca1.add(ct);
                            }
                            if(rs.getString("element").equals("Be")){
                                be1.add(ct);
                            }
                            if(rs.getString("element").equals("P")){
                                p1.add(ct);
                            }
                            if(rs.getString("element").equals("S")){
                                s1.add(ct);
                            }
                        }
                        rs.close();
                        ps.close();
                        deviation.setC(c1);
                        deviation.setSi(si1);
                        deviation.setMn(mn1);
                        deviation.setCu(cu1);
                        deviation.setNi(ni1);
                        deviation.setCr(cr1);
                        deviation.setMo(mo1);
                        deviation.setNb(nb1);
                        deviation.setV(v1);
                        deviation.setTi(ti1);
                        deviation.setAls(als1);
                        deviation.setAlt(alt1);
                        deviation.setN(n1);
                        deviation.setFe(fe1);
                        deviation.setMg(mg1);
                        deviation.setZn(zn1);
                        deviation.setB(b1);
                        deviation.setW(w1);
                        deviation.setSb(sb1);
                        deviation.setAl(al1);
                        deviation.setZr(zr1);
                        deviation.setCa(ca1);
                        deviation.setBe(be1);
                        deviation.setP(p1);
                        deviation.setS(s1);
                        data.setDeviation(deviation);


                        result.setResult("success");
                        result.setData(data);

                    }else{
                        result.setResult("fail");
                    }

                }
            }



        }catch (Exception e ){
            rs.close();
            ps.close();
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
