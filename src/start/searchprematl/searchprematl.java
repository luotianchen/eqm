package start.searchprematl;

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

import static start.excel.excel.putsheet;

@CrossOrigin
@Controller
public class searchprematl {                                //受压元件使用材料一览表
    @RequestMapping(value = "searchprematl")
    public @ResponseBody searchprematlresult searchprematl(@RequestBody searchprematlpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        searchprematlresult result = new searchprematlresult();
        ArrayList<searchprematldata> as = new ArrayList<searchprematldata>();
        searchprematldata data = null;

        int dwgno_id = 0;
        int prodname_id = 0;
        int issuematl_id = 0;
        int spartname_id = 0;
        int designation_id = 0;
        int millunit_id = 0;
        int heatcondi_id = 0;
        String codedmarking = null;
        String dwgno = null;

//        try {
            ps = conn.prepareStatement("SELECT * FROM prenotiform where prodno = ?");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                dwgno = rs.getString("dwgno");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit=1");
            ps.setString(1,dwgno);
            rs = ps.executeQuery();
            if(rs.next()){
                prodname_id = rs.getInt("productname_id_prodname");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM productname WHERE id = ?");
            ps.setInt(1,prodname_id);
            rs = ps.executeQuery();
            if(rs.next()){
                result.setProdname(rs.getString("prodname"));
                result.setEname(rs.getString("ename"));
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                result.setIssuedate(sdf.format(rs.getDate("exworkdate")));
            }
            rs.close();

            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status = 1");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                codedmarking = rs.getString("codedmarking");
                result.setIssuematl(rs.getString("user"));
            }
            rs.close();
            ps.close();


            ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status=1");
            ps.setString(1,codedmarking);
            rs = ps.executeQuery();
            if(rs.next()){
                result.setAudit_user(rs.getString("audit_user"));
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status=1 group by partno,codedmarking ORDER BY partno is null,partno ASC ");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchprematldata();
                data.setSpec(rs.getString("spec"));
                data.setPartno(rs.getString("partno"));
                data.setCodedmarking(rs.getString("codedmarking"));
                spartname_id = rs.getInt("parts_id_name");

                ps1 = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
                ps1.setInt(1,spartname_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data.setSpartname(rs1.getString("partsname"));
                    data.setEtrans(rs1.getString("enpartsname"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ?");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    designation_id = rs1.getInt("contraststand_id_designation");
                    millunit_id = rs1.getInt("millunit_id_millunit");
                    data.setHeatcondi(rs1.getString("heattreatcondition_id_heatcondi"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                ps1.setInt(1,designation_id);
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    data.setDesignation(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM millunit WHERE id = ?");
                ps1.setInt(1,millunit_id);
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    data.setMillunit(rs1.getString("millunit"));
                    data.setMillunitename(rs1.getString("millunitename"));
                }
                rs1.close();
                ps1.close();


                as.add(data);
            }
            result.setData(as);
            result.setResult("success");
//        }catch (Exception e){
//            result.setResult("fail");
//        }
        conn.close();
        return result;
    }
}
