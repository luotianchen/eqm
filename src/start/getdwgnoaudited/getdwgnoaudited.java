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
        ArrayList<getdwgnoauditeddata> ag = new ArrayList<getdwgnoauditeddata>();
        getdwgnoauditeddata data = null;
        ArrayList<Integer> sc = new ArrayList<Integer>();

        try {
            ps = conn.prepareStatement("SELECT id,dwgno, (select prodname from productname where id = productname_id_prodname) as prodname, type, mainstand, designdate, deconame FROM proparlist WHERE audit = 1 ORDER BY date DESC");
            rs = ps.executeQuery();
            while (rs.next()){
                for (int i = 0;i<ag.size();i++){
                    if(ag.get(i).getDwgno().equals(rs.getString("dwgno"))){
                        ps1 = conn.prepareStatement("DELETE FROM proparlist WHERE id = ?");
                        ps1.setInt(1,ag.get(i).getSc());
                        ps1.executeUpdate();
                        ps1.close();
                        ag.remove(i);
                        break;
                    }
                }


                data = new getdwgnoauditeddata();
                data.setDwgno(rs.getString("dwgno"));
                data.setProdname(rs.getString("prodname"));
                data.setType(rs.getString("type"));
                data.setMainstand(rs.getString("mainstand"));
                data.setDesigndate(rs.getString("designdate"));
                data.setDeconame(rs.getString("deconame"));
                data.setSc(rs.getInt("id"));
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
