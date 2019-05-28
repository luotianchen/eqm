package start.putpressurepartscache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@CrossOrigin
@Controller
public class Putpressurepartscache {                                         //发放记录提交缓存表                                                         //提交材料发放记录
    @RequestMapping(value = "putpressurepartscache")
    public @ResponseBody
    putpressurepartscacheresult putpressurepartscache(@RequestBody putpressurepartscachepost pp) throws ClassNotFoundException, SQLException, ParseException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;
        PreparedStatement ps1 = null ;
        ResultSet rs1 = null;

        putpressurepartscacheresult result = new putpressurepartscacheresult();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String uid = UUID.randomUUID().toString();                                  //生成随机字符串


        try {
            if(pp.getData().size() == 0){
                result.setResult("fail");
            }else {
                ps = conn.prepareStatement("SELECT * FROM pressurepartscache WHERE prodno = ?");
                ps.setString(1, pp.getProdno());
                rs = ps.executeQuery();
                if (rs.next()) {
                    ps1 = conn.prepareStatement("DELETE FROM pressurepartscache WHERE prodno = ?");
                    ps1.setString(1, pp.getProdno());
                    ps1.executeUpdate();
                    ps1.close();
                }
                rs.close();
                ps.close();
                for (int i = 0; i < pp.getData().size(); i++) {
                    java.util.Date d1 = sdf.parse(pp.getData().get(i).getIssuedate());
                    Date d = new Date(d1.getTime());


                    ps = conn.prepareStatement("INSERT INTO pressurepartscache " +
                            "(prodno,parts_id_name,spec,dimension,partno," +
                            "contraststand_id_designation,qty,codedmarking,issuedate,workshopperson_id_name,note,audit," +
                            "ispresspart,weldno,returnqty,warehouseperson_id_name)" +
                            "values (?,?,?,?,?,?,?,?,?,?,?,?," +
                            "?,?,?,?)");
                    ps.setString(1, pp.getProdno());
                    ps.setString(2, pp.getData().get(i).getSpartname());
                    ps.setString(3, pp.getData().get(i).getSpec());
                    ps.setString(4, pp.getData().get(i).getDimension());
                    ps.setString(5, pp.getData().get(i).getPartno());
                    ps.setString(6, pp.getData().get(i).getDesignation());
                    ps.setString(7, pp.getData().get(i).getQty());
                    ps.setString(8, pp.getData().get(i).getCodedmarking());
                    ps.setDate(9, d);
                    ps.setString(10, pp.getData().get(i).getPicker());
                    ps.setString(11, pp.getData().get(i).getNote());
                    ps.setString(12, uid);
                    ps.setString(13, pp.getData().get(i).getIspresspart());
                    ps.setString(14, pp.getData().get(i).getWeldno());
                    ps.setInt(15, pp.getData().get(i).getReturnqty());
                    ps.setString(16, pp.getData().get(i).getIssuematl());
                    ps.executeUpdate();
                    ps.close();

                }
                result.setResult("success");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
