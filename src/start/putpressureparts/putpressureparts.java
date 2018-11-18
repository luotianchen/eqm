package start.putpressureparts;

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
import java.util.UUID;

@CrossOrigin
@Controller
public class putpressureparts {                                                                     //提交材料发放记录
    @RequestMapping(value = "putpressureparts")
    public @ResponseBody putpressurepartsresult putpressureparts(@RequestBody putpressurepartspost pp) throws ClassNotFoundException, SQLException, ParseException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putpressurepartsresult result = new putpressurepartsresult();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int spartname_id=0;
        int designation_id=0;
        int picker_id=0;
        String uid = UUID.randomUUID().toString();                                  //生成随机字符串
        try {
            for(int i=0;i<pp.getData().size();i++){
                java.util.Date d1 = sdf.parse(pp.getData().get(i).getIssuedate());
                java.sql.Date d = new java.sql.Date(d1.getTime());


                ps = conn.prepareStatement("SELECT * FROM parts WHERE partsname=?");
                ps.setString(1,pp.getData().get(i).getSpartname());
                rs=ps.executeQuery();
                while (rs.next()){
                    spartname_id=rs.getInt("id");
                }
                rs.close();
                ps.close();

                ps = conn.prepareStatement("SELECT * FROM contraststand WHERE designation=?");
                ps.setString(1,pp.getData().get(i).getDesignation());
                rs=ps.executeQuery();
                while (rs.next()){
                    designation_id=rs.getInt("id");
                }
                rs.close();
                ps.close();

                ps = conn.prepareStatement("SELECT * FROM workshopperson WHERE name=?");
                ps.setString(1,pp.getData().get(i).getPicker());
                rs=ps.executeQuery();
                while (rs.next()){
                    picker_id=rs.getInt("id");
                }
                rs.close();
                ps.close();

                ps = conn.prepareStatement("INSERT INTO pressureparts " +
                        "(prodno,parts_id_name,spec,dimension,partno," +
                        "contraststand_id_designation,qty,codedmarking,issuedate,workshopperson_id_name,note,audit)" +
                        "value (?,?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,pp.getProdno());
                ps.setInt(2,spartname_id);
                ps.setString(3,pp.getData().get(i).getSpec());
                ps.setString(4,pp.getData().get(i).getDimension());
                ps.setString(5,pp.getData().get(i).getPartno());
                ps.setInt(6,designation_id);
                ps.setString(7,pp.getData().get(i).getQty());
                ps.setString(8,pp.getData().get(i).getCodedmarking());
                ps.setDate(9,d);
                ps.setInt(10,picker_id);
                ps.setString(11,pp.getData().get(i).getNote());
                ps.setString(12,uid);
                ps.executeUpdate();
                ps.close();

            }
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }

        return result;
    }
}
