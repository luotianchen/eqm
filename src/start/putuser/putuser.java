package start.putuser;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putuser {                                                  //新增用户
    @RequestMapping(value = "putuser")
    public @ResponseBody putuserresult putuser(@RequestBody putuserpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        putuserresult result = new putuserresult();

        String sql =null;
        String sql_1 =null;
        String sql_2 =null;
        String sql_3 =null;

        int role_p=0;
        int role2_p=0;
        int role3_p=0;
        int role4_p=0;
        int role5_p=0;
        int num=2;
        int id = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM department WHERE id = 0");
            rs = ps.executeQuery();
            if(!rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO department(id) VALUES (?)");
                ps.setInt(1,0);
                ps.executeUpdate();
                ps.close();

                ps = conn.prepareStatement("SELECT * FROM department ");
                rs = ps.executeQuery();
                while (rs.next()){
                    id = rs.getInt("id");
                }
                rs.close();
                ps.close();

                ps = conn.prepareStatement("UPDATE department SET id = 0 WHERE id = ?");
                ps.setInt(1,id);
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
            }

            ps = conn.prepareStatement("SELECT * FROM role WHERE id = 0");
            rs = ps.executeQuery();
            if(!rs.next()){
                rs.close();
                ps.close();
                ps = conn.prepareStatement("INSERT INTO role(id,department_id) VALUES (0,0)");
                ps.executeUpdate();
                ps.close();

                ps = conn.prepareStatement("SELECT * FROM role ");
                rs = ps.executeQuery();
                while (rs.next()){
                    id = rs.getInt("id");
                }
                rs.close();
                ps.close();

                ps = conn.prepareStatement("UPDATE role SET id = 0 WHERE id = ?");
                ps.setInt(1,id);
                ps.executeUpdate();
                ps.close();
            }else {
                rs.close();
                ps.close();
            }

            sql_1 = "INSERT INTO userform(username";
            sql_2 = ",name) values (?";
            sql_3 = ",?)";
            if(pp.getRole()!=0){
                sql_1 = sql_1 + " ,role_id ";
                sql_2 = sql_2 + ",?";
                role_p=1;
            }
            if(pp.getRole2()!=0){
                sql_1 = sql_1 + " ,role2_id ";
                sql_2 = sql_2 + ",?";
                role2_p=1;
            }
            if(pp.getRole3()!=0){
                sql_1 = sql_1 + " ,role3_id ";
                sql_2 = sql_2 + ",?";
                role3_p=1;
            }
            if(pp.getRole4()!=0){
                sql_1 = sql_1 + " ,role4_id ";
                sql_2 = sql_2 + ",?";
                role4_p=1;
            }
            if(pp.getRole5()!=0){
                sql_1 = sql_1 + " ,role5_id ";
                sql_2 = sql_2 + ",?";
                role5_p=1;
            }
            sql = sql_1+sql_2+sql_3;
            ps = conn.prepareStatement(sql);
            ps.setString(1,pp.getUsername());
            if(role_p==1){
                ps.setInt(num,pp.getRole());
                num++;
            }
            if(role2_p==1){
                ps.setInt(num,pp.getRole2());
                num++;
            }
            if(role3_p==1){
                ps.setInt(num,pp.getRole3());
                num++;
            }
            if(role4_p==1){
                ps.setInt(num,pp.getRole4());
                num++;
            }
            if(role5_p==1){
                ps.setInt(num,pp.getRole5());
                num++;
            }
            ps.setString(num,pp.getName());
            ps.executeUpdate();
            ps.close();
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
