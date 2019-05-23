package start.searchallmaterial;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchallmaterial {                                                            //查询所有材料入库信息带分页
    @RequestMapping(value = "searchallmaterial")
    public @ResponseBody searchallmaterialresult searchallmaterial(@RequestBody searchallmaterialpost sp) throws ClassNotFoundException, SQLException, ParseException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        ArrayList<searchallmaterialdata> as=new ArrayList<searchallmaterialdata>();
        searchallmaterialdata samd;
        searchallmaterialresult result = new searchallmaterialresult();

        String sql1 = "SELECT * ";
        String sql2 = "SELECT count(*) as num ";

        String sql;
        String sql_end = " limit ?,?";

        int num = 1;
        int codedmarking_p=0;
        int matlname_p=0;
        int matlname_id=0;
        int designation_p=0;
        int designation_id=0;
        int spec_p=0;
        int millunit_p=0;
        int millunit_id=0;
        int status_p=0;
        int indate_p=0;

        int user_rid=0;
        int warrantysitu_rid=0;
        int matlname_rid=0;
        int matlstand_rid=0;
        int modelstand_rid=0;
        int supplier_rid=0;
        int designation_rid=0;
        int millunit_rid=0;
        int heatcondi_rid=0;
        int impacttemp_rid=0;
        int bendangle_rid=0;
        int utclass_rid=0;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs=null;
        ResultSet rs1=null;

        int i = 0;


        try{
            sql="FROM putmaterial WHERE 1=1 ";
            if(!(sp.getSearchdata().getCodedmarking()==null||sp.getSearchdata().getCodedmarking().equals(""))){
                sql=sql+"and codedmarking = ? ";
                codedmarking_p=1;
            }
            if(!(sp.getSearchdata().getMatlname()==null || sp.getSearchdata().getMatlname().equals(""))){
                sql=sql+"and matlname_id_matlname=? ";
                matlname_p=1;
            }
            if(!(sp.getSearchdata().getDesignation()==null || sp.getSearchdata().getDesignation().equals(""))){
                sql=sql+"and contraststand_id_designation in (select id from contraststand where designation = ?) ";
                designation_p=1;
            }
            if(!(sp.getSearchdata().getSpec()==null || sp.getSearchdata().getSpec().equals(""))){
                sql=sql+"and spec=? ";
                spec_p=1;
            }
            if(!(sp.getSearchdata().getMillunit()==null || sp.getSearchdata().getMillunit().equals(""))){
                sql=sql+"and millunit_id_millunit=? ";
                millunit_p=1;
            }
            if(!(sp.getSearchdata().getStatus()==-1)){
                sql=sql+"and status=? ";
                status_p=1;
            }
            if(!(sp.getSearchdata().getIndate()==null || sp.getSearchdata().getIndate().equals(""))){
                sql=sql+"and indate = ? ";
                indate_p=1;
            }

            if(matlname_p==1){
                ps=conn.prepareStatement("SELECT * FROM matlname WHERE matlname=?");
                ps.setString(1,sp.getSearchdata().getMatlname());
                rs=ps.executeQuery();
                while (rs.next()){
                    matlname_id = rs.getInt("id");
                }
                rs.close();
                ps.close();
            }
            if(millunit_p==1){
                ps=conn.prepareStatement("SELECT * FROM millunit WHERE millunit=?");
                ps.setString(1,sp.getSearchdata().getMillunit());
                rs=ps.executeQuery();
                while (rs.next()){
                    millunit_id = rs.getInt("id");
                }
                rs.close();
                ps.close();
            }
            sql+=" ORDER BY codedmarking DESC";
            ps1 = conn.prepareStatement(sql2 + sql);
            sql = sql + sql_end;

            ps=conn.prepareStatement(sql1 + sql);
            if(codedmarking_p==1){
                ps.setString(num,sp.getSearchdata().getCodedmarking());
                ps1.setString(num,sp.getSearchdata().getCodedmarking());
                num++;
            }
            if(matlname_p==1){
                ps.setInt(num,matlname_id);
                ps1.setInt(num,matlname_id);
                num++;
            }
            if(designation_p==1){
                ps.setString(num,sp.getSearchdata().getDesignation());
                ps1.setString(num,sp.getSearchdata().getDesignation());
                num++;
            }
            if(spec_p==1){
                ps.setString(num,sp.getSearchdata().getSpec());
                ps1.setString(num,sp.getSearchdata().getSpec());
                num++;
            }
            if(millunit_p==1){
                ps.setInt(num,millunit_id);
                ps1.setInt(num,millunit_id);
                num++;
            }
            if(status_p==1){
                ps.setInt(num,sp.getSearchdata().getStatus());
                ps1.setInt(num,sp.getSearchdata().getStatus());
                num++;
            }
            if(indate_p==1){
                ps.setString(num,sp.getSearchdata().getIndate());
                ps1.setString(num,sp.getSearchdata().getIndate());
                num++;
            }

            ps.setInt(num,(sp.getPageindex()-1)*sp.getPagesize());
            num++;
            ps.setInt(num,sp.getPagesize());
            num++;

            rs1 = ps1.executeQuery();
            if(rs1.next()){
                result.setTotal(rs1.getInt("num"));
            }
            rs1.close();
            ps1.close();


            rs=ps.executeQuery();
            while(rs.next()){
                samd=new searchallmaterialdata();
                samd.setStatus(rs.getInt("status"));
                samd.setCodedmarking(rs.getString("codedmarking"));
                samd.setNote(rs.getString("note"));
                samd.setIndate(sdf.format(rs.getDate("indate")));
                samd.setWarrantyno(rs.getString("warrantyno"));
                samd.setSpec(rs.getString("spec"));
                samd.setQty(rs.getString("qty"));
                samd.setUnit(rs.getString("unit"));
                samd.setDimension(rs.getString("dimension"));
                samd.setHeatbatchno(rs.getString("heatbatchno"));
                samd.setC(rs.getString("c"));
                samd.setSi(rs.getString("si"));
                samd.setMn(rs.getString("mn"));
                samd.setCu(rs.getString("cu"));
                samd.setNi(rs.getString("ni"));
                samd.setCr(rs.getString("cr"));
                samd.setMo(rs.getString("mo"));
                samd.setNb(rs.getString("nb"));
                samd.setV(rs.getString("v"));
                samd.setTi(rs.getString("ti"));
                samd.setAls(rs.getString("als"));
                samd.setAlt(rs.getString("alt"));
                samd.setN(rs.getString("n"));
                samd.setFe(rs.getString("fe"));
                samd.setMg(rs.getString("mg"));
                samd.setZn(rs.getString("zn"));
                samd.setB(rs.getString("b"));
                samd.setW(rs.getString("w"));
                samd.setSb(rs.getString("sb"));
                samd.setAl(rs.getString("al"));
                samd.setZr(rs.getString("zr"));
                samd.setCa(rs.getString("ca"));
                samd.setBe(rs.getString("be"));
                samd.setP(rs.getString("p"));
                samd.setS(rs.getString("s"));
                samd.setRel1(rs.getString("rel1"));
                samd.setRel2(rs.getString("rel2"));
                samd.setRm1(rs.getString("rm1"));
                samd.setRm2(rs.getString("rm2"));
                samd.setElong1(rs.getString("elong1"));
                samd.setElong2(rs.getString("elong2"));
                samd.setHardness1(rs.getString("hardness1"));
                samd.setHardness2(rs.getString("hardness2"));
                samd.setHardness3(rs.getString("hardness3"));
                samd.setImpactp1(rs.getString("impactp1"));
                samd.setImpactp2(rs.getString("impactp2"));
                samd.setImpactp3(rs.getString("impactp3"));
                samd.setBendaxdia(rs.getString("bendaxdia"));
                user_rid=rs.getInt("user_id");
                warrantysitu_rid=rs.getInt("warrantystatus_id_certsitu");
                matlname_rid=rs.getInt("matlname_id_matlname");
                matlstand_rid=rs.getInt("contraststand_id_matlstand");
                modelstand_rid=rs.getInt("modelstand_id_modelstand");
                supplier_rid=rs.getInt("supplier_id_supplier");
                designation_rid=rs.getInt("contraststand_id_designation");
                millunit_rid=rs.getInt("millunit_id_millunit");
                samd.setHeatcondi(rs.getString("heattreatcondition_id_heatcondi"));
                samd.setImpacttemp(rs.getString("bending_id_impacttemp"));
                samd.setBendangle(rs.getString("bending_id_bendangle"));
                utclass_rid=rs.getInt("bending_id_utclass");

                ps1=conn.prepareStatement("SELECT * FROM userform WHERE id=?");
                ps1.setInt(1,user_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setUser(rs1.getString("username"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM warrantystatus WHERE id=?");
                ps1.setInt(1,warrantysitu_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setWarrantysitu(rs1.getString("certsitu"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM matlname WHERE id=?");
                ps1.setInt(1,matlname_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setMatlname(rs1.getString("matlname"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps1.setInt(1,matlstand_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setMatlstand(rs1.getString("matlstand"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM modelstand WHERE id=?");
                ps1.setInt(1,modelstand_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setModelstand(rs1.getString("modelstand"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM supplier WHERE id=?");
                ps1.setInt(1,supplier_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setSupplier(rs1.getString("supplier"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps1.setInt(1,designation_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setDesignation(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM millunit WHERE id=?");
                ps1.setInt(1,millunit_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setMillunit(rs1.getString("millunit"));
                }
                rs1.close();
                ps1.close();


                ps1=conn.prepareStatement("SELECT * FROM bending WHERE id=?");
                ps1.setInt(1,utclass_rid);
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    samd.setUtclass(rs1.getInt("utclass"));
                }
                rs1.close();
                ps1.close();

                as.add(samd);
                samd = null;
            }
            rs.close();
            ps.close();
            result.setResult("success");
            result.setData(as);
        }catch (Exception e){
            result.setResult("fail");
        }
        as = null;
        conn.close();
        return result;
    }
}
