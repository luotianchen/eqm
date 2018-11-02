package start.warrantystatus;

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
public class warrantystatus {
    @RequestMapping(value = "warrantystatus" , method = RequestMethod.GET)
    public @ResponseBody
    warrantystatusresult warrantystatus() throws ClassNotFoundException, SQLException {            //质保书情况
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
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
