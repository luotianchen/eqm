package start.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;
import start.user.user;

import java.sql.*;

@CrossOrigin
@Controller
public class login {                                                                        //登录
    @RequestMapping(value = "login")
    public @ResponseBody loginresult login(@RequestBody user ur) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=conn.prepareStatement("SELECT * FROM user");
        rs=ps.executeQuery();
        String pwd = null;                                                    //存储密码
        int a=0;                                                         //判断
        loginresult res = new loginresult();                                      //返回结果
        while (rs.next()) {											//循环找出账号
            if(rs.getString("username").equals(ur.getUsername())){
                a=1;												//存在账号输出a=1
                ur.setRole(rs.getString("role"));
                pwd=rs.getString("password");						//查找与该用户对应的密码
                break;												//跳出循环
            }else{
                a=0;												//不存在账号输出a=0
            }
        }
        if(a==1){
            if(pwd.equals(ur.getPassword())){
                res.setResult("success");
                res.setRole(ur.getRole());
            }else{
                res.setResult("fail");
            }
        }
        if(a==0){
            res.setResult("fail");
        }
        return res;
    }
}
