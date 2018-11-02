package start.jdbc;

public class jdbc {
    private String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    private String DBURL = "jdbc:mysql://localhost:3306/eqm?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
    private String DBUSER = "root";
    private String DBPASS = "123456";

    public String getDBDRIVER() {
        return DBDRIVER;
    }

    public String getDBURL() {
        return DBURL;
    }

    public String getDBUSER() {
        return DBUSER;
    }

    public String getDBPASS() {
        return DBPASS;
    }
}
