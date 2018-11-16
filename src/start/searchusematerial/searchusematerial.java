package start.searchusematerial;

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
import java.util.ArrayList;
import java.util.Collections;

@CrossOrigin
@Controller
public class searchusematerial {
    @RequestMapping(value = "searchusematerialresult")                                                      //材料使用查询(返回result)
    public @ResponseBody searchusematerialresult searchusematerialresult(@RequestBody searchusematerialpost sp) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        int designation_id=0;
        int spartname_id=0;
        int dwgno_id=0;
        int prodname_id=0;

        searchusematerialdata data =null;
        searchusematerialresult result = new searchusematerialresult();
        ArrayList<searchusematerialdata> as = new ArrayList<searchusematerialdata>();
        ArrayList<searchusematerialdata> as_q =null;


        try{
            ps=conn.prepareStatement("SELECT * FROM pressureparts WHERE codedmarking=?");
            ps.setString(1,sp.getCodedmarking());
            rs=ps.executeQuery();
            while(rs.next()){
                data = new searchusematerialdata();
                data.setCodedmarking(rs.getString("codedmarking"));
                data.setSpec(rs.getString("spec"));
                data.setQty(rs.getString("qty"));
                data.setProdno(rs.getString("prodno"));
                designation_id = rs.getInt("contraststand_id_designation");
                spartname_id = rs.getInt("parts_id_name");

                ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
                ps1.setInt(1,designation_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data.setDesignation(rs1.getString("designation"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM parts WHERE id=?");
                ps1.setInt(1,spartname_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data.setSpartname(rs1.getString("partsname"));
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno=?");                      //开始查询产品名称,从产品制造参数表找
                ps1.setString(1,data.getProdno());
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    dwgno_id = rs1.getInt("proparlist_id_dwgno");                                      //查询出图号的id
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM proparlist WHERE id=?");                             //根据图号在产品参数表查询产品名称的id
                ps1.setInt(1,dwgno_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    prodname_id=rs1.getInt("productname_id_prodname");                                  //查询出产品名称的id
                }
                rs1.close();
                ps1.close();

                ps1 = conn.prepareStatement("SELECT * FROM productname WHERE id=?");                                  //根据产品名称的id在产品名称表查询产品名称
                ps1.setInt(1,prodname_id);
                rs1 = ps1.executeQuery();
                while (rs1.next()){
                    data.setProdname(rs1.getString("prodname"));                                            //查询出产品名称输入data；
                }
                rs1.close();
                ps1.close();

                as.add(data);
            }
            rs.close();
            ps.close();
            conn.close();

            Collections.reverse(as);                                          //将list倒序
            int as_size;
            as_size=as.size();
            if(as_size<=((sp.getPageindex()-1)*sp.getPagesize())){
                result.setResult("fail");
            }else if((as_size-((sp.getPageindex()-1)*sp.getPagesize())<sp.getPagesize())){
                as_q = new ArrayList<searchusematerialdata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),as_size));
                result.setResult("success");
                result.setData(as_q);
                result.setTotal(as_q.size());
            }else {
                as_q=new ArrayList<searchusematerialdata>(as.subList(((sp.getPageindex()-1)*sp.getPagesize()),(sp.getPageindex()*sp.getPagesize())));
                result.setResult("success");
                result.setData(as_q);
                result.setTotal(as_q.size());
            }


        }catch (Exception e){
            result.setResult("fail");
        }
        return result;
    }



    @RequestMapping(value = "searchusematerialexcel")                                           //材料使用查询(返回excel表格)
    public @ResponseBody ResponseEntity<byte[]> searchusematerialexcel(@RequestBody searchusematerialexcelpost sp) throws ClassNotFoundException, SQLException, UnsupportedEncodingException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;

        int designation_id=0;
        int spartname_id=0;
        int dwgno_id=0;
        int prodname_id=0;

        searchusematerialdata data =null;
        ArrayList<searchusematerialdata> as = new ArrayList<searchusematerialdata>();


        ps=conn.prepareStatement("SELECT * FROM pressureparts WHERE codedmarking=?");
        ps.setString(1,sp.getCodedmarking());
        rs=ps.executeQuery();
        while(rs.next()){
            data = new searchusematerialdata();
            data.setCodedmarking(rs.getString("codedmarking"));
            data.setSpec(rs.getString("spec"));
            data.setQty(rs.getString("qty"));
            data.setProdno(rs.getString("prodno"));
            designation_id = rs.getInt("contraststand_id_designation");
            spartname_id = rs.getInt("parts_id_name");

            ps1 = conn.prepareStatement("SELECT * FROM contraststand WHERE id=?");
            ps1.setInt(1,designation_id);
            rs1 = ps1.executeQuery();
            while (rs1.next()){
                data.setDesignation(rs1.getString("designation"));
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM parts WHERE id=?");
            ps1.setInt(1,spartname_id);
            rs1 = ps1.executeQuery();
            while (rs1.next()){
                data.setSpartname(rs1.getString("partsname"));
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno=?");                      //开始查询产品名称,从产品制造参数表找
            ps1.setString(1,data.getProdno());
            rs1 = ps1.executeQuery();
            while (rs1.next()){
                dwgno_id = rs1.getInt("proparlist_id_dwgno");                                      //查询出图号的id
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM proparlist WHERE id=?");                             //根据图号在产品参数表查询产品名称的id
            ps1.setInt(1,dwgno_id);
            rs1 = ps1.executeQuery();
            while (rs1.next()){
                prodname_id=rs1.getInt("productname_id_prodname");                                  //查询出产品名称的id
            }
            rs1.close();
            ps1.close();

            ps1 = conn.prepareStatement("SELECT * FROM productname WHERE id=?");                                  //根据产品名称的id在产品名称表查询产品名称
            ps1.setInt(1,prodname_id);
            rs1 = ps1.executeQuery();
            while (rs1.next()){
                data.setProdname(rs1.getString("prodname"));                                            //查询出产品名称输入data；
            }
            rs1.close();
            ps1.close();

            as.add(data);
        }
        rs.close();
        ps.close();
        conn.close();
        Collections.reverse(as);                                          //将list倒序

        Workbook wb = new HSSFWorkbook();
        Sheet sh=wb.createSheet();;
        Row row0 = sh.createRow(0);

        Cell cell00 =row0.createCell(0);
        CellReference cr00 = new CellReference(cell00);
        cell00.setCellValue("入库编号");

        Cell cell01 =row0.createCell(1);
        CellReference cr01 = new CellReference(cell01);
        cell01.setCellValue("牌号");

        Cell cell02 =row0.createCell(2);
        CellReference cr02 = new CellReference(cell02);
        cell02.setCellValue("规格");

        Cell cell03 =row0.createCell(3);
        CellReference cr03 = new CellReference(cell03);
        cell03.setCellValue("数量");

        Cell cell04 =row0.createCell(4);
        CellReference cr04 = new CellReference(cell04);
        cell04.setCellValue("产品编号");

        Cell cell05 =row0.createCell(5);
        CellReference cr05 = new CellReference(cell05);
        cell05.setCellValue("产品名称");

        Cell cell06 =row0.createCell(6);
        CellReference cr06 = new CellReference(cell06);
        cell06.setCellValue("零件名称");

        for (int i=0;i<as.size();i++) {
            searchusematerialdata edata = as.get(i);
            Row row = sh.createRow(i + 1);

            Cell cell0 = row.createCell(0);
            CellReference cr0 = new CellReference(cell0);
            cell0.setCellValue(edata.getCodedmarking());

            Cell cell1 = row.createCell(1);
            CellReference cr1 = new CellReference(cell1);
            cell1.setCellValue(edata.getDesignation());

            Cell cell2 = row.createCell(2);
            CellReference cr2 = new CellReference(cell2);
            cell2.setCellValue(edata.getSpec());

            Cell cell3 = row.createCell(3);
            CellReference cr3 = new CellReference(cell3);
            cell3.setCellValue(edata.getQty());

            Cell cell4 = row.createCell(4);
            CellReference cr4 = new CellReference(cell4);
            cell4.setCellValue(edata.getProdno());

            Cell cell5 = row.createCell(5);
            CellReference cr5 = new CellReference(cell5);
            cell5.setCellValue(edata.getProdname());

            Cell cell6 = row.createCell(6);
            CellReference cr6 = new CellReference(cell6);
            cell6.setCellValue(edata.getSpartname());
        }


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        String fileName = new String("材料使用情况.xls".getBytes("UTF-8"), "iso-8859-1");//为了解决中文名称乱码问题
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
