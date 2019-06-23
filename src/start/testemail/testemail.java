package start.testemail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


@Controller
@CrossOrigin
public class testemail {                                            //发送测试邮件
    @RequestMapping(value = "testemail")
    public @ResponseBody testemailresult testemail(@RequestBody testemailpost tp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        testemailresult result = new testemailresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM userform WHERE username = ? AND password = ?");
            ps.setString(1,tp.getUsername());
            ps.setString(2,tp.getPassword());
            rs = ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps.close();


                // 收件人电子邮箱
                //ArrayList<String> to = new ArrayList<String>();
                String to = null;

                // 发件人电子邮箱
                String from = null;

                //授权码
                String authorization_code = null;

                String hostx[] = null;

                // 指定发送邮件的主机为 smtp.**.com
                String host = null;

                ps = conn.prepareStatement("SELECT * FROM email WHERE id != 1 ");
                rs = ps.executeQuery();
                while (rs.next()){


                    to = rs.getString("tosend_email");

                    // 获取系统属性
                    Properties properties = System.getProperties();

                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.port", 465);
                    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    // 获取默认session对象
                    Session session = null;

                    ps1 = conn.prepareStatement("SELECT * FROM email WHERE id = 1");
                    rs1 = ps1.executeQuery();
                    if(rs1.next()){
                        from = rs1.getString("system_email");
                        authorization_code = rs1.getString("authorization_code");
                        hostx = from.split("@");
                        host = "smtp."+hostx[1];

                        // 设置邮件服务器
                        properties.setProperty("mail.smtp.host", host);


                        String finalFrom = from;
                        String finalAuthorization_code = authorization_code;
                        session = Session.getDefaultInstance(properties,new Authenticator(){
                            public PasswordAuthentication getPasswordAuthentication()
                            {
                                return new PasswordAuthentication(finalFrom, finalAuthorization_code); //发件人邮件用户名、授权码
                            }
                        });
                    }
                    rs1.close();
                    ps1.close();

                    try{
                        // 创建默认的 MimeMessage 对象
                        MimeMessage message = new MimeMessage(session);

                        // Set From: 头部头字段
                        message.setFrom(new InternetAddress(from));

                        // Set To: 头部头字段
                        message.addRecipient(Message.RecipientType.TO,
                                new InternetAddress(to));

                        // Set Subject: 头部头字段
                        message.setSubject("测试邮件");

                        // 设置消息体
                        message.setText("testemail");

                        // 发送消息
                        Transport.send(message);
                        System.out.println("Sent message successfully....from runoob.com");
                    }catch (MessagingException mex) {
                        mex.printStackTrace();
                    }

                }
                rs.close();
                ps.close();

                result.setResult("success");








            }else {
                rs.close();
                ps.close();
                result.setResult("fail");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
