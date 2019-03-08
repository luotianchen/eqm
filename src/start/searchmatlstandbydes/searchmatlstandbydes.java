package start.searchmatlstandbydes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class searchmatlstandbydes {                                             //根据牌号查询材料标准（通过标准表查）
    @RequestMapping(value = "searchmatlstandbydes")
    public @ResponseBody searchmatlstandbydesresult searchmatlstandbydes(@RequestBody searchmatlstandbydespost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchmatlstandbydesresult result = new searchmatlstandbydesresult();
        ArrayList<String> as = new ArrayList<String>();

        try {
            ps = conn.prepareStatement("SELECT * FROM contraststand WHERE designation = ?");
            ps.setString(1,sp.getDesignation());
            rs = ps.executeQuery();
            while (rs.next()){
                as.add(rs.getString("matlstand"));
            }
            rs.close();
            ps.close();

            for (int i = 0; i < as.size() - 1; i++) {
                for (int q = as.size() - 1; q > i; q--) {
                    if (as.get(q).equals(as.get(i))) {
                        as.remove(q);
                    }
                }
            }

            result.setResult("success");
            result.setMatlstand(as);
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
