package start.getreport;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static start.excel.excel.*;

@Controller
@CrossOrigin
public class getpromatlreport {                                 //产品材料清单报表
    @RequestMapping(value = "getpromatlreport")
    public @ResponseBody ResponseEntity<byte[]> getpromatlreport(String prodno, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps_x = null;
        ResultSet rs_x=null;

        int partsname_id = 0;
        int designation_id = 0;
        int millunit_id = 0;
        int utclass_id = 0;
        int f = 2;
        int i = 0;
        int t = 0;


        ArrayList<String> material = null;
        ArrayList<String> codedmarking_f = null;

        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy年MM月dd日");

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"产品材料清单.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "产品材料清单_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String pdfname = getUploadFileName("产品材料清单.pdf");
        String url1 = uploadPath +"/"+ filename;
        String url2 = uploadPath +"/"+ pdfname;




        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        workBook.setPrintArea(
                0,0,30,0,20
        );
        fileXlsx.close();
        Sheet sheet_new=workBook.getSheetAt(0);
        Sheet sheet = null;

        putsheet(sheet_new,1,26,prodno);

        ps = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet_new,1,2,rs.getString("dwgno"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            calendar.setTime(rs.getDate("exworkdate"));
            putsheet(sheet_new,28,30,simpleDateFormat1.format(calendar.getTime()));
        }
        rs.close();

        ps1 = conn.prepareStatement("SELECT * FROM pressureparts WHERE prodno = ? AND status = 1");
        ps1.setString(1,prodno);
        rs1 = ps1.executeQuery();
        while (rs1.next()){
            if(i==0){
                material = new ArrayList<String>();
                codedmarking_f = new ArrayList<String>();
                sheet = workBook.getSheetAt(0);
            }
            if(i>=9){
                i=0;
                material = new ArrayList<String>();
                codedmarking_f = new ArrayList<String>();
                sheet=workBook.createSheet("产品材料清单"+f++);
                copySheet(workBook,(XSSFSheet) sheet_new,(XSSFSheet)sheet);
            }
            partsname_id = rs1.getInt("parts_id_name");
            ps = conn.prepareStatement("SELECT * FROM parts WHERE id = ?");
            ps.setInt(1,partsname_id);
            rs = ps.executeQuery();
            if(rs.next()){
                putsheet(sheet,8+i*2,1,rs.getString("partsname"));
            }
            rs.close();
            ps.close();

            putsheet(sheet,8+i*2,2,rs1.getString("partno"));

            designation_id = rs1.getInt("contraststand_id_designation");
            ps = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
            ps.setInt(1,designation_id);
            rs = ps.executeQuery();
            if(rs.next()){
                putsheet(sheet,8+i*2,3,rs.getString("designation"));
            }
            rs.close();
            ps.close();

            putsheet(sheet,9+i*2,3,rs1.getString("spec"));
            putsheet(sheet,8+i*2,8,rs1.getString("codedmarking"));
            codedmarking_f.add(rs1.getString("codedmarking"));

            ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
            ps.setString(1,rs1.getString("codedmarking"));
            rs = ps.executeQuery();
            while (rs.next()){
                setma(material,"c",rs);
                setma(material,"mn",rs);
                setma(material,"si",rs);
                setma(material,"p",rs);
                setma(material,"s",rs);
                setma(material,"cr",rs);
                setma(material,"ni",rs);
                setma(material,"ti",rs);
                setma(material,"cu",rs);
                setma(material,"fe",rs);
                setma(material,"n",rs);
                setma(material,"alt",rs);
                setma(material,"mo",rs);
                setma(material,"mg",rs);
                setma(material,"zn",rs);
                setma(material,"nb",rs);
                setma(material,"v",rs);
                setma(material,"b",rs);
                setma(material,"w",rs);
                setma(material,"sb",rs);
                setma(material,"al",rs);
                setma(material,"zr",rs);
                setma(material,"ca",rs);
                setma(material,"be",rs);
                setma(material,"als",rs);

                putsheet(sheet,8+i*2,4,rs.getString("heatbatchno"));

                millunit_id = rs.getInt("millunit_id_millunit");
                ps_x = conn.prepareStatement("SELECT * FROM millunit WHERE id = ?");
                ps_x.setInt(1,millunit_id);
                rs_x = ps_x.executeQuery();
                if(rs_x.next()){
                    putsheet(sheet,8+i*2,5,rs_x.getString("millunit"));
                }
                rs_x.close();
                ps_x.close();

                putsheet(sheet,8+i*2,6,rs.getString("heattreatcondition_id_heatcondi"));
                putsheet(sheet,8+i*2,22,rs.getString("rel1"));
                putsheet(sheet,8+i*2,24,rs.getString("rm1"));
                putsheet(sheet,8+i*2,26,rs.getString("elong1"));
                putsheet(sheet,8+i*2,28,rs.getString("hardness1"));
                putsheet(sheet,8+i*2,29,rs.getString("bending_id_impacttemp"));
                putsheet(sheet,8+i*2,30,rs.getString("impactp1"));
                putsheet(sheet,8+i*2,33,rs.getString("bending_id_bendangle"));
                putsheet(sheet,8+i*2,35,rs.getString("bendaxdia"));

                utclass_id = rs.getInt("bending_id_utclass");
                ps_x = conn.prepareStatement("SELECT * FROM bending WHERE id = ?");
                ps_x.setInt(1,utclass_id);
                rs_x = ps_x.executeQuery();
                if(rs_x.next()){
                    putsheet(sheet,8+i*2,36,rs_x.getString("utclass"));
                }
                rs_x.close();
                ps_x.close();
            }
            rs.close();
            ps.close();

            ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
            ps.setString(1,rs1.getString("codedmarking"));
            rs = ps.executeQuery();
            while (rs.next()){

                putsheet(sheet,9+i*2,22,rs.getString("rel1"));
                putsheet(sheet,9+i*2,24,rs.getString("rm1"));
                putsheet(sheet,9+i*2,26,rs.getString("elong1"));
                putsheet(sheet,9+i*2,28,rs.getString("hardness1"));
                putsheet(sheet,9+i*2,29,rs.getString("impacttemp"));
                putsheet(sheet,9+i*2,30,rs.getString("impactp1"));
                putsheet(sheet,9+i*2,33,rs.getString("bendangle"));
                putsheet(sheet,9+i*2,35,rs.getString("bendaxdia"));

            }
            rs.close();
            ps.close();


            if(i == 8 || rs1.isLast()){
                for (int z = 0;z<material.size();z++){
                    if(z>=12){
                        break;
                    }
                    putsheet(sheet,4,10+z,material.get(z));
                }


                for (int z=0;z<codedmarking_f.size();z++){
                    ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
                    ps.setString(1,codedmarking_f.get(z));
                    System.out.println(codedmarking_f.get(z));
                    rs = ps.executeQuery();
                    while (rs.next()){
                        for (int c = 0;c<material.size();c++){
                            if(c>=12){
                                break;
                            }
                            putsheet(sheet,8+z*2,10+c,rs.getString(material.get(c)));
                            System.out.println(rs.getString(material.get(c)));
                        }
                    }
                    rs.close();
                    ps.close();

                    ps = conn.prepareStatement("SELECT * FROM rematerial WHERE codedmarking = ? AND status = 1");
                    ps.setString(1,codedmarking_f.get(z));
                    rs = ps.executeQuery();
                    while (rs.next()){
                        for (int c = 0;c<material.size();c++){
                            if(c>=12){
                                break;
                            }
                            putsheet(sheet,9+z*2,10+c,rs.getString(material.get(c)));
                        }
                    }
                    rs.close();
                    ps.close();
                }
            }


            i++;
        }
        rs1.close();
        ps1.close();












        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();




        excel2Pdf_heng(url1,url2);                                       //转PDF
        File filepdf = new File(uploadPath, pdfname);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "产品材料清单.pdf");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }


    public void setma(ArrayList<String> as , String a , ResultSet rs) throws SQLException {
        int f = 0;
        for (int i = 0;i<as.size();i++){
            if(as.get(i).equals(a)){
                f=1;
                break;
            }
        }
        if(f==0 && !(rs.getString(a)==null || rs.getString(a).equals(""))){
            as.add(a);
        }

    }

    public static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {
        toStyle.cloneStyleFrom(fromStyle);//此一行代码搞定
    }
    public static void mergeSheetAllRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {//合并单元格
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    }

    public static void copyCell(XSSFWorkbook wb, XSSFCell fromCell, XSSFCell toCell) {
        XSSFCellStyle newstyle=wb.createCellStyle();
        copyCellStyle(fromCell.getCellStyle(), newstyle);
        //toCell.setEncoding(fromCell.getEncoding());
        //样式
        toCell.setCellStyle(newstyle);
        if (fromCell.getCellComment() != null) {
            toCell.setCellComment(fromCell.getCellComment());
        }
        // 不同数据类型处理
        int fromCellType = fromCell.getCellType();
        toCell.setCellType(fromCellType);
        if (fromCellType == XSSFCell.CELL_TYPE_NUMERIC) {

        } else if (fromCellType == XSSFCell.CELL_TYPE_STRING) {
            toCell.setCellValue(fromCell.getRichStringCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_BLANK) {
            // nothing21
        } else if (fromCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
            toCell.setCellValue(fromCell.getBooleanCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_ERROR) {
            toCell.setCellErrorValue(fromCell.getErrorCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_FORMULA) {
            toCell.setCellFormula(fromCell.getCellFormula());
        } else { // nothing29
        }

    }

    public static void copyRow(XSSFWorkbook wb, XSSFRow oldRow, XSSFRow toRow){
        toRow.setHeight(oldRow.getHeight());
        for (Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb,tmpCell, newCell);
        }
    }
    public static void copySheet(XSSFWorkbook wb,XSSFSheet fromSheet, XSSFSheet toSheet) {
        mergeSheetAllRegion(fromSheet, toSheet);
        //设置列宽
        for(int i=0;i<=fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();i++){
            toSheet.setColumnWidth(i,fromSheet.getColumnWidth(i));
        }
        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            XSSFRow oldRow = (XSSFRow) rowIt.next();
            XSSFRow newRow = toSheet.createRow(oldRow.getRowNum());
            copyRow(wb,oldRow,newRow);
        }
    }


    public static void main(String[] args) {
        List<String> pathList = new ArrayList<String>();
        pathList.add("d:/swingPrint/printTemplate/" + "3容量法100_4" + ".xlsx");
//	    	pathList.add("d:/swingPrint/printTemplate/" + "48藻类计数检测原始记录_1" + ".xlsx");
        //将所有类型的尽调excel文件合并成一个excel文件
        XSSFWorkbook newExcelCreat = new XSSFWorkbook();
        try {

            for(int i = 0;i<pathList.size();i++) {//遍历每个源excel文件，fileNameList为源文件的名称集合
                InputStream in = new FileInputStream(pathList.get(i));
                XSSFWorkbook fromExcel = new XSSFWorkbook(in);
                XSSFSheet oldSheet = fromExcel.getSheetAt(0);//模板文件Sheet1
                XSSFSheet newSheet = newExcelCreat.createSheet("Sheet"+(i+1)+"");
                copySheet(newExcelCreat, oldSheet, newSheet);
            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        String allFileName="d:/swingPrint/tempFile/fgModelPrint.xlsx";
        try {
            FileOutputStream fileOut = new FileOutputStream(allFileName);
            newExcelCreat.write(fileOut);
            fileOut.flush();
            fileOut.close();
            System.out.println("复制成功");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
