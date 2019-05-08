package start.putpregaumeatable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;
import start.liststring.liststring;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class putpregaumeatable {                                        //提交计量台帐输入
    @RequestMapping(value = "putpregaumeatable")
    public @ResponseBody putpregaumeatableresult putpregaumeatable(@RequestBody putpregaumeatablepost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(pp.getExitdate().equals("")){
            pp.setExitdate(null);
        }

        putpregaumeatableresult result = new putpregaumeatableresult();

        java.sql.Date date= new java.sql.Date(new java.util.Date().getTime());

        liststring ls = new liststring();
        String specialist = ls.listtostring(pp.getSpecialist(),"#");

        int f =0;


        try {
            ps = conn.prepareStatement("SELECT * FROM pregaumeatable where exitno = ?");
            ps.setString(1,pp.getExitno());
            rs = ps.executeQuery();
            while (rs.next()){
                if(getDaySub(pp.getCalibdate(),rs.getString("recalibdate"))>=31){
                    f=1;
                    break;
                };
            }
            rs.close();
            ps.close();

            if(f!=1){
                ps = conn.prepareStatement("INSERT INTO pregaumeatable(gaugename,gaugeno,exitno,type," +
                        "measrangemin,measrangemax," +
                        "accuclass,millunit,exitdate,managlevel," +
                        "calibdate,recalibdate,specialist,calibinterval," +
                        "note,date,user) VALUES (?,?,?,?," +
                        "?,?," +
                        "?,?,?,?," +
                        "?,?,?,?," +
                        "?,?,?)");
                ps.setString(1,pp.getGaugename());
                ps.setString(2,pp.getGaugeno());
                ps.setString(3,pp.getExitno());
                ps.setString(4,pp.getType());
                ps.setDouble(5,pp.getMeasrangemin());
                ps.setDouble(6,pp.getMeasrangemax());
                ps.setString(7,pp.getAccuclass());
                ps.setString(8,pp.getMillunit());
                ps.setString(9,pp.getExitdate());
                ps.setString(10,pp.getManaglevel());
                ps.setString(11,pp.getCalibdate());
                ps.setString(12,pp.getRecalibdate());
                ps.setString(13,specialist);
                ps.setString(14,pp.getCalibinterval());
                ps.setString(15,pp.getNote());
                ps.setDate(16,date);
                ps.setString(16,pp.getUser());
                ps.executeUpdate();
                ps.close();
                result.setResult("success");
            }else {
                result.setResult("fail");
            }


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
