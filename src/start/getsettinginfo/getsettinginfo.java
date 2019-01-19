package start.getsettinginfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class getsettinginfo {                               //获取系统设置
    @RequestMapping(value = "getsettinginfo",method = RequestMethod.GET)
    public @ResponseBody getsettinginforesult getsettinginfo() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getsettinginforesult result = new getsettinginforesult();

        try {
            ps = conn.prepareStatement("SELECT * FROM email");
            rs = ps.executeQuery();
            if(rs.next()){
                result.setDeconame(rs.getString("deconame"));
                result.setEdeconame(rs.getString("edeconame"));
                result.setDelicense(rs.getString("delicense"));
                result.setManulevel(rs.getString("manulevel"));
                result.setTime(rs.getString("time"));
                result.setOrgcode(rs.getString("orgcode"));

            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM supervisionunit");
            rs = ps.executeQuery();
            if(rs.next()){
                result.setName(rs.getString("name"));
                result.setEname(rs.getString("ename"));
                result.setUniformcode(rs.getString("uniformcode"));
                result.setManuno(rs.getString("manuno"));
            }
            rs.close();
            ps.close();


            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
