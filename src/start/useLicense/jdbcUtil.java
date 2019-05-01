package start.useLicense;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcUtil {
    public static Connection getConection(){
        try {

            String DBDRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(DBDRIVER); // 加载驱动

            System.out.println("加载驱动成功!!!");
        } catch (ClassNotFoundException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        Connection conn = null;
        try {
            String DBURL = "jdbc:mysql://47.99.210.247:3306/eqm?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
            String DBUSER = "root";
            String DBPASS = "123456";
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
