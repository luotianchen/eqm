package start.searchbyprodno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchbyprodno {                                               //通过产品编号查询发放记录（从受压元件表查询）
    @RequestMapping(value = "searchbyprodno")
    public @ResponseBody searchbyprodnoresult searchbyprodno(@RequestBody searchbyprodnopost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;

        String sql=null;
        int prodno_p=0;
        int audit_p=0;
        int status_p=0;
        int num=1;
        String prodno=null;

        int spartname_id=0;
        int designation_id=0;
        int picker_id=0;
        int issuematl_id=0;
        int dwgno_id=0;
        int prodname_id=0;

        searchbyprodnoresult result = new searchbyprodnoresult();
        searchbyprodnodata data=null;
        ArrayList<searchbyprodnodata> as = new ArrayList<searchbyprodnodata>();

        try {
            sql = "SELECT * FROM pressureparts WHERE 1=1 ";
            if(!(sp.getProdno()==null || sp.getProdno().equals(""))){
                sql=sql+"and prodno = ? ";
                prodno_p=1;
            }
            if(!(sp.getAudit()==null || sp.getAudit().equals(""))){
                sql=sql+"and audit = ? ";
                audit_p=1;
            }
            if(!(sp.getStatus()==-1)){
                sql=sql+"and status = ? ";
                status_p=1;
            }

            ps = conn.prepareStatement(sql);
            if(prodno_p==1){
                ps.setString(num,sp.getProdno());
                num++;
            }
            if(audit_p==1){
                ps.setString(num,sp.getAudit());
                num++;
            }
            if(status_p==1){
                ps.setInt(num,sp.getStatus());
                num++;
            }
            rs=ps.executeQuery();
            while (rs.next()){
                data = new searchbyprodnodata();
                prodno = rs.getString("prodno");
                data.setSpec(rs.getString("spec"));
                data.setDimension(rs.getString("dimension"));
                data.setPartno(rs.getString("partno"));
                data.setQty(rs.getString("qty"));
                data.setCodedmarking(rs.getString("codedmarking"));
                data.setIssuedate(sdf.format(rs.getDate("issuedate")));
                data.setIspresspart(rs.getString("ispresspart"));
                data.setWeldno(rs.getString("weldno"));
                data.setReturnqty(rs.getInt("returnqty"));
                data.setNote(rs.getString("note"));
                spartname_id=rs.getInt("parts_id_name");
                designation_id=rs.getInt("contraststand_id_designation");
                picker_id=rs.getInt("workshopperson_id_name");
                issuematl_id=rs.getInt("warehouseperson_id_name");

                ps1=conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ?");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setHeatbatchno(rs1.getString("heatbatchno"));
                }

                ps1=conn.prepareStatement("SELECT * FROM parts WHERE id=?");
                ps1.setInt(1,spartname_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setSpartname(rs1.getString("partsname"));
                }
                rs1.close();
                ps1.close();

                ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps1.setInt(1,designation_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setDesignation(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();

                ps1=conn.prepareStatement("SELECT * FROM workshopperson WHERE id=?");
                ps1.setInt(1,picker_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setPicker(rs1.getString("username"));
                }
                rs1.close();
                ps1.close();

                ps1=conn.prepareStatement("SELECT * FROM warehouseperson WHERE id=?");
                ps1.setInt(1,issuematl_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setIssuematl(rs1.getString("username"));
                }
                rs1.close();
                ps1.close();

                as.add(data);
            }
            rs.close();
            ps.close();

            Collections.reverse(as);                                          //将list1倒序

            ps=conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno=?");
            ps.setString(1,prodno);
            rs=ps.executeQuery();
            while (rs.next()){
                dwgno_id=rs.getInt("proparlist_id_dwgno");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM proparlist WHERE id=?");
            ps.setInt(1,dwgno_id);
            rs=ps.executeQuery();
            while (rs.next()){
                result.setDwgno(rs.getString("dwgno"));
                prodname_id=rs.getInt("productname_id_prodname");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM productname WHERE id=?");
            ps.setInt(1,prodname_id);
            rs=ps.executeQuery();
            while (rs.next()){
                result.setProdname(rs.getString("prodname"));
            }
            rs.close();
            ps.close();

            result.setData(as);
            result.setResult("success");

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
