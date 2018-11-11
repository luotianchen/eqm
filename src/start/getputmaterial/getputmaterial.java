package start.getputmaterial;

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
public class getputmaterial {                                                                              //获取入库登记基础信息
    @RequestMapping(value = "getputmaterial" ,method = RequestMethod.GET)
    public @ResponseBody getputmaterialresult getputmaterial() throws ClassNotFoundException, SQLException {          //生存单位，在单位名称内
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;


        ps=conn.prepareStatement("SELECT * FROM warrantystatus");                           //查询质保书情况
        rs=ps.executeQuery();
        getputmaterialresult res=new getputmaterialresult();
        getputmaterialdata data=new getputmaterialdata();
        warrantystatusdata wdata = null;
        ArrayList<warrantystatusdata> warrantysitu = new ArrayList<warrantystatusdata>();
        while(rs.next()){
            wdata = new warrantystatusdata();
            wdata.setCertsitu(rs.getString("certsitu"));                         //返回质保书情况
            warrantysitu.add(wdata);
            res.setResult("success");                                                       //添加成功
        }
        data.setWarrantysitu(warrantysitu);                                                     //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM matlname");                                //查询材料名称
        rs=ps.executeQuery();
        ArrayList<String> matlname = new ArrayList<String>();
        while(rs.next()){
            matlname.add(rs.getString("matlname"));                             //返回材料名称
            res.setResult("success");                                                       //添加成功
        }
        data.setMatlname(matlname);                                                         //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM millunit");                             //查询生产单位
        rs=ps.executeQuery();
        ArrayList<String> millunit = new ArrayList<String>();
        while(rs.next()){
            millunit.add(rs.getString("millunit"));                             ////返回生产单位
            res.setResult("success");                                                       //添加成功
        }
        data.setMillunit(millunit);                                                         //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM supplier");                             //查询供货单位
        rs=ps.executeQuery();
        ArrayList<String> supplier = new ArrayList<String>();
        while(rs.next()){
            supplier.add(rs.getString("supplier"));                             ////返回供货单位
            res.setResult("success");                                                       //添加成功
        }
        data.setSupplier(supplier);                                                         //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM contraststand");                             //查询对照标准表
        rs=ps.executeQuery();
        ArrayList<String> matlstand = new ArrayList<String>();
        while(rs.next()){
            matlstand.add(rs.getString("matlstand"));                             ////返回材料标准号
            res.setResult("success");                                                       //添加成功
        }
        data.setMatlstand(matlstand);                                                           //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM putmaterial");                             //查询入库登记表
        rs=ps.executeQuery();
        ArrayList<String> modelstand = new ArrayList<String>();
        while(rs.next()){
            modelstand.add(rs.getString("modelstand"));                             ////返回型号标准
            res.setResult("success");                                                       //添加成功
        }
        data.setModelstand(modelstand);                                                           //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM contraststand");                             //查询对照标准表
        rs=ps.executeQuery();
        ArrayList<String> designation = new ArrayList<String>();
        while(rs.next()){
            designation.add(rs.getString("designation"));                             ////返回牌号
            res.setResult("success");                                                       //添加成功
        }
        data.setDesignation(designation);                                                         //添加到data
        rs.close();
        ps.close();


        ps=conn.prepareStatement("SELECT * FROM heattreatcondition");                             //查询热处理状态
        rs=ps.executeQuery();
        ArrayList<String> heatcondi = new ArrayList<String>();
        while(rs.next()){
            heatcondi.add(rs.getString("heatcondi"));                             ////返回热处理状态
            res.setResult("success");                                                       //添加成功
        }
        data.setHeatcondi(heatcondi);                                                        //添加到data
        rs.close();
        ps.close();



        conn.close();

        res.setData(data);                                                                          //返回data
        return res;
    }
}