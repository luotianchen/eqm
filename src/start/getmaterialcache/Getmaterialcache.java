package start.getmaterialcache;

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
public class Getmaterialcache {                                                                                                              //根据入库编号查询材料数据
    @RequestMapping(value = "getmaterialcache")
    public @ResponseBody
    getmaterialcacheresult getmaterial(@RequestBody getmaterialcachepost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        getmaterialcacheresult result = new getmaterialcacheresult();
        getmaterialcachedata data = new getmaterialcachedata();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        data.setCodedmarking(gp.getCodedmarking());

        try{
            ps=conn.prepareStatement("SELECT * FROM putmaterialcache WHERE codedmarking=?");
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
                    data.setModelstand(rs.getString("modelstand_id_modelstand"));
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
                    data.setWarrantysitu(rs.getString("warrantystatus_id_certsitu"));
                    data.setMatlname(rs.getString("matlname_id_matlname"));
                    data.setMatlstand(rs.getString("contraststand_id_matlstand"));
                    data.setDesignation(rs.getString("contraststand_id_designation"));
                    data.setMillunit(rs.getString("millunit_id_millunit"));
                    data.setImpacttemp(rs.getString("bending_id_impacttemp"));
                    data.setBendangle(rs.getString("bending_id_bendangle"));
                    data.setUtclass(rs.getInt("bending_id_utclass"));
                    data.setSupplier(rs.getString("supplier_id_supplier"));
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
                }
                rs.close();
                ps.close();


                result.setResult("success");
            }




        }catch (Exception e){
            rs.close();
            ps.close();
            result.setResult("fail");
        }

        conn.close();
        result.setData(data);

        return result;
    }
}
