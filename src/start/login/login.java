package start.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import rsa.Base64Utils;
import rsa.RSAUtils;
import start.getreport.QRCodeUtil;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@CrossOrigin
@Controller
public class login {                                                                        //登录

    // 连接对象
    private Connection conn;
    // 传递sql语句
    private PreparedStatement ps = null;
    // 结果集
    ResultSet rs=null;
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcP5cj9ET9z4QwPbFfztYsceifIJhHvRh4mYbQWa3B6zqpcuCz1Vq9OGOWfVnB5uvRqHi6cRf1H5WxeO3X4M/4KbP/3uSJOhnugHVfYKhzZtWg5CUM4yUmEbh2o6gVdgyWYPrbTk4KwsykValyyQdEOp3tWhp324ir6lUCXzTk9wIDAQAB";

    @RequestMapping(value = "login")
    public @ResponseBody loginresult login(@RequestBody loginpost lp, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;



        Calendar cal1 =new GregorianCalendar();                                                     //日期操作方法
        Calendar cal2 =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cal1.setTime(new Date());


        int role_id=0;
        int role2_id=0;
        int role3_id=0;
        int role4_id=0;
        int role5_id=0;



        String pwd = null;                                                    //存储密码
        int a=0;                                                         //判断
        loginresult result = new loginresult();                                      //返回结果

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        System.out.println(path);
        String newpath = null;

        System.out.println(File.separator);

        String[] sourceStrArray = null;

        if(File.separator.equals("\\")){
            sourceStrArray = path.split(File.separator+File.separator);
        }else {
            sourceStrArray = path.split(File.separator);
        }

        for (int i = 0; i < sourceStrArray.length-1; i++) {
            System.out.println(sourceStrArray[i]);
            if(i==0){
                newpath = sourceStrArray[i];
            }else {
                newpath = newpath + File.separator + sourceStrArray[i];
            }

        }

        String uploadPath = newpath;                                                //获取文件名称

        System.out.println(uploadPath);

        File file = new File(uploadPath+"/eqm.dat");
        if(file.exists()){
            FileInputStream is = new FileInputStream(uploadPath+"/eqm.dat");
            //2、开始读取信息
            //先定义一个字节数组存放数据
            byte[] b = new byte[172];//把所有的数据读取到这个字节当中
            //完整的读取一个文件
            is.read(b);
            //read:返回的是读取的文件大小
            //最大不超过b.length，返回实际读取的字节个数
//          System.out.println(Arrays.toString(b));//读取的是字节数组
//          //把字节数组转成字符串
//          System.out.println(new String(b));
//          //关闭流

            System.out.println(new String(b));
            byte[] decodedData = RSAUtils.decryptByPublicKey(Base64Utils.decode(new String(b)), publicKey);
            String target = new String(decodedData);
            String timesCount = target.split(";")[1].split("=")[1];
            String timeEnd = target.split(";")[2].split("=")[1];

//            String cpu = target.split(";")[3].split("=")[1];
//
//            long start = System.currentTimeMillis();
//            Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
//            process.getOutputStream().close();
//            Scanner sc = new Scanner(process.getInputStream());
//            String property = sc.next();
//            String serial = sc.next();



            is.close();

            cal2.setTime(format.parse(timeEnd));


//            if(cpu.equals(serial)){
                if(Integer.parseInt(timesCount)>=0 && cal1.before(cal2)){
                    System.out.println(1);
                    ps=conn.prepareStatement("SELECT * FROM userform");
                    rs=ps.executeQuery();
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
                }else {
                    result.setResult("-2");
                }
//            }else {
//                result.setResult("-3");
//            }
        }else {
            result.setResult("-2");
        }


        return result;
    }
}
