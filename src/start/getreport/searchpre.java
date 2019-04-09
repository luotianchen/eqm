package start.getreport;

import start.jdbc.jdbc;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class searchpre {
    public ArrayList<searchpredata> searchpre(String prodno,String name) throws ClassNotFoundException, SQLException, ParseException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d1 = null;
        java.sql.Date d = null;

        ArrayList<searchpredata> as = new ArrayList<searchpredata>();
        searchpredata data = null;

        int ppart_id = 0;
        String ename = null;

        ps = conn.prepareStatement("SELECT * FROM presstestp WHERE presstestp = ?");
        ps.setString(1,name);
        rs = ps.executeQuery();
        if(rs.next()){
            ppart_id = rs.getInt("id");
            ename = rs.getString("ename");
        }
        rs.close();
        ps.close();

        System.out.println(prodno);
        System.out.println(ppart_id);

        ps1 = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ? AND presstestp_id_ppart1 = ?");
        ps1.setString(1,prodno);
        ps1.setInt(2,ppart_id);
        rs1 = ps1.executeQuery();
        System.out.println(2);
        if(rs1.next()){
            System.out.println(1);
            if(rs1.getString("dated1") != null && !rs1.getString("dated1").equals("")){
                data = new searchpredata();
                data.setProdno(prodno);
                data.setDwgno(rs1.getString("dwgno"));
                data.setName(name);
                data.setEname(ename);
                data.setDated(rs1.getString("dated1"));
                data.setTestmedia(rs1.getString("testmedia"));
                data.setEtestmedia(rs1.getString("etestmedia"));
                data.setClcontent(rs1.getString("clcontent"));

                ps = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart =? AND datetype = ?");
                ps.setString(1,prodno);
                ps.setString(2,name);
                ps.setString(3,"dated1");
                rs = ps.executeQuery();
                if(rs.next()){
                    data.setPgaugeno1(rs.getString("pgaugeno1"));
                    data.setCircutemp(rs.getString("circutemp"));
                    data.setMediatemp(rs.getString("mediatemp"));
                    data.setTestpress(rs.getString("testpress"));
                    data.setDewelltime(rs.getString("dewelltime"));
                }
                rs.close();
                ps.close();

                d1 = sdf.parse(data.getDated());
                d = new java.sql.Date(d1.getTime());

                ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE exitno = ? AND ? BETWEEN calibdate AND recalibdate");
                ps.setString(1,data.getPgaugeno1());
                ps.setDate(2,d);
                rs = ps.executeQuery();
                if (rs.next()){
                    String dx = rs.getString("calibdate");
                    data.setAccuclass(rs.getString("accuclass"));
                    data.setMeasrangemax(rs.getString("measrangemax"));
                    data.setMeasrangemin(rs.getString("measrangemin"));
                    data.setCalibdate(rs.getString("calibdate"));
                    data.setType(rs.getString("type"));
                    rs.close();
                    rs = ps.executeQuery();
                    while (rs.next()){
                        if(getDaySub(dx,rs.getString("calibdate"))>0){
                            data.setAccuclass(rs.getString("accuclass"));
                            data.setMeasrangemax(rs.getString("measrangemax"));
                            data.setMeasrangemin(rs.getString("measrangemin"));
                            data.setCalibdate(rs.getString("calibdate"));
                            data.setType(rs.getString("type"));
                            dx = rs.getString("calibdate");
                        }
                    }

                }
                rs.close();
                ps.close();

                as.add(data);


                if(rs1.getString("dated2") != null && !rs1.getString("dated2").equals("")){
                    data = new searchpredata();
                    data.setProdno(prodno);
                    data.setDwgno(rs1.getString("dwgno"));
                    data.setName(name);
                    data.setEname(ename);
                    data.setDated(rs1.getString("dated2"));
                    data.setTestmedia(rs1.getString("testmedia"));
                    data.setEtestmedia(rs1.getString("etestmedia"));
                    data.setClcontent(rs1.getString("clcontent"));

                    ps = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart =? AND datetype = ?");
                    ps.setString(1,prodno);
                    ps.setString(2,name);
                    ps.setString(3,"dated2");
                    rs = ps.executeQuery();
                    if(rs.next()){
                        data.setPgaugeno1(rs.getString("pgaugeno1"));
                        data.setCircutemp(rs.getString("circutemp"));
                        data.setMediatemp(rs.getString("mediatemp"));
                        data.setTestpress(rs.getString("testpress"));
                        data.setDewelltime(rs.getString("dewelltime"));
                    }
                    rs.close();
                    ps.close();

                    d1 = sdf.parse(data.getDated());
                    d = new java.sql.Date(d1.getTime());

                    ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE exitno = ? AND ? BETWEEN calibdate AND recalibdate");
                    ps.setString(1,data.getPgaugeno1());
                    ps.setDate(2,d);
                    rs = ps.executeQuery();
                    if (rs.next()){
                        String dx = rs.getString("calibdate");
                        data.setAccuclass(rs.getString("accuclass"));
                        data.setMeasrangemax(rs.getString("measrangemax"));
                        data.setMeasrangemin(rs.getString("measrangemin"));
                        data.setCalibdate(rs.getString("calibdate"));
                        data.setType(rs.getString("type"));
                        rs.close();
                        rs = ps.executeQuery();
                        while (rs.next()){
                            if(getDaySub(dx,rs.getString("calibdate"))>0){
                                data.setAccuclass(rs.getString("accuclass"));
                                data.setMeasrangemax(rs.getString("measrangemax"));
                                data.setMeasrangemin(rs.getString("measrangemin"));
                                data.setCalibdate(rs.getString("calibdate"));
                                data.setType(rs.getString("type"));
                                dx = rs.getString("calibdate");
                            }
                        }

                    }
                    rs.close();
                    ps.close();

                    as.add(data);

                    if(rs1.getString("dated3") != null && !rs1.getString("dated3").equals("")){
                        data = new searchpredata();
                        data.setProdno(prodno);
                        data.setDwgno(rs1.getString("dwgno"));
                        data.setName(name);
                        data.setEname(ename);
                        data.setDated(rs1.getString("dated3"));
                        data.setTestmedia(rs1.getString("testmedia"));
                        data.setEtestmedia(rs1.getString("etestmedia"));
                        data.setClcontent(rs1.getString("clcontent"));

                        ps = conn.prepareStatement("SELECT * FROM pretest WHERE prodno = ? AND ppart =? AND datetype = ?");
                        ps.setString(1,prodno);
                        ps.setString(2,name);
                        ps.setString(3,"dated3");
                        rs = ps.executeQuery();
                        if(rs.next()){
                            data.setPgaugeno1(rs.getString("pgaugeno1"));
                            data.setCircutemp(rs.getString("circutemp"));
                            data.setMediatemp(rs.getString("mediatemp"));
                            data.setTestpress(rs.getString("testpress"));
                            data.setDewelltime(rs.getString("dewelltime"));
                        }
                        rs.close();
                        ps.close();

                        d1 = sdf.parse(data.getDated());
                        d = new java.sql.Date(d1.getTime());

                        ps = conn.prepareStatement("SELECT * FROM pregaumeatable WHERE exitno = ? AND ? BETWEEN calibdate AND recalibdate");
                        ps.setString(1,data.getPgaugeno1());
                        ps.setDate(2,d);
                        rs = ps.executeQuery();
                        if (rs.next()){
                            String dx = rs.getString("calibdate");
                            data.setAccuclass(rs.getString("accuclass"));
                            data.setMeasrangemax(rs.getString("measrangemax"));
                            data.setMeasrangemin(rs.getString("measrangemin"));
                            data.setCalibdate(rs.getString("calibdate"));
                            data.setType(rs.getString("type"));
                            rs.close();
                            rs = ps.executeQuery();
                            while (rs.next()){
                                if(getDaySub(dx,rs.getString("calibdate"))>0){
                                    data.setAccuclass(rs.getString("accuclass"));
                                    data.setMeasrangemax(rs.getString("measrangemax"));
                                    data.setMeasrangemin(rs.getString("measrangemin"));
                                    data.setCalibdate(rs.getString("calibdate"));
                                    data.setType(rs.getString("type"));
                                    dx = rs.getString("calibdate");
                                }
                            }

                        }
                        rs.close();
                        ps.close();

                        as.add(data);

                    }



                }

            }
        }
        rs1.close();
        ps1.close();


        return as;
    }


    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate;
        java.util.Date endDate;
        try
        {
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
        } catch (ParseException e)
        {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }
        return day;
    }
}
