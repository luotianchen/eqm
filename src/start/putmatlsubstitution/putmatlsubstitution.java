package start.putmatlsubstitution;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.UUID;

@CrossOrigin
@Controller
public class putmatlsubstitution {                                      //材料代用申请提交
    @RequestMapping(value = "putmatlsubstitution")
    public @ResponseBody putmatlsubstitutionresult putmatlsubstitution(@RequestBody putmatlsubstitutionpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;

        putmatlsubstitutionresult result = new putmatlsubstitutionresult();

        String uid = UUID.randomUUID().toString();                                  //生成随机字符串
        java.util.Date date=new java.util.Date();

        try {
            for (int i = 0;i<pp.getData().size();i++){
                ps = conn.prepareStatement("INSERT INTO matlsubstitution (prodno," +
                        "why,user,name,designmatl,designspec,substitutematl," +
                        "substitutespec,type,audit,date) value (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,pp.getProdno());
                ps.setString(2,pp.getWhy());
                ps.setString(3,pp.getUser());
                ps.setString(4,pp.getData().get(i).getName());
                ps.setString(5,pp.getData().get(i).getDesignmatl());
                ps.setString(6,pp.getData().get(i).getDesignspec());
                ps.setString(7,pp.getData().get(i).getSubstitutematl());
                ps.setString(8,pp.getData().get(i).getSubstitutespec());
                ps.setString(9,pp.getData().get(i).getType());
                ps.setString(10,uid);
                ps.setDate(11,new java.sql.Date(date.getTime()));
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
