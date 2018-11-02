package start.stand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class stand {
    public static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DBURL = "jdbc:mysql://localhost:3306/eqm?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    public static String DBUSER = "root";
    public static String DBPASS = "123456";
    @RequestMapping(value = "stand", method = RequestMethod.GET)
    public @ResponseBody standresult stand() throws ClassNotFoundException, SQLException {          //标准
        Class.forName(DBDRIVER);
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=conn.prepareStatement("SELECT distinct stand FROM stand");
        rs=ps.executeQuery();
        standresult res = new standresult();
        ArrayList<String> data = new ArrayList<String>();
        while(rs.next()){
            data.add(rs.getString("stand"));                         //返回质保书情况
            res.setResult("success");                                                       //添加成功
        }
        res.setData(data);                                                                  //返回数据
        return res;
    }
}
