package start.searchpressurepart;

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
public class searchpressurepart {                                                       //受压元件查询
    @RequestMapping(value = "searchpressurepart")
    public @ResponseBody searchpressurepartresult searchpressurepart(@RequestBody searchpressurepartpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;

        searchpressurepartresult result = new searchpressurepartresult();
        searchpressurepartdata1 data1 = null;
        searchpressurepartdata2 data2 = null;
        ArrayList<searchpressurepartdata1> as1 = new ArrayList<searchpressurepartdata1>();
        ArrayList<searchpressurepartdata2> as2 = new ArrayList<searchpressurepartdata2>();
        ArrayList<searchpressurepartdata1> as1_q = null;
        ArrayList<searchpressurepartdata2> as2_q = null;

        int designation1_id=0;
        int spartname_id=0;
        int designation2_id=0;
        int modelstand2_id=0;

        try {
            ps= conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status=1");                          //先查询受压原件表data2，查询出入库编号用来查询入库表data1
            ps.setString(1,sp.getProdno());
            rs=ps.executeQuery();
            while(rs.next()){
                data2 = new searchpressurepartdata2();
                data2.setCodedmarking(rs.getString("codedmarking"));
                data2.setSpec(rs.getString("spec"));
                data2.setIssuedate(sdf.format(rs.getDate("issuedate")));
                data2.setQty(rs.getString("qty"));
                designation1_id=rs.getInt("contraststand_id_designation");
                spartname_id=rs.getInt("parts_id_name");

                ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps1.setInt(1,designation1_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data2.setDesignation(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM parts WHERE id=?");
                ps1.setInt(1,spartname_id);
                rs1=ps1.executeQuery();
                while (rs1.next()){
                    data2.setSpartname(rs1.getString("partsname"));
                }
                rs1.close();
                ps1.close();

                ps1=conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status=1");              //查到入库编号后查询入库信息
                ps1.setString(1,data2.getCodedmarking());
                rs1 = ps1.executeQuery();
                if (rs1.next()){
                    data1 = new searchpressurepartdata1();
                    data1.setCodedmarking(rs1.getString("codedmarking"));
                    data1.setSpec(rs1.getString("spec"));
                    data1.setIndate(sdf.format(rs1.getDate("indate")));
                    data1.setQty("qty");
                    designation2_id=rs1.getInt("contraststand_id_designation");
                    modelstand2_id=rs1.getInt("modelstand_id_modelstand");

                    ps2 = conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                    ps2.setInt(1,designation2_id);
                    rs2=ps2.executeQuery();
                    while (rs2.next()){
                        data1.setDesignation(rs2.getString("designation"));
                    }
                    rs2.close();
                    ps2.close();

                    ps2 = conn.prepareStatement("SELECT * FROM modelstand WHERE id=?");
                    ps2.setInt(1,modelstand2_id);
                    rs2=ps2.executeQuery();
                    while (rs2.next()){
                        data1.setModelstand(rs2.getString("modelstand"));
                    }
                    rs2.close();
                    ps2.close();

                    as1.add(data1);
                    data1 = null;
                }else {
                    as1.add(data1);
                }
                rs1.close();
                ps1.close();

                as2.add(data2);
                data2 = null;
            }
            rs.close();
            ps.close();
            conn.close();

            if(as1.size()>as2.size()){
                result.setTotal(as1.size());
            }else {
                result.setTotal(as2.size());
            }

            Collections.reverse(as1);                                          //将list1倒序
            int as1_size;
            as1_size=as1.size();
            if(as1_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("success");
            }else if((as1_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as1_q=new ArrayList<searchpressurepartdata1>(as1.subList(((sp.getPageindex()-1)*sp.getPagesize()),as1_size));
                result.setResult("success");
                result.setData1(as1_q);

            }else {
                as1_q=new ArrayList<searchpressurepartdata1>(as1.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData1(as1_q);

            }

            Collections.reverse(as2);                                          //将list2倒序
            int as2_size;
            as2_size=as2.size();
            if(as2_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("success");
            }else if((as2_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as2_q=new ArrayList<searchpressurepartdata2>(as2.subList(((sp.getPageindex()-1)*sp.getPagesize()),as2_size));
                result.setResult("success");
                result.setData2(as2_q);

            }else {
                as2_q=new ArrayList<searchpressurepartdata2>(as2.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData2(as2_q);

            }

        }catch (Exception e){
            result.setResult("fail");
        }
        as1 = null;
        as2 = null;
        as1_q = null;
        as2_q = null;
        return result;
    }
}
