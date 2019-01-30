package start.searchstatusthrough;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchstatusthrough {                                                      //查询所有已审核代用记录
    @RequestMapping(value = "searchstatusthrough")
    public @ResponseBody searchstatusthroughresult searchstatusthrough(@RequestBody searchstatusthroughpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchstatusthroughdata data = null;
        ArrayList<searchstatusthroughdata> as = new ArrayList<searchstatusthroughdata>();
        ArrayList<searchstatusthroughdata> as_q = null;
        searchstatusthroughresult result = new searchstatusthroughresult();

        String audit_p=null;
        int type_B = 0;
        int type_C = 0;
        String sql1 = "SELECT * FROM matlsubstitution WHERE design_status=1 AND matl_status=1 AND welding_status=1 AND process_status=1 AND inspection_status=1 ";
        String sql2 = "group by audit,id";

        //try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql1 = sql1 + "AND prodno = ? ";
            }
            ps = conn.prepareStatement(sql1+sql2);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                ps.setString(1,sp.getProdno());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
                ps1.setString(1,rs.getString("audit"));
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    if(rs1.getString("type").equals("B")){
                        type_B = 1;
                    }
                    if(rs1.getString("type").equals("C")){
                        type_C = 1;
                    }
                }
                rs1.close();
                ps1.close();

                if(type_B==1 && rs.getInt("status_b")!=1){
                    continue;
                }
                if(type_C==1 && rs.getInt("status_c")!=1){
                    continue;
                }
                data = new searchstatusthroughdata();
                data.setAudit(rs.getString("audit"));
                data.setDate(rs.getString("date"));
                data.setProdno(rs.getString("prodno"));
                data.setUser(rs.getString("user"));
                data.setWhy(rs.getString("why"));
                as.add(data);
                type_B=0;
                type_B=0;
            }
            rs.close();
            ps.close();
            Collections.reverse(as);                                          //将list倒序
            int as_size;
            as_size=as.size();
            if(as_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("success");
            }else if((as_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as_q=new ArrayList<searchstatusthroughdata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),as_size));
                result.setResult("success");
                result.setData(as_q);
                result.setTotal(as_q.size());
            }else{
                as_q=new ArrayList<searchstatusthroughdata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData(as_q);
                result.setTotal(as_q.size());
            }
//        }catch (Exception e){
//            result.setResult("fail");
//        }
        conn.close();
        return result;
    }
}
