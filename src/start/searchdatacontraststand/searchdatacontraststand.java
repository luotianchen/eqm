package start.searchdatacontraststand;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.sql.*;
import java.util.ArrayList;

@CrossOrigin
@Controller
public class searchdatacontraststand {                                      //通过图号查询资料标准
    @RequestMapping(value = "searchdatacontraststand")
    public @ResponseBody searchdatacontraststandresult searchdatacontraststand(@RequestBody searchdatacontraststandpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchdatacontraststandresult result = new searchdatacontraststandresult();
        searchdatacontraststanddata data = new searchdatacontraststanddata();
        ArrayList<Double> aweldmaxangul = new ArrayList<Double>();
        ArrayList<Double> bweldmaxangul = new ArrayList<Double>();
        ArrayList<Double> aweldmaxalign = new ArrayList<Double>();
        ArrayList<Double> bweldmaxalign = new ArrayList<Double>();
        ArrayList<Double> weldreinfs = new ArrayList<Double>();
        ArrayList<Double> weldreinfd = new ArrayList<Double>();
        ArrayList<Double> innerdia = new ArrayList<Double>();
        ArrayList<Double> roundness = new ArrayList<Double>();
        ArrayList<Double> straightness = new ArrayList<Double>();
        ArrayList<Double> outward = new ArrayList<Double>();
        ArrayList<Double> concave = new ArrayList<Double>();

        ArrayList<String> shthick_data = new ArrayList<String>();

        String stand = null;
        int shthick = 0;
        int stand_id = 0;
        double length = 0;

        try {
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps.setString(1,sp.getDwgno());
            rs = ps.executeQuery();
            if(rs.next()){
                stand = rs.getString("minorstand");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE stand = ?");
            ps.setString(1,stand);
            rs = ps.executeQuery();
            if(rs.next()){
                stand_id = rs.getInt("id");
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1");
            ps.setString(1,sp.getDwgno());
            rs = ps.executeQuery();
            if(rs.next()){
                if(!(rs.getString("shthick1") == null || rs.getString("shthick1").equals(""))){
                    shthick_data.add(rs.getString("shthick1"));

                    shthick = rs.getInt("shthick1");
                    ps1 = conn.prepareStatement("SELECT * FROM datacontraststand WHERE stand = ? ");
                    ps1.setInt(1,stand_id);
                    rs1 = ps1.executeQuery();
                    while (rs1.next()){
                        if(rs1.getInt("leixing") == 1){
                            jisuan1(aweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 2){
                            jisuan1(bweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 3){
                            jisuan1(aweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 4){
                            jisuan1(bweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 5){
                            jisuan1(weldreinfs,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 6){
                            jisuan1(weldreinfd,rs1,shthick);
                        }

                    }
                    rs1.close();
                    ps1.close();
                }
                if(!(rs.getString("shthick2") == null || rs.getString("shthick2").equals(""))){
                    shthick_data.add(rs.getString("shthick2"));

                    shthick = rs.getInt("shthick2");
                    ps1 = conn.prepareStatement("SELECT * FROM datacontraststand WHERE stand = ? ");
                    ps1.setInt(1,stand_id);
                    rs1 = ps1.executeQuery();
                    while (rs1.next()){
                        if(rs1.getInt("leixing") == 1){
                            jisuan1(aweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 2){
                            jisuan1(bweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 3){
                            jisuan1(aweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 4){
                            jisuan1(bweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 5){
                            jisuan1(weldreinfs,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 6){
                            jisuan1(weldreinfd,rs1,shthick);
                        }

                    }
                    rs1.close();
                    ps1.close();
                }
                if(!(rs.getString("shthick3") == null || rs.getString("shthick3").equals(""))){
                    shthick_data.add(rs.getString("shthick3"));

                    shthick = rs.getInt("shthick3");
                    ps1 = conn.prepareStatement("SELECT * FROM datacontraststand WHERE stand = ? ");
                    ps1.setInt(1,stand_id);
                    rs1 = ps1.executeQuery();
                    while (rs1.next()){
                        if(rs1.getInt("leixing") == 1){
                            jisuan1(aweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 2){
                            jisuan1(bweldmaxangul,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 3){
                            jisuan1(aweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 4){
                            jisuan1(bweldmaxalign,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 5){
                            jisuan1(weldreinfs,rs1,shthick);
                        }
                        if(rs1.getInt("leixing") == 6){
                            jisuan1(weldreinfd,rs1,shthick);
                        }

                    }
                    rs1.close();
                    ps1.close();
                }
            }
            rs.close();
            ps.close();

            PreparedStatement ps2 = null;
            ResultSet rs2=null;
            PreparedStatement ps3 = null;
            ResultSet rs3=null;


            ps2 = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps2.setString(1,sp.getDwgno());
            rs2 = ps2.executeQuery();
            while (rs2.next()){
                data.setProheight(rs2.getDouble("proheight"));
                length = rs2.getDouble("length");
                data.setLength(length);
            }
            rs2.close();
            ps2.close();

            ps2 = conn.prepareStatement("SELECT * FROM datacontraststand WHERE stand = ?");
            ps2.setInt(1,stand_id);
            rs2 = ps2.executeQuery();
            while (rs2.next()){
                if(rs2.getInt("leixing") == 8){
                    jisuan1(straightness,rs2,length);
                }
            }
            rs2.close();
            ps2.close();


            ps2 = conn.prepareStatement("SELECT * FROM channeldata WHERE dwgno = ? AND status = 1");
            ps2.setString(1,sp.getDwgno());
            rs2 = ps2.executeQuery();
            while (rs2.next()){
                innerdia.add(rs2.getDouble("innerdia"));
            }
            rs2.close();
            ps2.close();

            for(int i =0;i<innerdia.size();i++) {
                ps2 = conn.prepareStatement("SELECT * FROM datacontraststand WHERE stand = ?");
                ps2.setInt(1, stand_id);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if(rs2.getInt("leixing") == 9){
                        jisuan1(outward,rs2,innerdia.get(i));
                    }
                    if(rs2.getInt("leixing") == 10){
                        jisuan1(concave,rs2,innerdia.get(i));
                    }
                    if(rs2.getInt("leixing") == 7){
                        jisuan1(roundness,rs2,innerdia.get(i));
                    }
                }
                rs2.close();
                ps2.close();
            }


            data.setRoundness(roundness);
            data.setAweldmaxalign(aweldmaxalign);
            data.setBweldmaxalign(bweldmaxalign);
            data.setAweldmaxangul(aweldmaxangul);
            data.setBweldmaxangul(bweldmaxangul);
            data.setWeldreinfd(weldreinfd);
            data.setWeldreinfs(weldreinfs);
            data.setInnerdia(innerdia);
            data.setShthick(shthick_data);
            if(!(straightness.get(0)==null || straightness.get(0).equals(""))){
                data.setStraightness(straightness.get(0));
            }
            data.setOutward(outward);
            data.setConcave(concave);
            result.setData(data);
            result.setResult("success");
        }catch (Exception e){
            result.setResult(e.toString());
        }
        conn.close();
        return result;
    }











    public void jisuan1(ArrayList<Double> ad , ResultSet rs ,double shthick) throws SQLException {
        System.out.println(rs.getString("id"));
        if(!(rs.getString("spec_small") == null || rs.getString("spec_small").equals(""))){
            if(shthick>rs.getInt("spec_small")){
                if(!(rs.getString("spec_big") == null || rs.getString("spec_big").equals(""))){
                    if(shthick<=rs.getInt("spec_big")){
                        if(rs.getString("contrast").equals("L")){
                            if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                                if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                    ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("formula1"));
                                }
                            }else {
                                if(rs.getString("formula1_operation").equals("/")){
                                    if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                        ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                    }else {
                                        ad.add(rs.getDouble("formula1"));
                                    }
                                }
                                if(rs.getString("formula1_operation").equals("%")){
                                    if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                        ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                    }else {
                                        ad.add(rs.getDouble("formula1"));
                                    }
                                }
                            }
                        }else {
                            if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                                if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                    ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("contrast"));
                                }
                            }else {
                                if(rs.getString("formula1_operation").equals("/")){
                                    if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                        ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                    }else {
                                        ad.add(rs.getDouble("contrast"));
                                    }
                                }
                                if(rs.getString("formula1_operation").equals("%")){
                                    if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                        ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                    }else {
                                        ad.add(rs.getDouble("contrast"));
                                    }
                                }
                            }
                        }
                    }
                }else {
                    if(rs.getString("contrast").equals("L")){
                        if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                            if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("formula1"));
                            }
                        }else {
                            if(rs.getString("formula1_operation").equals("/")){
                                if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                    ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("formula1"));
                                }
                            }
                            if(rs.getString("formula1_operation").equals("%")){
                                if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                    ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("formula1"));
                                }
                            }
                        }
                    }else {
                        if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                            if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("contrast"));
                            }
                        }else {
                            if(rs.getString("formula1_operation").equals("/")){
                                if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                    ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("contrast"));
                                }
                            }
                            if(rs.getString("formula1_operation").equals("%")){
                                if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                    ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("contrast"));
                                }
                            }
                        }
                    }
                }
            }
        }else {
            if(!(rs.getString("spec_big") == null || rs.getString("spec_big").equals(""))){
                if(shthick<=rs.getInt("spec_big")){
                    if(rs.getString("contrast").equals("L")){
                        if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                            if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("formula1"));
                            }
                        }else {
                            if(rs.getString("formula1_operation").equals("/")){
                                if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                    ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("formula1"));
                                }
                            }
                            if(rs.getString("formula1_operation").equals("%")){
                                if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                    ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("formula1"));
                                }
                            }
                        }
                    }else {
                        if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                            if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("contrast"));
                            }
                        }else {
                            if(rs.getString("formula1_operation").equals("/")){
                                if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                    ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("contrast"));
                                }
                            }
                            if(rs.getString("formula1_operation").equals("%")){
                                if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                    ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                                }else {
                                    ad.add(rs.getDouble("contrast"));
                                }
                            }
                        }
                    }
                }
            }else {
                if(rs.getString("contrast").equals("L")){
                    if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                        if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                            ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                        }else {
                            ad.add(rs.getDouble("formula1"));
                        }
                    }else {
                        if(rs.getString("formula1_operation").equals("/")){
                            if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("formula1"));
                            }
                        }
                        if(rs.getString("formula1_operation").equals("%")){
                            if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("formula1")){
                                ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("formula1"));
                            }
                        }
                    }
                }else {

                    if((rs.getString("formula1_operation")==null || rs.getString("formula1_operation").equals(""))){
                        if((rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                            ad.add(Double.valueOf(rs.getDouble("formula1") + rs.getDouble("formula2")));
                        }else {
                            ad.add(rs.getDouble("contrast"));
                        }
                    }else {
                        if(rs.getString("formula1_operation").equals("/")){
                            if((shthick/rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                ad.add(Double.valueOf(shthick/rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("contrast"));
                            }
                        }
                        if(rs.getString("formula1_operation").equals("%")){
                            if((shthick*rs.getDouble("formula1") + rs.getDouble("formula2"))<rs.getDouble("contrast") || rs.getInt("contrast") == 0){
                                ad.add(Double.valueOf(shthick*rs.getDouble("formula1") + rs.getDouble("formula2")));
                            }else {
                                ad.add(rs.getDouble("contrast"));
                            }
                        }
                    }
                }
            }
        }
    }


}
