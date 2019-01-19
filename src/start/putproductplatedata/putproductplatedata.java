package start.putproductplatedata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putproductplatedata {                              //提交产品试板数据录入
    @RequestMapping(value = "putproductplatedata")
    public @ResponseBody putproductplatedataresult putproductplatedata(@RequestBody putproductplatedatapost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null ;
        ResultSet rs = null;

        putproductplatedataresult result = new putproductplatedataresult();

        try {
            ps = conn.prepareStatement("INSERT INTO productplatedata(prodno,testboardstand,matlstand," +
                    "designation,rm,elong,impacttemp," +
                    "impactpa1,impactpa2,impactpa3," +
                    "impactpb1,impactpb2,impactpb3," +
                    "bendpara,ebendpara,benddia,fractposit," +
                    "efractposit,entrustdate,user,date) VALUES (?,?,?," +
                    "?,?,?,?," +
                    "?,?,?," +
                    "?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setString(2,pp.getTestboardstand());
            ps.setString(3,pp.getMatlstand());
            ps.setString(4,pp.getDesignation());
            ps.setString(5,pp.getRm());
            ps.setString(6,pp.getElong());
            ps.setString(7,pp.getImpacttemp());
            ps.setString(8,pp.getImpactpa1());
            ps.setString(9,pp.getImpactpa2());
            ps.setString(10,pp.getImpactpa3());
            ps.setString(11,pp.getImpactpb1());
            ps.setString(12,pp.getImpactpb2());
            ps.setString(13,pp.getImpactpb3());
            ps.setString(14,pp.getBendpara());
            ps.setString(15,pp.getEbendpara());
            ps.setString(16,pp.getBenddia());
            ps.setString(17,pp.getFractposit());
            ps.setString(18,pp.getEfractposit());
            ps.setString(19,pp.getEntrustdate());
            ps.setString(20,pp.getUser());
            ps.setDate(21,new java.sql.Date(new java.util.Date().getTime()));
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
