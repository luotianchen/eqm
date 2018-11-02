package start.warrantystatus;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class warrantystatus {
    public static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DBURL = "jdbc:mysql://localhost:3306/eqm?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    public static String DBUSER = "root";
    public static String DBPASS = "123456";
    @RequestMapping(value = "warrantystatus" , method = RequestMethod.GET)
    public @ResponseBody warrantystatusresult warrantystatus() throws ClassNotFoundException, SQLException {            //质保书情况
        Class.forName(DBDRIVER);
        Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=conn.prepareStatement("SELECT * FROM warrantystatus");
        rs=ps.executeQuery();
        warrantystatusresult res = new warrantystatusresult();
        warrantystatusdata data = null;
        ArrayList<warrantystatusdata> adata = new ArrayList<warrantystatusdata>();
        while(rs.next()){
            data = new warrantystatusdata();
            System.out.println(rs.getInt("id"));
            data.setId(rs.getInt("id"));                                          //返回id
            data.setCertsitu(rs.getString("certsitu"));                         //返回质保书情况
            adata.add(data);
            res.setResult("success");                                                       //添加成功
        }
        res.setData(adata);                                                                  //返回数据
        return res;
    }
}
