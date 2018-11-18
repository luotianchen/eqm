package start.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class login {                                                                        //登录
    @RequestMapping(value = "login")
    public @ResponseBody loginresult login(@RequestBody loginpost lp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        ps=conn.prepareStatement("SELECT * FROM userform");
        rs=ps.executeQuery();
        String pwd = null;                                                    //存储密码
        String role=null;                                                      //存储角色(权限)
        String name=null;                                                       //存储用户真实名字
        int a=0;                                                         //判断
        loginresult result = new loginresult();                                      //返回结果
        while (rs.next()) {											//循环找出账号
            if(rs.getString("username").equals(lp.getUsername())){
                a=1;												//存在账号输出a=1
                result.setRole(rs.getString("role"));               //存入与该用户对应的角色(权限)
                result.setName(rs.getString("name"));               //存入与该用户对应的真实姓名
                pwd=rs.getString("password");						//查找与该用户对应的密码
                break;												//跳出循环
            }else{
                a=0;												//不存在账号输出a=0
            }
        }
        if(a==1){
            if(pwd.equals(lp.getPassword())){
                result.setResult("success");
            }else{
                result.setResult("fail");
            }
        }
        if(a==0){
            result.setResult("fail");
        }
        return result;
    }
}
