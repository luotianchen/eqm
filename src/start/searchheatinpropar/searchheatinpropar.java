package start.searchheatinpropar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchheatinpropar {                                               //查询产品参数表所有已审核热处理状态
    @RequestMapping(value = "searchheatinpropar",method = RequestMethod.GET)
    public @ResponseBody searchheatinproparresult searchheatinpropar() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;

        searchheatinproparresult result = new searchheatinproparresult();
        ArrayList<String> as = new ArrayList<String>();


        //try {


            ps = conn.prepareStatement("SELECT * FROM proparlist");
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM promanparlist WHERE proparlist_id_dwgno = ?");
                ps1.setInt(1,rs.getInt("id"));
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    ps2 = conn.prepareStatement("SELECT * FROM protestboardcom WHERE prodno = ?");
                    ps2.setString(1,rs1.getString("prodno"));
                    rs2 = ps2.executeQuery();
                    if(rs2.next()){
                        as.add(rs2.getString("heatcondi"));
                        result.setResult("success");
                    }else {
                        result.setResult("fail");
                        break;
                    }
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
