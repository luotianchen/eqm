package start.getmaterial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;

@CrossOrigin
@Controller
public class getmaterial {                                                                                                              //根据入库编号查询材料数据
    @RequestMapping(value = "getmaterial")
    public @ResponseBody getmaterialresult getmaterial(@RequestBody getmaterialpost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        getmaterialresult result = new getmaterialresult();
        getmaterialdata data = new getmaterialdata();
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
        int modelstand_id=0;
        int user_id=0;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        data.setCodedmarking(gp.getCodedmarking());

        try{
            ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking=?");
            ps.setString(1,gp.getCodedmarking());


            rs=ps.executeQuery();
            if(!rs.next()){
                result.setResult("fail");
                rs.close();
                ps.close();
            }else{
                rs.close();
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setNote(rs.getString("note"));
                    data.setIndate(sdf.format(rs.getDate("indate")));
                    data.setWarrantyno(rs.getString("warrantyno"));
                    modelstand_id=rs.getInt("modelstand_id_modelstand");
                    data.setSpec(rs.getString("spec"));
                    data.setQty(rs.getString("qty"));
                    data.setUnit(rs.getString("unit"));
                    data.setDimension(rs.getString("dimension"));
                    data.setHeatbatchno(rs.getString("heatbatchno"));
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
                    data.setBendaxdia(rs.getString("bendaxdia"));
                    warrantystatus_id=rs.getInt("warrantystatus_id_certsitu");
                    matlname_id=rs.getInt("matlname_id_matlname");
                    matlstand_id=rs.getInt("contraststand_id_matlstand");
                    designation_id=rs.getInt("contraststand_id_designation");
                    millunit_id=rs.getInt("millunit_id_millunit");
                    data.setImpacttemp(rs.getString("bending_id_impacttemp"));
                    data.setBendangle(rs.getString("bending_id_bendangle"));
                    utclass_id=rs.getInt("bending_id_utclass");
                    supplier_id=rs.getInt("supplier_id_supplier");
                    data.setHeatcondi(rs.getString("heattreatcondition_id_heatcondi"));
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
                    user_id = rs.getInt("user_id");
                    data.setAudit_user(rs.getString("audit_user"));
                    data.setDate(rs.getString("date"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM modelstand WHERE id=?");
                ps.setInt(1,modelstand_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setModelstand(rs.getString("modelstand"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM warrantystatus WHERE id=?");
                ps.setInt(1,warrantystatus_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setWarrantysitu(rs.getString("certsitu"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM matlname WHERE id=?");
                ps.setInt(1,matlname_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setMatlname(rs.getString("matlname"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps.setInt(1,matlstand_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setMatlstand(rs.getString("matlstand"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps.setInt(1,designation_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setDesignation(rs.getString("designation"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM millunit WHERE id=?");
                ps.setInt(1,millunit_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setMillunit(rs.getString("millunit"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM bending WHERE id=?");
                ps.setInt(1,utclass_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setUtclass(rs.getInt("utclass"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM supplier WHERE id=?");
                ps.setInt(1,supplier_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setSupplier(rs.getString("supplier"));
                }
                rs.close();
                ps.close();


                ps=conn.prepareStatement("SELECT * FROM userform WHERE id=?");
                ps.setInt(1,user_id);
                rs=ps.executeQuery();
                while (rs.next()){
                    data.setUser(rs.getString("username"));
                }
                rs.close();
                ps.close();

                result.setResult("success");
            }




        }catch (Exception e){

            result.setResult("fail");
        }

        conn.close();
        result.setData(data);

        return result;
    }
}
