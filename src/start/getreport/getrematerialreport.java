package start.getreport;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static start.excel.excel.putsheet;

@Controller
@CrossOrigin
public class getrematerialreport {                                      //材料复验申请单
    @RequestMapping(value = "getrematerialreport")
    public @ResponseBody ResponseEntity<byte[]> getrematerialreport(String codedmarking, HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;

        int matlname_id=0;                              //产品名称
        int supplier_id=0;                              //供货单位
        int designation_id=0;                              //牌号
        int stand_id=0;                                 //标准


        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"材料复验.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "材料复验_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);

        ps = conn.prepareStatement("SELECT * FROM putmaterial WHERE codedmarking = ? AND status = 1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            matlname_id = rs.getInt("matlname_id_matlname");
            supplier_id = rs.getInt("supplier_id_supplier");
            designation_id = rs.getInt("contraststand_id_designation");
            stand_id = rs.getInt("contraststand_id_matlstand");
            putsheet(sheet,4,12,rs.getString("heattreatcondition_id_heatcondi"));
            putsheet(sheet,5,2,rs.getString("spec"));
            putsheet(sheet,7,0,rs.getString("heatbatchno"));
            putsheet(sheet,7,8,rs.getString("qty"));
            putsheet(sheet,7,12,codedmarking);
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM matlname WHERE id = ?");
        ps.setInt(1,matlname_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,3,2,rs.getString("matlname"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM supplier WHERE id = ?");
        ps.setInt(1,supplier_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,3,8,rs.getString("supplier"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
        ps.setInt(1,designation_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,4,2,rs.getString("designation"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM contraststand WHERE id = ?");
        ps.setInt(1,stand_id);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,5,10,rs.getString("matlstand"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM rematerialitem WHERE codedmarking = ? AND status = 1");
        ps.setString(1,codedmarking);
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,8,1,rs.getString("why"));
            putsheet(sheet,12,1,rs.getString("forceperformance"));
            putsheet(sheet,14,1,rs.getString("chemicalcomposition"));
        }
        rs.close();
        ps.close();




        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "材料复验.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}
