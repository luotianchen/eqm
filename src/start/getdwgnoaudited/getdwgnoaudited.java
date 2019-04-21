package start.getdwgnoaudited;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class getdwgnoaudited {                                      //获取所有已审核图号
    @RequestMapping(value = "getdwgnoaudited")
    public @ResponseBody getdwgnoauditedresult getdwgnoaudited() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        getdwgnoauditedresult result  = new getdwgnoauditedresult();
        ArrayList<String> as = new ArrayList<String>();
        ArrayList<Integer> sc = new ArrayList<Integer>();

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE audit = 1");
            rs = ps.executeQuery();
            while (rs.next()){
                for (int i = 0;i<as.size();i++){
                    if(as.get(i).equals(rs.getString("dwgno"))){
                        ps1 = conn.prepareStatement("DELETE FROM proparlist WHERE id = ?");
                        ps1.setInt(1,sc.get(i));
                        ps1.executeUpdate();
                        ps1.close();
                        as.remove(i);
                        break;
                    }
                }

                as.add(rs.getString("dwgno"));
                sc.add(rs.getInt("id"));
            }
            rs.close();
            ps.close();
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
