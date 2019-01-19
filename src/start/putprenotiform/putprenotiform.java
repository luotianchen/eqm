package start.putprenotiform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putprenotiform {                                                         //提交试压通知单
    @RequestMapping(value = "putprenotiform")
    public @ResponseBody putprenotiformresult putprenotiform(@RequestBody putprenotiformpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;

        putprenotiformresult result = new putprenotiformresult();

        java.sql.Date time = new java.sql.Date(new java.util.Date().getTime());

        int ppart_id = 0;
        try {
            if(pp.getTestmedia()==null || pp.getTestmedia().equals("")){
                result.setResult("fail");
            }else {
                ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
                ps.setString(1,pp.getProdno());
                rs = ps.executeQuery();
                if(rs.next()){
                    ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ? AND ename = ?");
                    ps.setString(1,pp.getPpart());
                    ps.setString(2,pp.getEppart());
                    rs = ps.executeQuery();
                    if(rs.next()){
                        ppart_id = rs.getInt("id");
                        rs.close();
                        ps.close();
                        ps = conn.prepareStatement("UPDATE prenotiform SET prodno = ? ,dwgno = ? ,presstestp_id_ppart1=? ,dated1=? ," +
                                "testmedia = ? , etestmedia = ? , clcontent = ? , user = ? , date = ? WHERE prodno = ?");
                        ps.setString(1,pp.getProdno());
                        ps.setString(2,pp.getDwgno());
                        ps.setInt(3,ppart_id);
                        ps.setString(4,pp.getDated1());
                        ps.setString(5,pp.getTestmedia());
                        ps.setString(6,pp.getEtestmedia());
                        ps.setString(7,pp.getClcontent());
                        ps.setString(8,pp.getUser());
                        ps.setDate(9,time);
                        ps.setString(10,pp.getProdno());
                        ps.executeUpdate();
                        ps.close();

                        if(!(pp.getDated2() == null||pp.getDated2().equals(""))){
                            ps = conn.prepareStatement("UPDATE prenotiform SET dated2 = ? WHERE prodno = ? AND presstestp_id_ppart1 = ?");
                            ps.setString(1,pp.getDated2());
                            ps.setString(2,pp.getProdno());
                            ps.setInt(3,ppart_id);
                            ps.executeUpdate();
                            ps.close();
                        }
                        if(!(pp.getDated3() == null||pp.getDated3().equals(""))){
                            ps = conn.prepareStatement("UPDATE prenotiform SET dated3 = ? WHERE prodno = ? AND presstestp_id_ppart1 = ?");
                            ps.setString(1,pp.getDated3());
                            ps.setString(2,pp.getProdno());
                            ps.setInt(3,ppart_id);
                            ps.executeUpdate();
                            ps.close();
                        }
                        result.setResult("success");
                    }else {
                        rs.close();
                        ps.close();
                        result.setResult("fail");
                    }
                }else {
                    ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ? AND ename = ?");
                    ps.setString(1,pp.getPpart());
                    ps.setString(2,pp.getEppart());
                    rs = ps.executeQuery();
                    if(rs.next()){
                        ppart_id = rs.getInt("id");
                        rs.close();
                        ps.close();
                        ps = conn.prepareStatement("INSERT INTO prenotiform(prodno,dwgno,presstestp_id_ppart1,dated1," +
                                "testmedia,etestmedia,clcontent,user,date) VALUES (?,?,?,?," +
                                "?,?,?,?,?)");
                        ps.setString(1,pp.getProdno());
                        ps.setString(2,pp.getDwgno());
                        ps.setInt(3,ppart_id);
                        ps.setString(4,pp.getDated1());
                        ps.setString(5,pp.getTestmedia());
                        ps.setString(6,pp.getEtestmedia());
                        ps.setString(7,pp.getClcontent());
                        ps.setString(8,pp.getUser());
                        ps.setDate(9,time);
                        ps.executeUpdate();
                        ps.close();

                        if(!(pp.getDated2() == null||pp.getDated2().equals(""))){
                            ps = conn.prepareStatement("UPDATE prenotiform SET dated2 = ? WHERE prodno = ? AND presstestp_id_ppart1 = ?");
                            ps.setString(1,pp.getDated2());
                            ps.setString(2,pp.getProdno());
                            ps.setInt(3,ppart_id);
                            ps.executeUpdate();
                            ps.close();
                        }
                        if(!(pp.getDated3() == null||pp.getDated3().equals(""))){
                            ps = conn.prepareStatement("UPDATE prenotiform SET dated3 = ? WHERE prodno = ? AND presstestp_id_ppart1 = ?");
                            ps.setString(1,pp.getDated3());
                            ps.setString(2,pp.getProdno());
                            ps.setInt(3,ppart_id);
                            ps.executeUpdate();
                            ps.close();
                        }
                        result.setResult("success");
                    }else {
                        rs.close();
                        ps.close();
                        result.setResult("fail");
                    }
                }



            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();

        return result;
    }
}
