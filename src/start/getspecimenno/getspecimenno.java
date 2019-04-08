package start.getspecimenno;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@Controller
@CrossOrigin
public class getspecimenno {                                        //获取试板委托通过但登记未审核通过的试样编号（试件编号）（specimenno）
    @RequestMapping(value = "getspecimenno",method = RequestMethod.GET)
    public @ResponseBody getspecimennoresult getspecimenno() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;

        getspecimennoresult result = new getspecimennoresult();
        getspecimennodata data = null;
        ArrayList<getspecimennodata> as = new ArrayList<getspecimennodata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE status = 1");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getspecimennodata();
                ps1 = conn.prepareStatement("SELECT * FROM productplate WHERE prodno = ? AND specimenno = ?");
                ps1.setString(1,rs.getString("prodno"));
                ps1.setString(2,rs.getString("specimenno"));
                rs1 = ps1.executeQuery();
                if(rs1.next()){
                    rs1.close();
                    ps1.close();
                    ps1 = conn.prepareStatement("SELECT * FROM productplate WHERE status !=1");
                    rs1 = ps1.executeQuery();
                    while (rs1.next()){
                        if(rs.getString("prodno").equals(rs1.getString("prodno")) && rs.getString("specimenno").equals(rs1.getString("specimenno"))){
                            data.setProdno(rs.getString("prodno"));
                            data.setSpecimennno(rs.getString("specimenno"));
                            as.add(data);
                            break;
                        }
                    }
                }else {
                    rs1.close();
                    ps1.close();
                    data.setProdno(rs.getString("prodno"));
                    data.setSpecimennno(rs.getString("specimenno"));
                    as.add(data);
                }

            }
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
