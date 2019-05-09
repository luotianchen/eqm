package start.searchsubstitutestatus;

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
public class searchsubstitutestatus {                                               //查询所有未审核代用记录
    @RequestMapping(value = "searchsubstitutestatus")
    public @ResponseBody searchsubstitutestatusresult searchsubstitutestatus(@RequestBody searchsubstitutestatuspost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        String sql = null;
        int b_f = 0;
        int c_f = 0;

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        searchsubstitutestatusresult result = new searchsubstitutestatusresult();
        searchsubstitutestatusdata data = null;
        ArrayList<searchsubstitutestatusdata> as = new ArrayList<searchsubstitutestatusdata>();

//        try {
            sql = "SELECT distinct audit,date,prodno,user,why,status_b,status_c FROM matlsubstitution ";
            if(!(sp.getDesign_status()==0&&sp.getMatl_status()==0&&sp.getWelding_status()==0&&sp.getProcess_status()==0&&sp.getInspection_status()==0
            &&sp.getStatus_b()==0&&sp.getStatus_c()==0)){
                sql =sql + "WHERE 1=2 ";
            }
            if(sp.getDesign_status()==1){
                sql = sql +" OR design_status = 0";
            }
            if(sp.getMatl_status()==1){
                sql = sql +" OR matl_status = 0";
            }
            if(sp.getWelding_status()==1){
                sql = sql +" OR welding_status = 0";
            }
            if(sp.getProcess_status()==1){
                sql = sql +" OR process_status = 0";
            }
            if(sp.getInspection_status()==1){
                sql = sql +" OR inspection_status = 0";
            }
            if(sp.getStatus_b()==1){
                sql = sql +" OR status_b = 0";
            }
            if(sp.getStatus_c()==1){
                sql = sql +" OR status_c = 0";
            }
            ps =conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt("status_b"));
                data =new searchsubstitutestatusdata();
                ps1= conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
                ps1.setString(1,rs.getString("audit"));
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    if(rs1.getString("type").equals("B")){
                        b_f=1;
                    }
                    if(rs1.getString("type").equals("C")){
                        c_f=1;
                    }
                }
                rs1.close();
                ps1.close();

                if(b_f==0 && c_f==0){
                    data.setAudit(rs.getString("audit"));
                    data.setDate(sdf.format(rs.getDate("date")));
                    data.setProdno(rs.getString("prodno"));
                    data.setUser(rs.getString("user"));
                    data.setWhy(rs.getString("why"));
                    as.add(data);
                }else {
                    if(b_f==1 && c_f==1){
                        if(sp.getStatus_b()==1 || sp.getStatus_c() == 1){
                            data.setAudit(rs.getString("audit"));
                            data.setDate(sdf.format(rs.getDate("date")));
                            data.setProdno(rs.getString("prodno"));
                            data.setUser(rs.getString("user"));
                            data.setWhy(rs.getString("why"));
                            as.add(data);
                        }
                    }else {
                        if(b_f==1){
                            if(sp.getStatus_b()==1){
                                if(rs.getInt("status_b")==0){
                                    data.setAudit(rs.getString("audit"));
                                    data.setDate(sdf.format(rs.getDate("date")));
                                    data.setProdno(rs.getString("prodno"));
                                    data.setUser(rs.getString("user"));
                                    data.setWhy(rs.getString("why"));
                                    as.add(data);
                                }

                            }
                        }
                        if(c_f==1){
                            if(sp.getStatus_c()==1){
                                if (rs.getInt("status_c")==0){
                                    data.setAudit(rs.getString("audit"));
                                    data.setDate(sdf.format(rs.getDate("date")));
                                    data.setProdno(rs.getString("prodno"));
                                    data.setUser(rs.getString("user"));
                                    data.setWhy(rs.getString("why"));
                                    as.add(data);
                                }

                            }
                        }
                    }
                }
            }
            rs.close();
            ps.close();
            Collections.reverse(as);                                          //将list倒序
            result.setData(as);
            result.setResult("success");
//        }catch (Exception e){
//            result.setResult("fail");
//        }
        conn.close();
        return result;
    }
}
