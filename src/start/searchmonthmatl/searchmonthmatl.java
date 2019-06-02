package start.searchmonthmatl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchmonthmatl {
    @RequestMapping(value = "searchmonthmatlresult")                                        //月材料查询
    public @ResponseBody searchmonthmatlresult searchmonthmatlresult(@RequestBody searchmonthmatlpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        int inmonth =Integer.parseInt(sp.getInmonth());
        if(inmonth<10){
            sp.setInmonth("0"+sp.getInmonth());
        }

        int warrantysitu_id=0;
        int supplier_id=0;
        int millunit_id=0;
        int matlname_id=0;
        int matlstand_id=0;
        int designation_id=0;
        int heatcondi_id=0;

        int index = 0;

        String year_month = sp.getInyear()+"-"+sp.getInmonth()+"%";

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        searchmonthmatlresult result = new searchmonthmatlresult();
        searchmonthmatldata data = null;
        ArrayList<searchmonthmatldata> as = new ArrayList<searchmonthmatldata>();
        ArrayList<searchmonthmatldata> as_q =null;

        try {
            ps = conn.prepareStatement("SELECT * FROM matlcoderules");
            rs = ps.executeQuery();
            if(rs.next()){
                index = rs.getInt("indexx");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE indate LIKE ? ORDER BY codedmarking ASC");
            ps.setString(1,year_month);
            rs=ps.executeQuery();
            while (rs.next()){
                data = new searchmonthmatldata();
                if(sp.getMatlcode().charAt(0)==(rs.getString("codedmarking").charAt(index-1))){
                    data.setCodedmarking(rs.getString("codedmarking"));
                    data.setIndate(sdf.format(rs.getDate("indate")));
                    data.setHeatbatchno(rs.getString("heatbatchno"));
                    data.setWarrantyno(rs.getString("warrantyno"));
                    data.setSpec(rs.getString("spec"));
                    data.setQty(rs.getString("qty"));
                    data.setUnit(rs.getString("unit"));
                    data.setDimension(rs.getString("dimension"));
                    warrantysitu_id=rs.getInt("warrantystatus_id_certsitu");
                    supplier_id=rs.getInt("supplier_id_supplier");
                    millunit_id=rs.getInt("millunit_id_millunit");
                    matlname_id=rs.getInt("matlname_id_matlname");
                    matlstand_id=rs.getInt("contraststand_id_matlstand");
                    designation_id=rs.getInt("contraststand_id_designation");
                    data.setHeatcondi(rs.getString("heattreatcondition_id_heatcondi"));


                    ps1=conn.prepareStatement("SELECT * FROM warrantystatus WHERE id=?");
                    ps1.setInt(1,warrantysitu_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setWarrantysitu(rs1.getString("certsitu"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM supplier WHERE id=?");
                    ps1.setInt(1,supplier_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setSupplier(rs1.getString("supplier"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM millunit WHERE id=?");
                    ps1.setInt(1,millunit_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMillunit(rs1.getString("millunit"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM matlname WHERE id=?");
                    ps1.setInt(1,matlname_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMatlname(rs1.getString("matlname"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                    ps1.setInt(1,matlstand_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMatlstand(rs1.getString("matlstand"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                    ps1.setInt(1,designation_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setDesignation(rs1.getString("designation"));
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

            result.setTotal(as.size());
            Collections.reverse(as);                                          //将list倒序
            int as_size;
            as_size=as.size();
            if(as_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("fail");
            }else if((as_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as_q=new ArrayList<searchmonthmatldata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),as_size));
                result.setResult("success");
                result.setData(as_q);
            }else{
                as_q=new ArrayList<searchmonthmatldata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData(as_q);
            }
        }catch (Exception e){
            result.setResult("fail");
        }



        conn.close();
        return result;
    }



    @RequestMapping(value = "searchmonthmatlexcel")                                 //月材料查询生成excel表格
    public @ResponseBody ResponseEntity<byte[]> searchmonthmatlexcel(@RequestBody searchmonthmatlexcelpost sp) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;

        int inmonth =Integer.parseInt(sp.getInmonth());
        if(inmonth<10){
            sp.setInmonth("0"+sp.getInmonth());
        }

        int warrantysitu_id=0;
        int supplier_id=0;
        int millunit_id=0;
        int matlname_id=0;
        int matlstand_id=0;
        int designation_id=0;
        int heatcondi_id=0;
        int index = 0;

        String year_month = sp.getInyear()+"-"+sp.getInmonth()+"%";

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        searchmonthmatldata data = null;
        ArrayList<searchmonthmatldata> as = new ArrayList<searchmonthmatldata>();

        try {
            ps = conn.prepareStatement("SELECT * FROM matlcoderules");
            rs = ps.executeQuery();
            if(rs.next()){
                index = rs.getInt("indexx");
            }
            rs.close();
            ps.close();

            ps=conn.prepareStatement("SELECT * FROM putmaterial WHERE indate LIKE ?");
            ps.setString(1,year_month);
            rs=ps.executeQuery();
            while (rs.next()){
                data = new searchmonthmatldata();
                if(sp.getMatlcode().charAt(0)==(rs.getString("codedmarking").charAt(index-1))){
                    data.setCodedmarking(rs.getString("codedmarking"));
                    data.setIndate(sdf.format(rs.getDate("indate")));
                    data.setHeatbatchno(rs.getString("heatbatchno"));
                    data.setWarrantyno(rs.getString("warrantyno"));
                    data.setSpec(rs.getString("spec"));
                    data.setQty(rs.getString("qty"));
                    data.setUnit(rs.getString("unit"));
                    data.setDimension(rs.getString("dimension"));
                    warrantysitu_id=rs.getInt("warrantystatus_id_certsitu");
                    supplier_id=rs.getInt("supplier_id_supplier");
                    millunit_id=rs.getInt("millunit_id_millunit");
                    matlname_id=rs.getInt("matlname_id_matlname");
                    matlstand_id=rs.getInt("contraststand_id_matlstand");
                    designation_id=rs.getInt("contraststand_id_designation");
                    data.setHeatcondi(rs.getString("heattreatcondition_id_heatcondi"));


                    ps1=conn.prepareStatement("SELECT * FROM warrantystatus WHERE id=?");
                    ps1.setInt(1,warrantysitu_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setWarrantysitu(rs1.getString("certsitu"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM supplier WHERE id=?");
                    ps1.setInt(1,supplier_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setSupplier(rs1.getString("supplier"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM millunit WHERE id=?");
                    ps1.setInt(1,millunit_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMillunit(rs1.getString("millunit"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM matlname WHERE id=?");
                    ps1.setInt(1,matlname_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMatlname(rs1.getString("matlname"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                    ps1.setInt(1,matlstand_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setMatlstand(rs1.getString("matlstand"));
                    }
                    rs1.close();
                    ps1.close();

                    ps1=conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                    ps1.setInt(1,designation_id);
                    rs1=ps1.executeQuery();
                    while (rs1.next()){
                        data.setDesignation(rs1.getString("designation"));
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

            Collections.reverse(as);                                          //将list倒序
        }catch (Exception e){
            System.out.println("error");
        }
        conn.close();

        Workbook wb = new HSSFWorkbook();
        Sheet sh=wb.createSheet();
        Row row0 = sh.createRow(0);

        Cell cell00 =row0.createCell(0);
        CellReference cr00 = new CellReference(cell00);
        cell00.setCellValue("入库编号");

        Cell cell01 =row0.createCell(1);
        CellReference cr01 = new CellReference(cell01);
        cell01.setCellValue("质保书情况");

        Cell cell02 =row0.createCell(2);
        CellReference cr02 = new CellReference(cell02);
        cell02.setCellValue("供货单位");

        Cell cell03 =row0.createCell(3);
        CellReference cr03 = new CellReference(cell03);
        cell03.setCellValue("生产单位");

        Cell cell04 =row0.createCell(4);
        CellReference cr04 = new CellReference(cell04);
        cell04.setCellValue("入库日期");

        Cell cell05 =row0.createCell(5);
        CellReference cr05 = new CellReference(cell05);
        cell05.setCellValue("炉批号");

        Cell cell06 =row0.createCell(6);
        CellReference cr06 = new CellReference(cell06);
        cell06.setCellValue("材料名称");

        Cell cell07 =row0.createCell(7);
        CellReference cr07 = new CellReference(cell07);
        cell07.setCellValue("质保书号");

        Cell cell08 =row0.createCell(8);
        CellReference cr08 = new CellReference(cell08);
        cell08.setCellValue("材料标准号");

        Cell cell09 =row0.createCell(9);
        CellReference cr09 = new CellReference(cell09);
        cell09.setCellValue("牌号");

        Cell cell010 =row0.createCell(10);
        CellReference cr010 = new CellReference(cell010);
        cell010.setCellValue("规格");

        Cell cell011 =row0.createCell(11);
        CellReference cr011 = new CellReference(cell011);
        cell011.setCellValue("数量");

        Cell cell012 =row0.createCell(12);
        CellReference cr012 = new CellReference(cell012);
        cell012.setCellValue("单位");

        Cell cell013 =row0.createCell(13);
        CellReference cr013 = new CellReference(cell013);
        cell013.setCellValue("尺寸");

        Cell cell014 =row0.createCell(14);
        CellReference cr014 = new CellReference(cell014);
        cell014.setCellValue("热处理状态");
        for (int i=0;i<as.size();i++){
            searchmonthmatldata edata = as.get(i);
            Row row = sh.createRow(i+1);

            Cell cell0 =row.createCell(0);
            CellReference cr0 = new CellReference(cell0);
            cell0.setCellValue(edata.getCodedmarking());

            Cell cell1 =row.createCell(1);
            CellReference cr1 = new CellReference(cell1);
            cell1.setCellValue(edata.getWarrantysitu());

            Cell cell2 =row.createCell(2);
            CellReference cr2 = new CellReference(cell2);
            cell2.setCellValue(edata.getSupplier());

            Cell cell3 =row.createCell(3);
            CellReference cr3 = new CellReference(cell3);
            cell3.setCellValue(edata.getMillunit());

            Cell cell4 =row.createCell(4);
            CellReference cr4 = new CellReference(cell4);
            cell4.setCellValue(edata.getIndate());

            Cell cell5 =row.createCell(5);
            CellReference cr5 = new CellReference(cell5);
            cell5.setCellValue(edata.getHeatbatchno());

            Cell cell6 =row.createCell(6);
            CellReference cr6 = new CellReference(cell6);
            cell6.setCellValue(edata.getMatlname());

            Cell cell7 =row.createCell(7);
            CellReference cr7 = new CellReference(cell7);
            cell7.setCellValue(edata.getWarrantyno());

            Cell cell8 =row.createCell(8);
            CellReference cr8 = new CellReference(cell8);
            cell8.setCellValue(edata.getMatlstand());

            Cell cell9 =row.createCell(9);
            CellReference cr9 = new CellReference(cell9);
            cell9.setCellValue(edata.getDesignation());

            Cell cell10 =row.createCell(10);
            CellReference cr10 = new CellReference(cell10);
            cell10.setCellValue(edata.getSpec());

            Cell cell11 =row.createCell(11);
            CellReference cr11 = new CellReference(cell11);
            cell11.setCellValue(edata.getQty());

            Cell cell12 =row.createCell(12);
            CellReference cr12 = new CellReference(cell12);
            cell12.setCellValue(edata.getUnit());

            Cell cell13 =row.createCell(13);
            CellReference cr13 = new CellReference(cell13);
            cell13.setCellValue(edata.getDimension());

            Cell cell14 =row.createCell(14);
            CellReference cr14 = new CellReference(cell14);
            cell14.setCellValue(edata.getHeatcondi());
        }



        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("月材料查询.xls".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(out.toByteArray(),headers, HttpStatus.CREATED);
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return download;

    }
}
