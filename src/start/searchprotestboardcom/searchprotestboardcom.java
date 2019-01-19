package start.searchprotestboardcom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchprotestboardcom {                                    //产品试板委托查询
    @RequestMapping(value = "searchprotestboardcom")
    public @ResponseBody searchprotestboardcomresult searchprotestboardcom(@RequestBody searchprotestboardcompost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        searchprotestboardcomresult result = new searchprotestboardcomresult();
        ArrayList<searchprotestboardcomdata> as = new ArrayList<searchprotestboardcomdata>();
        searchprotestboardcomdata data = null;

        String sql = "SELECT * FROM protestboardcom WHERE 1=1 ";
        int num = 0;

        try {
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                sql = sql + "AND prodno = ? ";
            }
            if(sp.getStatus() != -1){
                sql = sql + "AND status = ?";
            }
            ps = conn.prepareStatement(sql);
            if(!(sp.getProdno() == null || sp.getProdno().equals(""))){
                num = num + 1;
                ps.setString(num,sp.getProdno());
            }
            if(sp.getStatus() != -1){
                num = num + 1;
                ps.setInt(num,sp.getStatus());
            }
            rs = ps.executeQuery();
            while (rs.next()){
                data = new searchprotestboardcomdata();
                data.setProdno(rs.getString("prodno"));
                data.setEvaluastand(rs.getString("evaluastand"));
                data.setSpecimenno(rs.getString("specimenno"));
                data.setSpecimenname(rs.getString("specimenname"));
                data.setWeldingsteelseal(rs.getString("weldingsteelseal"));
                data.setDesignation(rs.getString("designation"));
                data.setSpec(rs.getString("spec"));
                data.setGroovetype(rs.getString("groovetype"));
                data.setWeldingmethod(rs.getString("weldingmethod"));
                data.setWeldingposition(rs.getString("weldingposition"));
                data.setHeatcondi(rs.getString("heatcondi"));
                data.setRepresentno(rs.getString("representno"));
                data.setRepresentpart(rs.getString("representpart"));
                data.setDrawingnumber(rs.getString("drawingnumber"));
                data.setSurfacebending(rs.getString("surfacebending"));
                data.setBackbending(rs.getString("backbending"));
                data.setLateralbending(rs.getString("lateralbending"));
                data.setFlattening(rs.getString("flattening"));
                data.setShocktemperature(rs.getString("shocktemperature"));
                data.setWeldzoneshocknum(rs.getString("weldzoneshocknum"));
                data.setThermalimpactzonenum(rs.getString("thermalimpactzonenum"));
                data.setUser(rs.getString("user"));
                data.setDate(rs.getString("date"));
                data.setAudit_user(rs.getString("audit_user"));
                as.add(data);
            }
            rs.close();
            ps.close();
            Collections.reverse(as);                                          //将list倒序
            result.setData(as);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
