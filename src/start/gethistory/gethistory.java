package start.gethistory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Random;

@Controller
@CrossOrigin
public class gethistory {                                                       //获取历史上的今天
    @RequestMapping(value = "gethistory",method = RequestMethod.GET)
    public @ResponseBody gethistoryresult gethistoryr() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        int i = 1;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String date = df.format(new java.util.Date().getTime());

        String datev[] = date.split("-");

        for (int z = 0;z<datev.length;z++){
            System.out.println(datev[z]);
        }

        gethistoryresult result = new gethistoryresult();

        try {
            while (i==1){
                ps = conn.prepareStatement("SELECT * FROM history WHERE month = ? AND day = ?");
                System.out.println(Integer.parseInt(datev[1]) + "/n" + Integer.parseInt(datev[2]));
                ps.setInt(1,Integer.parseInt(datev[1]));
                ps.setInt(2,Integer.parseInt(datev[2]));
                rs = ps.executeQuery();
                while (rs.next()){
                    if(new Random().nextInt(50)==1){
                        i =0;
                        result.setMessage(rs.getString("title"));
                        result.setLunar(rs.getString("lunar"));
                        StringBuffer sb = new StringBuffer(rs.getString("date"));
                        sb.insert(sb.length()-4,"-");
                        sb.insert(sb.length()-2,"-");
                        result.setDate(sb.toString());
                        result.setResult("success");
                        break;
                    }
                }
                rs.close();
                ps.close();
            }

        }catch (Exception e){
            result.setResult("fail");
        }

        conn.close();
        return result;


    }
}
