package start.putmaterial;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@CrossOrigin
@Controller
public class putmaterial {                                                                              //材料入库登记
    @RequestMapping(value = "putmaterial")
    public @ResponseBody putmaterialresult putmaterial(@RequestBody putmaterialpost pp) throws ClassNotFoundException, SQLException, ParseException {          //生存单位，在单位名称内
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        int warrantystatus_id=0;
        int matlname_id=0;
        int matlstand_id=0;
        int designation_id=0;
        int millunit_id=0;
        int impacttemp_id=0;
        int bendangle_id=0;
        int utclass_id=0;
        int supplier_id=0;
        int heatcondi_id=0;
        int user_id=0;
        int modelstand_id=0;
        putmaterialresult result=new putmaterialresult();
        try{


            ps=conn.prepareStatement("SELECT * from modelstand WHERE modelstand = ?");
            ps.setString(1,pp.getModelstand());
            rs=ps.executeQuery();
            while(rs.next()){
                modelstand_id=rs.getInt("id");
            }
            rs.close();
            ps.close();


            ps=conn.prepareStatement("SELECT * from userform WHERE username = ?");
            ps.setString(1,pp.getUser());
            rs=ps.executeQuery();
            while(rs.next()){
                user_id=rs.getInt("id");
            }
            rs.close();
            ps.close();


            ps=conn.prepareStatement("SELECT * from warrantystatus WHERE certsitu=?");
            ps.setString(1,pp.getWarrantysitu());
            rs=ps.executeQuery();
            while(rs.next()){
                warrantystatus_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from matlname WHERE matlname=?");
            ps.setString(1,pp.getMatlname());
            rs=ps.executeQuery();
            while(rs.next()){
                matlname_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from contraststand WHERE matlstand=?");
            ps.setString(1,pp.getMatlstand());
            rs=ps.executeQuery();
            while(rs.next()){
                matlstand_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from contraststand WHERE designation=?");
            ps.setString(1,pp.getDesignation());
            rs=ps.executeQuery();
            while(rs.next()){
                designation_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from millunit WHERE millunit=?");
            ps.setString(1,pp.getMillunit());
            rs=ps.executeQuery();
            while(rs.next()){
                millunit_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from bending WHERE impacttemp=?");
            ps.setString(1,pp.getImpacttemp());
            rs=ps.executeQuery();
            while(rs.next()){
                impacttemp_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from bending WHERE bendangle=?");
            ps.setString(1,pp.getBendangle());
            rs=ps.executeQuery();
            while(rs.next()){
                bendangle_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from bending WHERE utclass=?");
            ps.setInt(1,pp.getUtclass());
            rs=ps.executeQuery();
            while(rs.next()){
                utclass_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from supplier WHERE supplier=?");
            ps.setString(1,pp.getSupplier());
            rs=ps.executeQuery();
            while(rs.next()){
                supplier_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * from heattreatcondition WHERE heatcondi=?");
            ps.setString(1,pp.getHeatcondi());
            rs=ps.executeQuery();
            while(rs.next()){
                heatcondi_id=rs.getInt("id");
            }
            rs.close();
            ps.close();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d1 = sdf.parse(pp.getIndate());
            java.sql.Date d = new java.sql.Date(d1.getTime());


            ps=conn.prepareStatement("SELECT * from putmaterial WHERE codedmarking = ?");
            ps.setString(1,pp.getCodedmarking());
            rs=ps.executeQuery();
            if(!rs.next()){
                rs.close();
                ps.close();
                ps=conn.prepareStatement("INSERT INTO putmaterial " +
                        "(codedmarking,note,indate,warrantyno,modelstand_id_modelstand,spec,qty,unit,dimension,heattreatcondition_id_heatcondi,heatbatchno," +
                        "c,si,mn,cu,ni,cr,mo,nb,v,ti,alt,n,mg,p,s," +
                        "rel1,rel2,rm1,rm2,elong1,elong2,hardness1,hardness2,hardness3,impactp1,impactp2,impactp3," +
                        "bendaxdia," +
                        "supplier_id_supplier,warrantystatus_id_certsitu,matlname_id_matlname,contraststand_id_matlstand,contraststand_id_designation,millunit_id_millunit," +
                        "bending_id_impacttemp,bending_id_bendangle,bending_id_utclass," +
                        "als,fe,zn,b,w,sb,al,zr,ca,be," +
                        "user_id)values(?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?," +
                        "?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?," +
                        "?)");
                ps.setString(1,pp.getCodedmarking());
                ps.setString(2,pp.getNote());
                ps.setDate(3,d);
                ps.setString(4,pp.getWarrantyno());
                ps.setInt(5,modelstand_id);
                ps.setString(6,pp.getSpec());
                ps.setString(7,pp.getQty());
                ps.setString(8,pp.getUnit());
                ps.setString(9,pp.getDimension());
                ps.setInt(10,heatcondi_id);
                ps.setString(11,pp.getHeatbatchno());
                ps.setString(12,pp.getC());
                ps.setString(13,pp.getSi());
                ps.setString(14,pp.getMn());
                ps.setString(15,pp.getCu());
                ps.setString(16,pp.getNi());
                ps.setString(17,pp.getCr());
                ps.setString(18,pp.getMo());
                ps.setString(19,pp.getNb());
                ps.setString(20,pp.getV());
                ps.setString(21,pp.getTi());
                ps.setString(22,pp.getAlt());
                ps.setString(23,pp.getN());
                ps.setString(24,pp.getMg());
                ps.setString(25,pp.getP());
                ps.setString(26,pp.getS());
                ps.setString(27,pp.getRel1());
                ps.setString(28,pp.getRel2());
                ps.setString(29,pp.getRm1());
                ps.setString(30,pp.getRm2());
                ps.setString(31,pp.getElong1());
                ps.setString(32,pp.getElong2());
                ps.setString(33,pp.getHardness1());
                ps.setString(34,pp.getHardness2());
                ps.setString(35,pp.getHardness3());
                ps.setString(36,pp.getImpactp1());
                ps.setString(37,pp.getImpactp2());
                ps.setString(38,pp.getImpactp3());
                ps.setString(39,pp.getBendaxdia());
                ps.setInt(40,supplier_id);
                ps.setInt(41,warrantystatus_id);
                ps.setInt(42,matlname_id);
                ps.setInt(43,matlstand_id);
                ps.setInt(44,designation_id);
                ps.setInt(45,millunit_id);
                ps.setInt(46,impacttemp_id);
                ps.setInt(47,bendangle_id);
                ps.setInt(48,utclass_id);
                ps.setString(49,pp.getAls());
                ps.setString(50,pp.getFe());
                ps.setString(51,pp.getZn());
                ps.setString(52,pp.getB());
                ps.setString(53,pp.getW());
                ps.setString(54,pp.getSb());
                ps.setString(55,pp.getAl());
                ps.setString(56,pp.getZr());
                ps.setString(57,pp.getCa());
                ps.setString(58,pp.getBe());
                ps.setInt(59,user_id);
                ps.executeUpdate();
                ps.close();
            }else{
                rs.close();
                ps.close();
                ps=conn.prepareStatement("UPDATE putmaterial SET " +
                        "note=?,indate=?,warrantyno=?,modelstand_id_modelstand=?,spec=?,qty=?,unit=?,dimension=?,heattreatcondition_id_heatcondi=?,heatbatchno=?," +
                        "c=?,si=?,mn=?,cu=?,ni=?,cr=?,mo=?,nb=?,v=?,ti=?,alt=?,n=?,mg=?,p=?,s=?," +
                        "rel1=?,rel2=?,rm1=?,rm2=?,elong1=?,elong2=?,hardness1=?,hardness2=?,hardness3=?,impactp1=?,impactp2=?,impactp3=?," +
                        "bendaxdia=?," +
                        "supplier_id_supplier=?,warrantystatus_id_certsitu=?,matlname_id_matlname=?,contraststand_id_matlstand=?,contraststand_id_designation=?,millunit_id_millunit=?," +
                        "bending_id_impacttemp=?,bending_id_bendangle=?,bending_id_utclass=?," +
                        "als=?,fe=?,zn=?,b=?,w=?,sb=?,al=?,zr=?,ca=?,be=?," +
                        "user_id=? WHERE codedmarking=?");
                ps.setString(1,pp.getCodedmarking());
                ps.setString(1,pp.getNote());
                ps.setDate(2,d);
                ps.setString(3,pp.getWarrantyno());
                ps.setInt(4,modelstand_id);
                ps.setString(5,pp.getSpec());
                ps.setString(6,pp.getQty());
                ps.setString(7,pp.getUnit());
                ps.setString(8,pp.getDimension());
                ps.setInt(9,heatcondi_id);
                ps.setString(10,pp.getHeatbatchno());
                ps.setString(11,pp.getC());
                ps.setString(12,pp.getSi());
                ps.setString(13,pp.getMn());
                ps.setString(14,pp.getCu());
                ps.setString(15,pp.getNi());
                ps.setString(16,pp.getCr());
                ps.setString(17,pp.getMo());
                ps.setString(18,pp.getNb());
                ps.setString(19,pp.getV());
                ps.setString(20,pp.getTi());
                ps.setString(21,pp.getAlt());
                ps.setString(22,pp.getN());
                ps.setString(23,pp.getMg());
                ps.setString(24,pp.getP());
                ps.setString(25,pp.getS());
                ps.setString(26,pp.getRel1());
                ps.setString(27,pp.getRel2());
                ps.setString(28,pp.getRm1());
                ps.setString(29,pp.getRm2());
                ps.setString(30,pp.getElong1());
                ps.setString(31,pp.getElong2());
                ps.setString(32,pp.getHardness1());
                ps.setString(33,pp.getHardness2());
                ps.setString(34,pp.getHardness3());
                ps.setString(35,pp.getImpactp1());
                ps.setString(36,pp.getImpactp2());
                ps.setString(37,pp.getImpactp3());
                ps.setString(38,pp.getBendaxdia());
                ps.setInt(39,supplier_id);
                ps.setInt(40,warrantystatus_id);
                ps.setInt(41,matlname_id);
                ps.setInt(42,matlstand_id);
                ps.setInt(43,designation_id);
                ps.setInt(44,millunit_id);
                ps.setInt(45,impacttemp_id);
                ps.setInt(46,bendangle_id);
                ps.setInt(47,utclass_id);
                ps.setString(48,pp.getAls());
                ps.setString(49,pp.getFe());
                ps.setString(50,pp.getZn());
                ps.setString(51,pp.getB());
                ps.setString(52,pp.getW());
                ps.setString(53,pp.getSb());
                ps.setString(54,pp.getAl());
                ps.setString(55,pp.getZr());
                ps.setString(56,pp.getCa());
                ps.setString(57,pp.getBe());
                ps.setInt(58,user_id);
                ps.setString(59,pp.getCodedmarking());
                ps.executeUpdate();
                ps.close();
            }

            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}