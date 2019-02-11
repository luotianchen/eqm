package start.searchbyprodnocache;

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
public class Searchbyprodnocache {                                               //缓存表通过产品编号查询发放记录（从受压元件缓存表查询）
    @RequestMapping(value = "searchbyprodnocache")
    public @ResponseBody
    searchbyprodnocacheresult searchbyprodnocache(@RequestBody searchbyprodnocachepost sp) throws ClassNotFoundException, SQLException {
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

        searchbyprodnocacheresult result = new searchbyprodnocacheresult();
        searchbyprodnocachedata data=null;
        ArrayList<searchbyprodnocachedata> as = new ArrayList<searchbyprodnocachedata>();

        try {
            sql = "SELECT * FROM pressurepartscache WHERE 1=1 ";
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
                data = new searchbyprodnocachedata();
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
                data.setSpartname(rs.getString("parts_id_name"));
                data.setDesignation(rs.getString("contraststand_id_designation"));
                data.setPicker(rs.getString("workshopperson_id_name"));
                data.setIssuematl(rs.getString("warehouseperson_id_name"));

                ps1=conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ?");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data.setHeatbatchno(rs1.getString("heatbatchno"));
                }

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
