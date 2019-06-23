package start.useLicense;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Scanner;


public class EndInt extends JFrame {
    // 连接对象
    private Connection conn;
    // 传递sql语句
    private PreparedStatement ps = null;
    // 结果集
    ResultSet rs=null;
    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcP5cj9ET9z4QwPbFfztYsceifIJhHvRh4mYbQWa3B6zqpcuCz1Vq9OGOWfVnB5uvRqHi6cRf1H5WxeO3X4M/4KbP/3uSJOhnugHVfYKhzZtWg5CUM4yUmEbh2o6gVdgyWYPrbTk4KwsykValyyQdEOp3tWhp324ir6lUCXzTk9wIDAQAB";
    private static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJw/lyP0RP3PhDA9\n" +
            "sV/O1ixx6J8gmEe9GHiZhtBZrcHrOqly4LPVWr04Y5Z9WcHm69GoeLpxF/UflbF4\n" +
            "7dfgz/gps//e5Ik6Ge6AdV9gqHNm1aDkJQzjJSYRuHajqBV2DJZg+ttOTgrCzKRV\n" +
            "qXLJB0Q6ne1aGnfbiKvqVQJfNOT3AgMBAAECgYAVRWr0EBscWpXqY1u0aVoq7s72\n" +
            "at+QVKPyNyRtVOgc0ENKMkN4+ADEZdrY7/O6PA+6mS7mYuMaEdS1NntaulZVghAl\n" +
            "MY5fIWDvOvJ/x5nTR+cglngkodNoFp70sRrv2l0GhQBWrnxXxL+GnvBO+ukqmxuU\n" +
            "8rq30GlX79lcNpfEIQJBAMxWr6fRz0sfLABk9xBq/9RqooX7RiENij9du4vFgHUB\n" +
            "mSbb1uUdZu1sbvbYFdW71VeWP8bZR0Fqy6GYB4s7yA8CQQDDwGIg0dwzRG0c6I+B\n" +
            "X749PrwZKfluXVXd+U8IhCd5TJ0atM3tfka4wqTbx0PzzpMW9YrY/Q6KIU8tcUka\n" +
            "rGyZAkEAnCUh1Xvei+kmFqcQpNse45MY3olE7b0LB6D4z7X8k2zFO4yCilINdSCH\n" +
            "Ktm01b7TKDsIQUuHx9V0BefG2/6ywQJAJ4BmDOtsfSf8wM2pT9krQZ6sRIO04vGQ\n" +
            "x1ds/HH2qUKtnVsJBzjpOpWEwRkH/GYeRo7MMxd13Kf2G1x1/Rz4WQJBALGqd2Tq\n" +
            "wcU+kKazyZPQbX73pxI5C46Drh/1X3sYyygAKi+GZvkFwDL6hOOzO7CTc3yVUnV3\n" +
            "WNlVc1bgCWKnq5A=";


    public void EndInt() {
        this.setTitle("企业质量管理系统激活工具");
        Container cont=this.getContentPane();
        this.setLayout(null);
        JLabel label1 = new JLabel("请输入License：");
        MJTextArea jta=new MJTextArea();
        JLabel route = new JLabel("请选择webapp路径");
        MJTextField jbtroute = new MJTextField();
        JButton jbt=new JButton("请选择路径");
        JButton btn=new JButton("立即激活");

        cont.add(label1);
        cont.add(btn);
        cont.add(jta);
        cont.add(route);
        cont.add(jbt);
        cont.add(jbtroute);

        route.setBounds(30,20,120,40);
        jbtroute.setBounds(150,20,150,40);
        jbt.setBounds(310,20,70,40);
        label1.setBounds(30, 60, 120, 40);
        jta.setBounds(30,100,340,140);
        btn.setBounds(125,260,150,40);

        jta.setLineWrap(true);
        this.setVisible(true);
        this.setBounds(500, 260, 400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(jbtroute.getText().equals("") || jbtroute.getText()==null){
                        JOptionPane.showMessageDialog(null, "路径错误！", "错误",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    byte[] decodedData = RSAUtils.decryptByPublicKey(Base64.getDecoder().decode(jta.getText()), publicKey);
                    String target = new String(decodedData);
                    String username = target.split(";")[0].split("=")[1];
                    int times = 0;
                    String sql = "SELECT * FROM license where lid = \""+username+"\"";
                    conn = jdbcUtil.getConection();
                    ps = conn.prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        times = rs.getInt("times");
                        if((rs.getInt("times")-1)<0){
                            JOptionPane.showMessageDialog(null, "激活码激活次数已达上限！", "失败",JOptionPane.ERROR_MESSAGE);
                            return;
                        }else{
                            times --;
                        }
                        String sql2 = "UPDATE license set dateEnd = \""+rs.getString("dateEnd")+"\" , times = "+(rs.getInt("times")-1)+" WHERE lid = \""+username+"\"";
                        ps = conn.prepareStatement(sql2);
                        ps.execute();
                    }
                    String date = target.split(";")[2].split("=")[1];

                    String licenseStatic = "serial="+username+";timesCount="+times+";timeEnd="+date+";cpu="+GetCPUSerialNumber();

                    byte[] data = licenseStatic.getBytes();
                    byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);//用私钥加密


                    FileWriter writer;
                    try {
                        File fp=new File(jbtroute.getText()+"/eqm.dat");
                        PrintWriter pfp= new PrintWriter(fp);
                        pfp.print(Base64.getEncoder().encodeToString(encodedData));
                        pfp.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "激活成功！License剩余可使用次数："+times, "成功",JOptionPane.INFORMATION_MESSAGE
                    );
                    System.out.println("解密后: \r\n" + target);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "激活码无效，请检查后重试！", "失败",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser jfc=new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY );
                jfc.showDialog(new JLabel(), "选择");
                File file=jfc.getSelectedFile();
                if(file.isDirectory()){
                    jbtroute.setText(file.getAbsolutePath());
                    System.out.println("文件夹:"+file.getAbsolutePath());
                }
            }
        });

    }
    String GetCPUSerialNumber() throws IOException {
        long start = System.currentTimeMillis();
        Process process = Runtime.getRuntime().exec(
                new String[] { "wmic", "cpu", "get", "ProcessorId" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        return serial;
    }

}