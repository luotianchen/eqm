package start.searchmeabyexi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@CrossOrigin
public class searchmeabyexi {                                   //根据出厂编号查询测量范围
    @RequestMapping(value = "searchmeabyexi")
    public @ResponseBody searchmeabyexiresult searchmeabyexi(@RequestBody searchmeabyexipost sp) throws ClassNotFoundException, SQLException, ParseException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchmeabyexiresult result = new searchmeabyexiresult();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d1 = sdf.parse(sp.getDate());
        java.sql.Date d = new java.sql.Date(d1.getTime());

        try {
            ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE exitno = ? AND status=1 AND ? BETWEEN calibdate AND recalibdate");
            ps.setString(1,sp.getExitno());
            ps.setDate(2,d);
            rs = ps.executeQuery();
            if (rs.next()){
                String dx = rs.getString("calibdate");
                result.setMax(rs.getString("measrangemax"));
                result.setMin(rs.getString("measrangemin"));
                rs.close();
                rs = ps.executeQuery();
                while (rs.next()){
                    if(getDaySub(dx,rs.getString("calibdate"))>0){
                        result.setMax(rs.getString("measrangemax"));
                        result.setMin(rs.getString("measrangemin"));
                        dx = rs.getString("calibdate");
                    }
                }
                result.setResult("success");
            }else {
                result.setResult("fail");
            }
            rs.close();
            ps.close();

        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }

    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (ParseException e)
        {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }
}
