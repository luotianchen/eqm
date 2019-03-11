package start.searchheatinpropar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchheatinpropar {                                               //查询产品参数表所有已审核热处理状态
    @RequestMapping(value = "searchheatinpropar")
    public @ResponseBody searchheatinproparresult searchheatinpropar(@RequestBody searchheatinproparpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;
        PreparedStatement ps3 = null;
        ResultSet rs3=null;

        searchheatinproparresult result = new searchheatinproparresult();
        ArrayList<String> as = new ArrayList<String>();
        String matlstand = null;
        String designation = null;


        //try {


            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno=? AND status=1");
            ps.setString(1,sp.getProdno());
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ?");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    ps2 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                    ps2.setInt(1,rs1.getInt("contraststand_id_matlstand"));
                    rs2 = ps2.executeQuery();
                    if(rs.next()){
                        ;as.add(rs2.getString("heatcondi"));
                    }
                    rs2.close();
                    ps2.close();
                }
                rs1.close();
                ps1.close();
            }
            rs.close();
            ps.close();
            result.setData(as);

//        }catch (Exception e){
//            result.setResult("fail");
//        }
        conn.close();
        return result;
    }

}
