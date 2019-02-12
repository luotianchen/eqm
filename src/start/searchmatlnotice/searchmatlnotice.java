package start.searchmatlnotice;

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
public class searchmatlnotice {                                                     //入库通知单接口
    @RequestMapping(value = "searchmatlnotice")
    public @ResponseBody searchmatlnoticeresult searchmatlnotice(@RequestBody searchmatlnoticepost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        searchmatlnoticedata data =null;
        ArrayList<searchmatlnoticedata> as = new ArrayList<searchmatlnoticedata>();
        searchmatlnoticeresult result = new searchmatlnoticeresult();

        int user_id=0;


        try {
            if(!(sp.getMatlcode()==null || sp.getMatlcode().equals(""))){
                if(!(sp.getMatlcode().charAt(0)!='1' &&  sp.getMatlcode().charAt(0)!='2' &&  sp.getMatlcode().charAt(0)!='7' &&  sp.getMatlcode().charAt(0)!='8' &&  sp.getMatlcode().charAt(0)!='5')){
                    int inmonth =Integer.parseInt(sp.getMonth());
                    if(inmonth<10){
                        sp.setMonth("0"+sp.getMonth());
                    }
                    String year_month = sp.getYear()+"-"+sp.getMonth()+"%";

                    ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE indate LIKE ?");
                    ps.setString(1,year_month);
                    rs = ps.executeQuery();
                    while (rs.next()){
                        if(sp.getMatlcode().charAt(0)==(rs.getString("codedmarking").charAt(4))){
                            data = new searchmatlnoticedata();
                            user_id=rs.getInt("user_id");
                            data.setAudit_user(rs.getString("audit_user"));
                            data.setCodedmarking(rs.getString("codedmarking"));
                            data.setSpec(rs.getString("spec"));
                            data.setQty(rs.getString("qty"));
                            data.setWarrantyno(rs.getString("warrantyno"));
                            data.setHeatbatchno(rs.getString("heatbatchno"));
                            data.setDimension(rs.getString("dimension"));
                            data.setIndate(rs.getString("indate"));
                            if(sp.getMatlcode().charAt(0)=='5'){
                                ps1 = conn.prepareStatement("SELECT * FROM weldingcard WHERE matlname_id_matlname=?");
                                ps1.setInt(1,rs.getInt("matlname_id_matlname"));
                                rs1 = ps1.executeQuery();
                                while (rs1.next()){
                                    data.setPack(rs1.getString("pack"));
                                    data.setHumisitu(rs1.getString("humisitu"));
                                    data.setCoating(rs1.getString("coating"));
                                    data.setRustsitu(rs1.getString("rustsitu"));
                                    data.setEccentricity(rs1.getString("eccentricity"));
                                    data.setPackcheck(rs1.getString("packcheck"));
                                    data.setRustsitu1(rs1.getString("rustsitu1"));
                                    data.setDiamtest(rs1.getString("diamtest"));
                                    data.setPackcheck1(rs1.getString("packcheck1"));
                                    data.setHumisitu1(rs1.getString("humisitu1"));
                                    data.setGraincheck(rs1.getString("graincheck"));
                                }
                                rs1.close();
                                ps1.close();
                            }

                            ps1 = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
                            ps1.setInt(1,rs.getInt("matlname_id_matlname"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setMatlname(rs1.getString("matlname"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                            ps1.setInt(1,rs.getInt("contraststand_id_designation"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setDesignation(rs1.getString("designation"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                            ps1.setInt(1,rs.getInt("contraststand_id_matlstand"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setMatlstand(rs1.getString("matlstand"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM warrantystatus WHERE id = ?");
                            ps1.setInt(1,rs.getInt("warrantystatus_id_certsitu"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setWarrantysitu(rs1.getString("certsitu"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM millunit WHERE id = ?");
                            ps1.setInt(1,rs.getInt("millunit_id_millunit"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setMillunit(rs1.getString("millunit"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM supplier WHERE id = ?");
                            ps1.setInt(1,rs.getInt("supplier_id_supplier"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setSupplier(rs1.getString("supplier"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM modelstand WHERE id = ?");
                            ps1.setInt(1,rs.getInt("modelstand_id_modelstand"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setModelstand(rs1.getString("modelstand"));
                            }
                            rs1.close();
                            ps1.close();

                            ps1 = conn.prepareStatement("SELECT * FROM userform WHERE id = ?");
                            ps1.setInt(1,user_id);
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setUser(rs1.getString("username"));
                            }
                            rs1.close();
                            ps1.close();

                            as.add(data);
                        }else {
                            continue;
                        }
                    }
                    rs.close();
                    ps.close();
                    result.setResult("success");
                    result.setData(as);
                }else {
                    result.setResult("fail");
                }
            }else {
                if(sp.getCodedmarking().charAt(4)=='1' || sp.getCodedmarking().charAt(4)=='2' || sp.getCodedmarking().charAt(4)=='7' || sp.getCodedmarking().charAt(4)=='8' || sp.getCodedmarking().charAt(4)=='5' ){
                    ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking=? ");
                    ps.setString(1,sp.getCodedmarking());
                    rs = ps.executeQuery();
                    while (rs.next()){
                        data = new searchmatlnoticedata();
                        user_id=rs.getInt("user_id");
                        data.setAudit_user(rs.getString("audit_user"));
                        data.setCodedmarking(rs.getString("codedmarking"));
                        data.setSpec(rs.getString("spec"));
                        data.setQty(rs.getString("qty"));
                        data.setWarrantyno(rs.getString("warrantyno"));
                        data.setHeatbatchno(rs.getString("heatbatchno"));
                        data.setDimension(rs.getString("dimension"));
                        data.setIndate(rs.getString("indate"));
                        if((rs.getString("codedmarking").charAt(4))=='5'){
                            ps1 = conn.prepareStatement("SELECT * FROM weldingcard WHERE matlname_id_matlname=?");
                            ps1.setInt(1,rs.getInt("matlname_id_matlname"));
                            rs1 = ps1.executeQuery();
                            while (rs1.next()){
                                data.setPack(rs1.getString("pack"));
                                data.setHumisitu(rs1.getString("humisitu"));
                                data.setCoating(rs1.getString("coating"));
                                data.setRustsitu(rs1.getString("rustsitu"));
                                data.setEccentricity(rs1.getString("eccentricity"));
                                data.setPackcheck(rs1.getString("packcheck"));
                                data.setRustsitu1(rs1.getString("rustsitu1"));
                                data.setDiamtest(rs1.getString("diamtest"));
                                data.setPackcheck1(rs1.getString("packcheck1"));
                                data.setHumisitu1(rs1.getString("humisitu1"));
                                data.setGraincheck(rs1.getString("graincheck"));
                            }
                            rs1.close();
                            ps1.close();
                        }

                        ps1 = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
                        ps1.setInt(1,rs.getInt("matlname_id_matlname"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setMatlname(rs1.getString("matlname"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                        ps1.setInt(1,rs.getInt("contraststand_id_designation"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setDesignation(rs1.getString("designation"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
                        ps1.setInt(1,rs.getInt("contraststand_id_matlstand"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setMatlstand(rs1.getString("matlstand"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM warrantystatus WHERE id = ?");
                        ps1.setInt(1,rs.getInt("warrantystatus_id_certsitu"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setWarrantysitu(rs1.getString("certsitu"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM millunit WHERE id = ?");
                        ps1.setInt(1,rs.getInt("millunit_id_millunit"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setMillunit(rs1.getString("millunit"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM supplier WHERE id = ?");
                        ps1.setInt(1,rs.getInt("supplier_id_supplier"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setSupplier(rs1.getString("supplier"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM modelstand WHERE id = ?");
                        ps1.setInt(1,rs.getInt("modelstand_id_modelstand"));
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setModelstand(rs1.getString("modelstand"));
                        }
                        rs1.close();
                        ps1.close();

                        ps1 = conn.prepareStatement("SELECT * FROM userform WHERE id = ?");
                        ps1.setInt(1,user_id);
                        rs1 = ps1.executeQuery();
                        while (rs1.next()){
                            data.setUser(rs1.getString("username"));
                        }
                        rs1.close();
                        ps1.close();

                        as.add(data);
                    }
                    rs.close();
                    ps.close();
                    Collections.reverse(as);                                          //将list倒序
                    result.setResult("success");
                    result.setData(as);
                }else {
                    result.setResult("fail");
                }
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;
    }
}
