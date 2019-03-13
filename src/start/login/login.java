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

        int role_id=0;
        int role2_id=0;
        int role3_id=0;
        int role4_id=0;
        int role5_id=0;



        String pwd = null;                                                    //存储密码
        int a=0;                                                         //判断
        loginresult result = new loginresult();                                      //返回结果
        while (rs.next()) {											//循环找出账号
            if(rs.getString("username").equals(lp.getUsername())){
                a=1;												//存在账号输出a=1
                result.setName(rs.getString("name"));               //存入与该用户对应的真实姓名
                result.setEmail(rs.getString("email"));             //取出角色email
                role_id=rs.getInt("role_id");                       //取出角色id
                role2_id=rs.getInt("role2_id");                       //取出角色id
                role3_id=rs.getInt("role3_id");                       //取出角色id
                role4_id=rs.getInt("role4_id");                       //取出角色id
                role5_id=rs.getInt("role5_id");                       //取出角色id
                result.setRoles(role_id + ";" + role2_id + ";" +role3_id + ";" +role4_id + ";" +role5_id );
                pwd=rs.getString("password");						//查找与该用户对应的密码
                break;												//跳出循环
            }else{
                a=0;												//不存在账号输出a=0
            }
        }

        rs.close();
        ps.close();
        ps=conn.prepareStatement("SELECT * FROM role WHERE id=?");
        ps.setInt(1,role_id);
        rs=ps.executeQuery();
        while (rs.next()){
            result.setRole(rs.getString("rolename"));
        }
        rs.close();
        ps.close();

        ps=conn.prepareStatement("SELECT * FROM role WHERE id=?");
        ps.setInt(1,role2_id);
        rs=ps.executeQuery();
        while (rs.next()){
            result.setRole2(rs.getString("rolename"));
        }
        rs.close();
        ps.close();

        ps=conn.prepareStatement("SELECT * FROM role WHERE id=?");
        ps.setInt(1,role3_id);
        rs=ps.executeQuery();
        while (rs.next()){
            result.setRole3(rs.getString("rolename"));
        }
        rs.close();
        ps.close();

        ps=conn.prepareStatement("SELECT * FROM role WHERE id=?");
        ps.setInt(1,role4_id);
        rs=ps.executeQuery();
        while (rs.next()){
            result.setRole4(rs.getString("rolename"));
        }
        rs.close();
        ps.close();

        ps=conn.prepareStatement("SELECT * FROM role WHERE id=?");
        ps.setInt(1,role5_id);
        rs=ps.executeQuery();
        while (rs.next()){
            result.setRole5(rs.getString("rolename"));
        }
        rs.close();
        ps.close();

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
        conn.close();
        return result;
    }
}
