package start.getprodno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class getprodno {                                                            //获取所有产品编号
    @RequestMapping(value = "getprodno" ,method = RequestMethod.GET)
    public @ResponseBody getprodnoresult getprodno() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        ArrayList<String> data =new ArrayList<String>();
        getprodnoresult result = new getprodnoresult();

        try {
            ps=conn.prepareStatement("SELECT DISTINCT prodno FROM prenotiform");
            rs=ps.executeQuery();
            while (rs.next()){
                data.add(rs.getString("prodno"));
            }
            rs.close();
            ps.close();
            for (int i = 0; i < data.size() - 1; i++) {
                for (int q = data.size() - 1; q > i; q--) {
                    if (data.get(q).equals(data.get(i))) {
                        data.remove(q);
                    }
                }
            }
            Collections.reverse(data);
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
