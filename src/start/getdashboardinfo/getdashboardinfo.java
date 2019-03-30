package start.getdashboardinfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@Controller
@CrossOrigin
public class getdashboardinfo {                             //首页未审核信息展示
    @RequestMapping(value = "getdashboardinfo",method = RequestMethod.GET)
    public @ResponseBody getdashboardinforesult getdashboardinfo() throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        int type_B = 0;
        int type_C = 0;

        getdashboardinforesult result = new getdashboardinforesult();

        int num=0;

        try {
            ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setMatlin(num);
            num=0;
            rs.close();
            ps.close();


            ps = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE design_status=0 OR matl_status=0 OR welding_status=0 OR process_status=0 OR inspection_status=0 ");
            rs = ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM matlsubstitution WHERE audit = ?");
                ps1.setString(1,rs.getString("audit"));
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    if(rs1.getString("type").equals("B")){
                        type_B = 1;
                    }
                    if(rs1.getString("type").equals("C")){
                        type_C = 1;
                    }
                }
                rs1.close();
                ps1.close();

                if(type_B==1 && rs.getInt("status_b")==1){
                    continue;
                }
                if(type_C==1 && rs.getInt("status_c")==1){
                    continue;
                }
                num++;
                type_B=0;
                type_B=0;
            }
            result.setMatlsubs(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM pressureparts WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setMatldist(num);
            num=0;
            rs.close();
            ps.close();

            result.setMatl(result.getMatlin()+result.getMatlsubs()+result.getMatldist());

            ps = conn.prepareStatement("SELECT * FROM rematerialitem WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setMatlreincom(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM rematerial WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setMatlrein(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM protestboardcom WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setTestboardcom(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM productplate WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setTestboard(num);
            num=0;
            rs.close();
            ps.close();

            result.setPyhandche(result.getMatlreincom()+result.getMatlrein()+result.getTestboard()+result.getTestboardcom());

            ps = conn.prepareStatement("SELECT * FROM vacuumparameters WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setVacuum(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setProductmanu(num);
            num=0;
            rs.close();
            ps.close();

            result.setInspecandtest(result.getVacuum()+result.getProductmanu());

            ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE status=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setMeasinstru(num);
            num=0;
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE audit=0");
            rs = ps.executeQuery();
            while (rs.next()){
                num++;
            }
            result.setDesign(num);
            num=0;
            rs.close();
            ps.close();

            result.setResult("success");
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;
    }
}
