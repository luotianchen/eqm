package start.getdwgnobynoaudit;

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
public class getdwgnobynoaudit {                                    //获取所有未审核图号
    @RequestMapping(value = "getdwgnobynoaudit",method = RequestMethod.GET)
    public @ResponseBody getdwgnobynoauditresult getdwgnobynoaudit() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        getdwgnobynoauditresult result = new getdwgnobynoauditresult();
        getdwgnobynoauditdata data = null;
        ArrayList<getdwgnobynoauditdata> ag = new ArrayList<getdwgnobynoauditdata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE audit = 0");
            rs = ps.executeQuery();
            while (rs.next()){
                data = new getdwgnobynoauditdata();
                data.setDate(rs.getString("date"));
                data.setDwgno(rs.getString("dwgno"));
                data.setUser(rs.getString("user"));
                ag.add(data);
            }
            rs.close();
            ps.close();
            result.setData(ag);
            result.setResult("success");
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}