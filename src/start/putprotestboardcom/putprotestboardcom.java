package start.putprotestboardcom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putprotestboardcom {                               //提交产品试板委托
    @RequestMapping(value = "putprotestboardcom")
    public @ResponseBody putprotestboardcomresult putprotestboardcom(@RequestBody putprotestboardcompost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        putprotestboardcomresult result = new putprotestboardcomresult();

        try {


            ps = conn.prepareStatement("INSERT INTO protestboardcom(prodno,evaluastand,specimenno," +
                    "specimenname,weldingsteelseal,designation,spec," +
                    "groovetype,weldingmethod,weldingposition,heatcondi," +
                    "representno,representpart,drawingnumber,surfacebending," +
                    "backbending,lateralbending,flattening,shocktemperature," +
                    "weldzoneshocknum,thermalimpactzonenum,user,date,prodname) VALUES (?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?," +
                    "?,?,?,?,?)");
            ps.setString(1,pp.getProdno());
            ps.setString(2,pp.getEvaluastand());
            ps.setString(3,pp.getSpecimenno());
            ps.setString(4,pp.getSpecimenname());
            ps.setString(5,pp.getWeldingsteelseal());
            ps.setString(6,pp.getDesignation());
            ps.setString(7,pp.getSpec());
            ps.setString(8,pp.getGroovetype());
            ps.setString(9,pp.getWeldingmethod());
            ps.setString(10,pp.getWeldingposition());
            ps.setString(11,pp.getHeatcondi());
            ps.setString(12,pp.getRepresentno());
            ps.setString(13,pp.getRepresentpart());
            ps.setString(14,pp.getDrawingnumber());
            ps.setString(15,pp.getSurfacebending());
            ps.setString(16,pp.getBackbending());
            ps.setString(17,pp.getLateralbending());
            ps.setString(18,pp.getFlattening());
            ps.setString(19,pp.getShocktemperature());
            ps.setString(20,pp.getWeldzoneshocknum());
            ps.setString(21,pp.getThermalimpactzonenum());
            ps.setString(22,pp.getUser());
            ps.setDate(23,new java.sql.Date(new java.util.Date().getTime()));
            ps.setString(24,pp.getProdname());
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
