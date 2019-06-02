package start.searchnocertsitu;

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
public class searchnocertsitu {
    @RequestMapping(value = "searchnocertsituresult")                                       //质保书未到查询
    public @ResponseBody searchnocertsituresult searchnocertsituresult(@RequestBody searchnocertsitupost sp) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        ArrayList<searchnocertsitudata> as_q=null;

        ArrayList<searchnocertsitudata> as =new ArrayList<searchnocertsitudata>();
        searchnocertsitudata sncd = null;
        searchnocertsituresult result = new searchnocertsituresult();


        String sql=null;
        String sql_x=null;
        int z=0;

        int num = 1;

        try{

            sql="SELECT * FROM putmaterialcache WHERE warrantystatus_id_certsitu='质保书未到' ";
            sql_x = "ORDER BY codedmarking DESC";
            if(!(sp.getDesignation()==null || sp.getDesignation().equals(""))){
                sql=sql+"AND contraststand_id_designation=? ";
            }
            if(!(sp.getCodedmarking()==null || sp.getCodedmarking().equals(""))){
                sql=sql+"AND codedmarking LIKE ? ";
            }
            if(!(sp.getSupplier()==null || sp.getSupplier().equals(""))){
                sql=sql+"AND supplier_id_supplier LIKE ? ";
            }
            if(!(sp.getHeatbatchno()==null || sp.getHeatbatchno().equals(""))){
                sql=sql+"AND heatbatchno LIKE ? ";
            }
            if(!(sp.getDesignation()==null || sp.getDesignation().equals(""))){
                sql=sql+"AND warrantyno LIKE ?  ";
            }
            ps=conn.prepareStatement(sql+sql_x);
            if(!(sp.getDesignation()==null || sp.getDesignation().equals(""))){
                ps.setString(num,sp.getDesignation());
                num++;
            }
            if(!(sp.getCodedmarking()==null || sp.getCodedmarking().equals(""))){
                ps.setString(num,"%"+sp.getCodedmarking()+"%");
                num++;
            }
            if(!(sp.getSupplier()==null || sp.getSupplier().equals(""))){
                ps.setString(num,"%"+sp.getSupplier()+"%");
                num++;
            }
            if(!(sp.getHeatbatchno()==null || sp.getHeatbatchno().equals(""))){
                ps.setString(num,"%"+sp.getHeatbatchno()+"%");
                num++;
            }
            if(!(sp.getWarrantyno()==null || sp.getWarrantyno().equals(""))){
                ps.setString(num,"%"+sp.getWarrantyno()+"%");
                num++;
            }
            rs=ps.executeQuery();
            while (rs.next()){
                ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1 = ps1.executeQuery();
                if(!rs1.next()){
                    rs1.close();
                    ps1.close();

                    sncd = new searchnocertsitudata();
                    if(!(sp.getMatlcode()==null || sp.getMatlcode().equals(""))){
                        ps1 = conn.prepareStatement("SELECT * FROM matlcoderules");
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            if(sp.getMatlcode().charAt(0)==(rs.getString("codedmarking").charAt(rs1.getInt("indexx")-1))){
                                rs1.close();
                                ps1.close();

                                sncd.setCodedmarking(rs.getString("codedmarking"));
                                sncd.setSpec(rs.getString("spec"));
                                sncd.setQty(rs.getString("qty"));
                                sncd.setNote(rs.getString("note"));
                                sncd.setIndate(rs.getString("indate"));
                                sncd.setHeatbatchno(rs.getString("heatbatchno"));
                                sncd.setWarrantyno(rs.getString("warrantyno"));
                                sncd.setMatlname(rs.getString("matlname_id_matlname"));
                                sncd.setDesignation(rs.getString("contraststand_id_designation"));
                                sncd.setMatlstand(rs.getString("contraststand_id_matlstand"));
                                sncd.setSupplier(rs.getString("supplier_id_supplier"));
                                sncd.setMillunit(rs.getString("millunit_id_millunit"));

                            }else {
                                rs1.close();
                                ps1.close();
                                continue;
                            }
                        }else {
                            rs1.close();
                            ps1.close();
                        }
                    }else{
                        sncd.setCodedmarking(rs.getString("codedmarking"));
                        sncd.setSpec(rs.getString("spec"));
                        sncd.setQty(rs.getString("qty"));
                        sncd.setNote(rs.getString("note"));
                        sncd.setIndate(rs.getString("indate"));
                        sncd.setHeatbatchno(rs.getString("heatbatchno"));
                        sncd.setWarrantyno(rs.getString("warrantyno"));
                        sncd.setMatlname(rs.getString("matlname_id_matlname"));
                        sncd.setDesignation(rs.getString("contraststand_id_designation"));
                        sncd.setMatlstand(rs.getString("contraststand_id_matlstand"));
                        sncd.setSupplier(rs.getString("supplier_id_supplier"));
                        sncd.setMillunit(rs.getString("millunit_id_millunit"));

                    }
                    as.add(sncd);
                }else {
                    rs1.close();
                    ps1.close();
                }

            }

            result.setTotal(as.size());
            int as_size;
            as_size=as.size();
            if(as_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("fail");
            }else if((as_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as_q=new ArrayList<searchnocertsitudata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),as_size));
                result.setResult("success");
                result.setData(as_q);
            }else{
                as_q=new ArrayList<searchnocertsitudata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData(as_q);
            }
        }catch (Exception e){
            result.setResult("fail");
        }
        conn.close();
        return result;


    }

    @RequestMapping(value = "searchnocertsituexcel")                                        //质保书未到查询生成excel表格
    public @ResponseBody ResponseEntity<byte[]> searchnocertsituexcel(@RequestBody searchnocertsituexcelpost sp) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        ArrayList<searchnocertsitudata> as =new ArrayList<searchnocertsitudata>();
        searchnocertsitudata sncd = null;

        String sql=null;
        String sql_x=null;
        int z=0;

        try {

            sql="SELECT * FROM putmaterialcache WHERE warrantystatus_id_certsitu='质保书未到' ";
            sql_x = "ORDER BY codedmarking DESC";
            if(!(sp.getDesignation()==null || sp.getDesignation().equals(""))){
                sql=sql+"AND contraststand_id_designation=? ";
            }
            ps=conn.prepareStatement(sql+sql_x);
            if(!(sp.getDesignation()==null || sp.getDesignation().equals(""))){
                ps.setString(1,sp.getDesignation());
            }
            rs=ps.executeQuery();
            while (rs.next()){

                ps1 = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
                ps1.setString(1,rs.getString("codedmarking"));
                rs1 = ps1.executeQuery();
                if(!rs1.next()){
                    rs1.close();
                    ps1.close();

                    sncd = new searchnocertsitudata();
                    if(!(sp.getMatlcode()==null || sp.getMatlcode().equals(""))){
                        ps1 = conn.prepareStatement("SELECT * FROM matlcoderules");
                        rs1 = ps1.executeQuery();
                        if(rs1.next()){
                            if(sp.getMatlcode().charAt(0)==(rs.getString("codedmarking").charAt(rs1.getInt("indexx")-1))){
                                rs1.close();
                                ps1.close();

                                sncd.setCodedmarking(rs.getString("codedmarking"));
                                sncd.setSpec(rs.getString("spec"));
                                sncd.setQty(rs.getString("qty"));
                                sncd.setNote(rs.getString("note"));
                                sncd.setIndate(rs.getString("indate"));
                                sncd.setHeatbatchno(rs.getString("heatbatchno"));
                                sncd.setWarrantyno(rs.getString("warrantyno"));
                                sncd.setMatlname(rs.getString("matlname_id_matlname"));
                                sncd.setDesignation(rs.getString("contraststand_id_designation"));
                                sncd.setMatlstand(rs.getString("contraststand_id_matlstand"));
                                sncd.setSupplier(rs.getString("supplier_id_supplier"));
                                sncd.setMillunit(rs.getString("millunit_id_millunit"));

                            }else {
                                rs1.close();
                                ps1.close();
                                continue;
                            }
                        }else {
                            rs1.close();
                            ps1.close();
                        }
                    }else{
                        sncd.setCodedmarking(rs.getString("codedmarking"));
                        sncd.setSpec(rs.getString("spec"));
                        sncd.setQty(rs.getString("qty"));
                        sncd.setNote(rs.getString("note"));
                        sncd.setIndate(rs.getString("indate"));
                        sncd.setHeatbatchno(rs.getString("heatbatchno"));
                        sncd.setWarrantyno(rs.getString("warrantyno"));
                        sncd.setMatlname(rs.getString("matlname_id_matlname"));
                        sncd.setDesignation(rs.getString("contraststand_id_designation"));
                        sncd.setMatlstand(rs.getString("contraststand_id_matlstand"));
                        sncd.setSupplier(rs.getString("supplier_id_supplier"));
                        sncd.setMillunit(rs.getString("millunit_id_millunit"));

                    }
                    as.add(sncd);
                }else {
                    rs1.close();
                    ps1.close();
                }

            }
            conn.close();


        }catch (Exception e){
            System.out.println("error");
        }

        Workbook wb = new HSSFWorkbook();
        Sheet sh=wb.createSheet();;
        Row row0 = sh.createRow(0);

        Cell cell00 =row0.createCell(0);
        CellReference cr00 = new CellReference(cell00);
        cell00.setCellValue("入库编号");

        Cell cell01 =row0.createCell(1);
        CellReference cr01 = new CellReference(cell01);
        cell01.setCellValue("供货单位");

        Cell cell02 =row0.createCell(2);
        CellReference cr02 = new CellReference(cell02);
        cell02.setCellValue("质保书号");

        Cell cell03 =row0.createCell(3);
        CellReference cr03 = new CellReference(cell03);
        cell03.setCellValue("牌号");

        Cell cell04 =row0.createCell(4);
        CellReference cr04 = new CellReference(cell04);
        cell04.setCellValue("规格");

        Cell cell05 =row0.createCell(5);
        CellReference cr05 = new CellReference(cell05);
        cell05.setCellValue("数量");

        Cell cell06 =row0.createCell(6);
        CellReference cr06 = new CellReference(cell06);
        cell06.setCellValue("材料标准号");

        Cell cell07 =row0.createCell(7);
        CellReference cr07 = new CellReference(cell07);
        cell07.setCellValue("生产单位");

        Cell cell08 =row0.createCell(8);
        CellReference cr08 = new CellReference(cell08);
        cell08.setCellValue("入库日期");

        Cell cell09 =row0.createCell(9);
        CellReference cr09 = new CellReference(cell09);
        cell09.setCellValue("材料名称");

        Cell cell010 =row0.createCell(10);
        CellReference cr010 = new CellReference(cell010);
        cell010.setCellValue("炉批号");

        Cell cell011 =row0.createCell(11);
        CellReference cr011 = new CellReference(cell011);
        cell011.setCellValue("备注");
        for (int i=0;i<as.size();i++){
            searchnocertsitudata edata = as.get(i);
            Row row = sh.createRow(i+1);

            Cell cell0 =row.createCell(0);
            CellReference cr0 = new CellReference(cell0);
            cell0.setCellValue(edata.getCodedmarking());

            Cell cell1 =row.createCell(1);
            CellReference cr1 = new CellReference(cell1);
            cell1.setCellValue(edata.getSupplier());

            Cell cell2 =row.createCell(2);
            CellReference cr2 = new CellReference(cell2);
            cell2.setCellValue(edata.getWarrantyno());

            Cell cell3 =row.createCell(3);
            CellReference cr3 = new CellReference(cell3);
            cell3.setCellValue(edata.getDesignation());

            Cell cell4 =row.createCell(4);
            CellReference cr4 = new CellReference(cell4);
            cell4.setCellValue(edata.getSpec());

            Cell cell5 =row.createCell(5);
            CellReference cr5 = new CellReference(cell5);
            cell5.setCellValue(edata.getQty());

            Cell cell6 =row.createCell(6);
            CellReference cr6 = new CellReference(cell6);
            cell6.setCellValue(edata.getMatlstand());

            Cell cell7 =row.createCell(7);
            CellReference cr7 = new CellReference(cell7);
            cell7.setCellValue(edata.getMillunit());

            Cell cell8 =row.createCell(8);
            CellReference cr8 = new CellReference(cell8);
            cell8.setCellValue(edata.getIndate());

            Cell cell9 =row.createCell(9);
            CellReference cr9 = new CellReference(cell9);
            cell9.setCellValue(edata.getMatlname());

            Cell cell10 =row.createCell(10);
            CellReference cr10 = new CellReference(cell10);
            cell10.setCellValue(edata.getHeatbatchno());

            Cell cell11 =row.createCell(11);
            CellReference cr11 = new CellReference(cell11);
            cell11.setCellValue(edata.getNote());
        }



        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("质保书未到.xls".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
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


