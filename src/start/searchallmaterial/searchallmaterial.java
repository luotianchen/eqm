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
            sql="SELECT * FROM putmaterial WHERE 1=1 ";
            if(!(sp.getSearchdata().getCodedmarking()==null||sp.getSearchdata().getCodedmarking().equals(""))){
                sql=sql+"and codedmarking = ? ";
                codedmarking_p=1;
            }
            if(!(sp.getSearchdata().getMatlname()==null || sp.getSearchdata().getMatlname().equals(""))){
                sql=sql+"and matlname_id_matlname=? ";
                matlname_p=1;
            }
            if(!(sp.getSearchdata().getDesignation()==null || sp.getSearchdata().getDesignation().equals(""))){
                sql=sql+"and contraststand_id_designation=? ";
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
            if(designation_p==1){
                ps=conn.prepareStatement("SELECT * FROM contraststand WHERE designation=?");
                ps.setString(1,sp.getSearchdata().getDesignation());
                rs=ps.executeQuery();
                while (rs.next()){
                    designation_id = rs.getInt("id");
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

            sql = sql + sql_end;

            ps=conn.prepareStatement(sql);
            System.out.println(sql);
            if(codedmarking_p==1){
                ps.setString(num,sp.getSearchdata().getCodedmarking());
                num++;
            }
            if(matlname_p==1){
                ps.setInt(num,matlname_id);
                num++;
            }
            if(designation_p==1){
                ps.setInt(num,designation_id);
                num++;
            }
            if(spec_p==1){
                ps.setString(num,sp.getSearchdata().getSpec());
                num++;
            }
            if(millunit_p==1){
                ps.setInt(num,millunit_id);
                num++;
            }
            if(status_p==1){
                ps.setInt(num,sp.getSearchdata().getStatus());
                num++;
            }
            if(indate_p==1){
                ps.setString(num,sp.getSearchdata().getIndate());
                num++;
            }

            ps.setInt(num,(sp.getPageindex()-1)*sp.getPagesize());
            num++;
            ps.setInt(num,sp.getPagesize());
            num++;


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
                samd.setC(rs.getDouble("c"));
                samd.setSi(rs.getDouble("si"));
                samd.setMn(rs.getDouble("mn"));
                samd.setCu(rs.getDouble("cu"));
                samd.setNi(rs.getDouble("ni"));
                samd.setCr(rs.getDouble("cr"));
                samd.setMo(rs.getDouble("mo"));
                samd.setNb(rs.getDouble("nb"));
                samd.setV(rs.getDouble("v"));
                samd.setTi(rs.getDouble("ti"));
                samd.setAls(rs.getDouble("als"));
                samd.setAlt(rs.getDouble("alt"));
                samd.setN(rs.getDouble("n"));
                samd.setFe(rs.getDouble("fe"));
                samd.setMg(rs.getDouble("mg"));
                samd.setZn(rs.getDouble("zn"));
                samd.setB(rs.getDouble("b"));
                samd.setW(rs.getDouble("w"));
                samd.setSb(rs.getDouble("sb"));
                samd.setAl(rs.getDouble("al"));
                samd.setZr(rs.getDouble("zr"));
                samd.setCa(rs.getDouble("ca"));
                samd.setBe(rs.getDouble("be"));
                samd.setP(rs.getDouble("p"));
                samd.setS(rs.getDouble("s"));
                samd.setRel1(rs.getInt("rel1"));
                samd.setRel2(rs.getInt("rel2"));
                samd.setRm1(rs.getInt("rm1"));
                samd.setRm2(rs.getInt("rm2"));
                samd.setElong1(rs.getDouble("elong1"));
                samd.setElong2(rs.getDouble("elong2"));
                samd.setHardness1(rs.getString("hardness1"));
                samd.setHardness2(rs.getString("hardness2"));
                samd.setHardness3(rs.getString("hardness3"));
                samd.setImpactp1(rs.getInt("impactp1"));
                samd.setImpactp2(rs.getInt("impactp2"));
                samd.setImpactp3(rs.getInt("impactp3"));
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
            }
            rs.close();
            ps.close();
            result.setResult("success");
            result.setTotal(as.size());
            Collections.reverse(as);                                          //将list倒序
            result.setData(as);
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
