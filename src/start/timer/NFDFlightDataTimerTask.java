package start.timer;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import start.jdbc.jdbc;
import start.liststring.liststring;
import start.searchpregaubystatus.searchpregaubystatusdata;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import static start.excel.excel.putsheet;

/**
 * 在 TimerManager 这个类里面，大家一定要注意 时间点的问题。如果你设定在凌晨2点执行任务。但你是在2点以后
 *发布的程序或是重启过服务，那这样的情况下，任务会立即执行，而不是等到第二天的凌晨2点执行。为了，避免这种情况
 *发生，只能判断一下，如果发布或重启服务的时间晚于定时执行任务的时间，就在此基础上加一天。
 * @author wls
 *
 */
public class NFDFlightDataTimerTask extends TimerTask {
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void run() {
        try {
            Calendar calendar1 = Calendar.getInstance();
            //在这里写你要执行的内容
            System.out.println("执行当前时间"+formatter.format(calendar1.getTime()));

            String data[] = formatter1.format(calendar1.getTime()).split("-");
            System.out.println(data[2]);
            if(data[2].equals("15")){
                jdbc j = new jdbc();
                Class.forName(j.getDBDRIVER());
                Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
                PreparedStatement ps = null;
                ResultSet rs=null;
                PreparedStatement ps1 = null;
                ResultSet rs1=null;


                liststring ls = new liststring();
                ArrayList<String> as = null;
                int i = 1;
                String name_user = null;


                //创建excel
                Workbook wb = new HSSFWorkbook();
                Sheet sh=wb.createSheet();
                Row row0 = sh.createRow(0);

                Cell cell00 =row0.createCell(0);
                CellReference cr00 = new CellReference(cell00);
                cell00.setCellValue("名称");

                Cell cell01 =row0.createCell(1);
                CellReference cr01 = new CellReference(cell01);
                cell01.setCellValue("厂内编号");

                Cell cell02 =row0.createCell(2);
                CellReference cr02 = new CellReference(cell02);
                cell02.setCellValue("出厂编号");

                Cell cell03 =row0.createCell(3);
                CellReference cr03 = new CellReference(cell03);
                cell03.setCellValue("型号");

                Cell cell04 =row0.createCell(4);
                CellReference cr04 = new CellReference(cell04);
                cell04.setCellValue("测量范围");

                Cell cell05 =row0.createCell(5);
                CellReference cr05 = new CellReference(cell05);
                cell05.setCellValue("精度等级");

                Cell cell06 =row0.createCell(6);
                CellReference cr06 = new CellReference(cell06);
                cell06.setCellValue("生产单位");

                Cell cell07 =row0.createCell(7);
                CellReference cr07 = new CellReference(cell07);
                cell07.setCellValue("出厂日期");

                Cell cell08 =row0.createCell(8);
                CellReference cr08 = new CellReference(cell08);
                cell08.setCellValue("管理类别");

                Cell cell09 =row0.createCell(9);
                CellReference cr09 = new CellReference(cell09);
                cell09.setCellValue("检定日期");

                Cell cell010 =row0.createCell(10);
                CellReference cr010 = new CellReference(cell010);
                cell010.setCellValue("下次检定日期");

                Cell cell011 =row0.createCell(11);
                CellReference cr011 = new CellReference(cell011);
                cell011.setCellValue("专管人");



                ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE status = ? AND recalibdate BETWEEN ? AND ?");
                ps.setInt(1,1);
                calendar1.add(calendar1.DATE, 1);
                ps.setString(2,formatter1.format(calendar1.getTime()));
                calendar1.add(Calendar.MONTH, 1);
                calendar1.set(Calendar.DAY_OF_MONTH, calendar1.getActualMaximum(Calendar.DAY_OF_MONTH));
                ps.setString(3,formatter1.format(calendar1.getTime()));
                rs = ps.executeQuery();
                while (rs.next()){
                    Row row = sh.createRow(i);

                    Cell cell0 =row.createCell(0);
                    CellReference cr0 = new CellReference(cell0);
                    cell0.setCellValue(rs.getString("gaugename"));

                    Cell cell1 =row.createCell(1);
                    CellReference cr1 = new CellReference(cell1);
                    cell1.setCellValue(rs.getString("gaugeno"));

                    Cell cell2 =row.createCell(2);
                    CellReference cr2 = new CellReference(cell2);
                    cell2.setCellValue(rs.getString("exitno"));

                    Cell cell3 =row.createCell(3);
                    CellReference cr3 = new CellReference(cell3);
                    cell3.setCellValue(rs.getString("type"));

                    Cell cell4 =row.createCell(4);
                    CellReference cr4 = new CellReference(cell4);
                    cell4.setCellValue(rs.getString("measrangemin") + "~" +rs.getDouble("measrangemax"));

                    Cell cell5 =row.createCell(5);
                    CellReference cr5 = new CellReference(cell5);
                    cell5.setCellValue(rs.getString("accuclass"));

                    Cell cell6 =row.createCell(6);
                    CellReference cr6 = new CellReference(cell6);
                    cell6.setCellValue(rs.getString("millunit"));

                    Cell cell7 =row.createCell(7);
                    CellReference cr7 = new CellReference(cell7);
                    cell7.setCellValue(rs.getString("exitdate"));

                    Cell cell8 =row.createCell(8);
                    CellReference cr8 = new CellReference(cell8);
                    cell8.setCellValue(rs.getString("managlevel"));

                    Cell cell9 =row.createCell(9);
                    CellReference cr9 = new CellReference(cell9);
                    cell9.setCellValue(rs.getString("calibdate"));

                    Cell cell10 =row.createCell(10);
                    CellReference cr10 = new CellReference(cell10);
                    cell10.setCellValue(rs.getString("recalibdate"));

                    as = ls.stringtolist(rs.getString("specialist"),"#");
                    for (int z = 0;z<as.size();z++){
                        ps1 = conn.prepareStatement("SELECT * FROM userform WHERE username = ?");
                        ps1.setString(1,as.get(z));
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            if(name_user == null || name_user.equals("")){
                                name_user = rs1.getString("name");
                            }else {

                                name_user = name_user + "、" +rs1.getString("name");
                            }
                        }
                        rs1.close();
                        ps1.close();
                    }

                    Cell cell11 =row.createCell(11);
                    CellReference cr11 = new CellReference(cell11);
                    cell11.setCellValue(name_user);
                    name_user = null;

                    i++;
                }
                rs.close();
                ps.close();







                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    wb.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }










                Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
                calendar.setTime(new java.sql.Date(new java.util.Date().getTime()));
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmmss");


                //标题名字
                String name = "计量台账提醒"+simpleDateFormat1.format(calendar.getTime());
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
                        message.setSubject(name);

                        // 设置消息体
                        message.setText("testemail");

                        // 添加附件
                        Multipart mm = new MimeMultipart();
                        BodyPart mdp = new MimeBodyPart();
                        ///E:/JavaWorkspace/rs/WebRoot/WEB-INF/classes/xlsresult/ygxc.xls
                        //File file = new File(this.getClass().getResource("/").getPath()+"xlsresult/ygxc.xls");
                        DataSource fds = new ByteArrayDataSource(out.toByteArray(), "application/vnd.ms-excel");
                        DataHandler dh = new DataHandler(fds);
                        //mdp.setFileName("salary.xls");//设置后缀为xls的名字后就可以发送excel附件了
                        //mdp.setFileName(attachname);//设置后缀为xls的名字后就可以发送excel附件了
                        mdp.setFileName(MimeUtility.encodeWord(name+".xls"));//设置后缀为xls的名字后就可以发送excel附件了

                        //mdp.setFileName(URLEncoder.encode(attachname, "UTF-8"));

                        mdp.setDataHandler(dh);
                        mm.addBodyPart(mdp);
                        message.setContent(mm);

                        // 发送消息
                        Transport.send(message);
                        System.out.println("Sent message successfully....from runoob.com");
                    }catch (MessagingException mex) {
                        mex.printStackTrace();
                    }

                }
                rs.close();
                ps.close();
                conn.close();
            }

        } catch (Exception e) {
            System.out.println("-------------解析信息发生异常--------------");
        }
        System.gc();
    }

}