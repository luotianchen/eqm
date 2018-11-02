package start.millunit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class millunit {
    @RequestMapping(value = "millunit" ,method = RequestMethod.GET)
    public @ResponseBody millunitresult millunit() throws ClassNotFoundException, SQLException {          //生存单位，在单位名称内
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=conn.prepareStatement("SELECT * FROM unit");
        rs=ps.executeQuery();
        millunitresult res = new millunitresult();
        millunitdata data = null;
        ArrayList<millunitdata> adata = new ArrayList<millunitdata>();
        while(rs.next()){
            data = new millunitdata();
            System.out.println(rs.getInt("id"));
            data.setId(rs.getInt("id"));                                          //返回id
            data.setMillunit(rs.getString("millunit"));                         //返回生产单位
            adata.add(data);
            res.setResult("success");                                                       //添加成功
        }
        res.setData(adata);                                                                  //返回数据
        return res;
    }
}
