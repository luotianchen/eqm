package start.gettestno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class gettestno {                                                            //获取试验编号（testno）
    @RequestMapping(value = "gettestno")
    public @ResponseBody gettestnoresult gettestno(@RequestBody gettestnopost gp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        gettestnoresult result = new gettestnoresult();
        int num = 1;
        int f = 0;

        try {
            if(!(gp.getTestno() == null || gp.getTestno().equals(""))){
                ps = conn.prepareStatement("SELECT * FROM productplate WHERE testno=?");
                ps.setString(1,gp.getTestno());
                rs = ps.executeQuery();
                if(rs.next()){
                    result.setStatus(2);
                    while (true){
                        ps = conn.prepareStatement("SELECT * FROM productplate");
                        rs = ps.executeQuery();
                        while (rs.next()){
                            if(rs.getString("testno").equals(gtestno(num))){
                                f=1;
                                break;
                            }else {
                                f=0;
                            }
                        }
                        if(f==1){
                            num++;
                        }
                        if(f==0){
                            break;
                        }
                    }
                    result.setTestno(gtestno(num));
                }else {
                    result.setStatus(1);
                }
                result.setResult("success");
            }else {
                while (true){
                    ps = conn.prepareStatement("SELECT * FROM productplate");
                    rs = ps.executeQuery();
                    while (rs.next()){
                        if(rs.getString("testno").equals(gtestno(num))){
                            f=1;
                            break;
                        }else {
                            f=0;
                        }
                    }
                    if(f==1){
                        num++;
                    }
                    if(f==0){
                        break;
                    }
                }
                result.setResult("success");
                result.setTestno(gtestno(num));
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }

    public String gtestno(int num){
        String x = String.valueOf(new java.sql.Date(new java.util.Date().getTime()));
        String year = x.substring(2,4);
        String mo = x.substring(5,7);
        String testno = year + mo + "00" + num;
        return testno;
    }
}
