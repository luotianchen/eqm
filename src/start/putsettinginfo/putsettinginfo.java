package start.putsettinginfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putsettinginfo {                                           //修改系统设置
    @RequestMapping(value = "putsettinginfo")
    public @ResponseBody putsettinginforesult putsettinginfo(@RequestBody putsettinginfopost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putsettinginforesult result = new putsettinginforesult();

        try {
            ps = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("UPDATE email SET deconame = ?,edeconame = ?," +
                        "delicense = ?,manulevel = ?,time = ?,orgcode = ? WHERE id = 1");
                ps.setString(1,pp.getDeconame());
                ps.setString(2,pp.getEdeconame());
                ps.setString(3,pp.getDelicense());
                ps.setString(4,pp.getManulevel());
                ps.setString(5,pp.getTime());
                ps.setString(6,pp.getOrgcode());
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO email(deconame,edeconame," +
                        "delicense,manulevel,time,orgcode,id) VALUES (?,?,?,?,?,?,1)");
                ps.setString(1,pp.getDeconame());
                ps.setString(2,pp.getEdeconame());
                ps.setString(3,pp.getDelicense());
                ps.setString(4,pp.getManulevel());
                ps.setString(5,pp.getTime());
                ps.setString(6,pp.getOrgcode());
                ps.executeUpdate();
                ps.close();
            }

            ps = conn.prepareStatement("SELECT * FROM supervisionunit WHERE id = 1");
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("UPDATE supervisionunit SET name = ?,ename = ?," +
                        "uniformcode = ?,manuno = ? WHERE id = 1");
                ps.setString(1,pp.getName());
                ps.setString(2,pp.getEname());
                ps.setString(3,pp.getUniformcode());
                ps.setString(4,pp.getManuno());
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO supervisionunit (name,ename," +
                        "uniformcode,manuno,id) VALUES (?,?,?,?,1)");
                ps.setString(1,pp.getName());
                ps.setString(2,pp.getEname());
                ps.setString(3,pp.getUniformcode());
                ps.setString(4,pp.getManuno());
                ps.executeUpdate();
                ps.close();
            }


            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
