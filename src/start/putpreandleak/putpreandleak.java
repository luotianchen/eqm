package start.putpreandleak;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;

@CrossOrigin
@Controller
public class putpreandleak {                                    //提交压力参数和泄漏参数
    @RequestMapping(value = "putpreandleak")
    public @ResponseBody putpreandleakresult putpreandleak(@RequestBody putpreandleakpost pp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;

        putpreandleakresult result = new putpreandleakresult();

        try {
            ps = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ?");
            ps.setString(1,pp.getProdno());
            rs = ps.executeQuery();
            if(rs.next()){
                ps1 = conn.prepareStatement("DELETE FROM pretest WHERE prodno = ?");
                ps1.setString(1,pp.getProdno());
                ps1.executeUpdate();
                ps1.close();

                ps1 = conn.prepareStatement("DELETE FROM leakagetest WHERE prodno = ?");
                ps1.setString(1,pp.getProdno());
                ps1.executeUpdate();
                ps1.close();
            }
            rs.close();
            ps.close();


            if(pp.getData().getDated1().getStatus() == 0){
                result.setResult("fail");
            }else {
                ps = conn.prepareStatement("INSERT INTO pretest(prodno,date,pgaugeno1,pgaugeno2," +
                        "dewelltime,circutemp,mediatemp," +
                        "testpress,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                        "?,?,?," +
                        "?,?,?,?)");
                ps.setString(1,pp.getProdno());
                ps.setString(2,pp.getData().getDated1().getPress().getDate());
                ps.setString(3,pp.getData().getDated1().getPress().getPgaugeno1());
                ps.setString(4,pp.getData().getDated1().getPress().getPgaugeno2());
                ps.setInt(5,pp.getData().getDated1().getPress().getDewelltime());
                ps.setInt(6,pp.getData().getDated1().getPress().getCircutemp());
                ps.setInt(7,pp.getData().getDated1().getPress().getMediatemp());
                ps.setDouble(8,pp.getData().getDated1().getPress().getTestpress());
                ps.setString(9,pp.getData().getDated1().getPress().getPpart());
                ps.setString(10,pp.getData().getDated1().getPress().getTestmedia());
                ps.setString(11,"dated1");
                ps.executeUpdate();
                ps.close();

                ps = conn.prepareStatement("INSERT INTO leakagetest(prodno,date,pgaugeno1,pgaugeno2," +
                        "dewelltime,circutemp,mediatemp," +
                        "leaktestp,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                        "?,?,?," +
                        "?,?,?,?)");
                ps.setString(1,pp.getProdno());
                ps.setString(2,pp.getData().getDated1().getLeak().getDate());
                ps.setString(3,pp.getData().getDated1().getLeak().getPgaugeno1());
                ps.setString(4,pp.getData().getDated1().getLeak().getPgaugeno2());
                ps.setInt(5,pp.getData().getDated1().getLeak().getDewelltime());
                ps.setInt(6,pp.getData().getDated1().getLeak().getCircutemp());
                ps.setInt(7,pp.getData().getDated1().getLeak().getMediatemp());
                ps.setDouble(8,pp.getData().getDated1().getLeak().getLeaktestp());
                ps.setString(9,pp.getData().getDated1().getLeak().getPpart());
                ps.setString(10,pp.getData().getDated1().getLeak().getTestmedia());
                ps.setString(11,"dated1");
                ps.executeUpdate();
                ps.close();

                if(pp.getData().getDated2().getStatus() != 0){
                    ps = conn.prepareStatement("INSERT INTO pretest(prodno,date,pgaugeno1,pgaugeno2," +
                            "dewelltime,circutemp,mediatemp," +
                            "testpress,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                            "?,?,?," +
                            "?,?,?,?)");
                    ps.setString(1,pp.getProdno());
                    ps.setString(2,pp.getData().getDated2().getPress().getDate());
                    ps.setString(3,pp.getData().getDated2().getPress().getPgaugeno1());
                    ps.setString(4,pp.getData().getDated2().getPress().getPgaugeno2());
                    ps.setInt(5,pp.getData().getDated2().getPress().getDewelltime());
                    ps.setInt(6,pp.getData().getDated2().getPress().getCircutemp());
                    ps.setInt(7,pp.getData().getDated2().getPress().getMediatemp());
                    ps.setDouble(8,pp.getData().getDated2().getPress().getTestpress());
                    ps.setString(9,pp.getData().getDated2().getPress().getPpart());
                    ps.setString(10,pp.getData().getDated2().getPress().getTestmedia());
                    ps.setString(11,"dated2");
                    ps.executeUpdate();
                    ps.close();

                    ps = conn.prepareStatement("INSERT INTO leakagetest(prodno,date,pgaugeno1,pgaugeno2," +
                            "dewelltime,circutemp,mediatemp," +
                            "leaktestp,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                            "?,?,?," +
                            "?,?,?,?)");
                    ps.setString(1,pp.getProdno());
                    ps.setString(2,pp.getData().getDated2().getLeak().getDate());
                    ps.setString(3,pp.getData().getDated2().getLeak().getPgaugeno1());
                    ps.setString(4,pp.getData().getDated2().getLeak().getPgaugeno2());
                    ps.setInt(5,pp.getData().getDated2().getLeak().getDewelltime());
                    ps.setInt(6,pp.getData().getDated2().getLeak().getCircutemp());
                    ps.setInt(7,pp.getData().getDated2().getLeak().getMediatemp());
                    ps.setDouble(8,pp.getData().getDated2().getLeak().getLeaktestp());
                    ps.setString(9,pp.getData().getDated2().getLeak().getPpart());
                    ps.setString(10,pp.getData().getDated2().getLeak().getTestmedia());
                    ps.setString(11,"dated2");
                    ps.executeUpdate();
                    ps.close();

                }

                if(pp.getData().getDated3().getStatus() != 0){
                    ps = conn.prepareStatement("INSERT INTO pretest(prodno,date,pgaugeno1,pgaugeno2," +
                            "dewelltime,circutemp,mediatemp," +
                            "testpress,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                            "?,?,?," +
                            "?,?,?,?)");
                    ps.setString(1,pp.getProdno());
                    ps.setString(2,pp.getData().getDated3().getPress().getDate());
                    ps.setString(3,pp.getData().getDated3().getPress().getPgaugeno1());
                    ps.setString(4,pp.getData().getDated3().getPress().getPgaugeno2());
                    ps.setInt(5,pp.getData().getDated3().getPress().getDewelltime());
                    ps.setInt(6,pp.getData().getDated3().getPress().getCircutemp());
                    ps.setInt(7,pp.getData().getDated3().getPress().getMediatemp());
                    ps.setDouble(8,pp.getData().getDated3().getPress().getTestpress());
                    ps.setString(9,pp.getData().getDated3().getPress().getPpart());
                    ps.setString(10,pp.getData().getDated3().getPress().getTestmedia());
                    ps.setString(11,"dated3");
                    ps.executeUpdate();
                    ps.close();

                    ps = conn.prepareStatement("INSERT INTO leakagetest(prodno,date,pgaugeno1,pgaugeno2," +
                            "dewelltime,circutemp,mediatemp," +
                            "leaktestp,ppart,testmedia,datetype) VALUES (?,?,?,?," +
                            "?,?,?," +
                            "?,?,?,?)");
                    ps.setString(1,pp.getProdno());
                    ps.setString(2,pp.getData().getDated3().getLeak().getDate());
                    ps.setString(3,pp.getData().getDated3().getLeak().getPgaugeno1());
                    ps.setString(4,pp.getData().getDated3().getLeak().getPgaugeno2());
                    ps.setInt(5,pp.getData().getDated3().getLeak().getDewelltime());
                    ps.setInt(6,pp.getData().getDated3().getLeak().getCircutemp());
                    ps.setInt(7,pp.getData().getDated3().getLeak().getMediatemp());
                    ps.setDouble(8,pp.getData().getDated3().getLeak().getLeaktestp());
                    ps.setString(9,pp.getData().getDated3().getLeak().getPpart());
                    ps.setString(10,pp.getData().getDated3().getLeak().getTestmedia());
                    ps.setString(11,"dated3");
                    ps.executeUpdate();
                    ps.close();

                }
                result.setResult("success");
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
