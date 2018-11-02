package start.matlname;

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
public class matlname {
    @RequestMapping(value = "matlname", method = RequestMethod.GET)
    public @ResponseBody
    matlnameresult matlname() throws ClassNotFoundException, SQLException {          //材料名称
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=conn.prepareStatement("SELECT * FROM unit");
        rs=ps.executeQuery();
        matlnameresult res = new matlnameresult();
        ArrayList<String> data = new ArrayList<String>();
        while(rs.next()){
            data.add(rs.getString("note"));                         //返回材料名称(位于单位名称表的备注中)
            res.setResult("success");                                                       //添加成功
        }
        res.setData(data);                                                                  //返回数据
        return res;
    }
}
