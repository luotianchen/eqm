package start.getreport;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import start.jdbc.jdbc;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.util.List;

import static start.excel.excel.*;

@Controller
@CrossOrigin
public class getquaplanport {                                               //压力容器产品质量计划报表
    @RequestMapping(value = "getquaplanport")
    public @ResponseBody ResponseEntity<byte[]> getquaplanport(@RequestParam(value="excel") MultipartFile multipartFile, String prodno,HttpServletRequest request) throws Exception {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps = null;
        ResultSet rs=null;
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;

        String realPath = request.getSession().getServletContext().getRealPath("");
        InputStream inputStream = multipartFile.getInputStream();                           //服务器根目录的路径
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        String filename = getUploadFileName(multipartFile);                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;
        String url2 = uploadPath +"/"+ "123.pdf";
        System.out.println(url1);
        System.out.println(url2);





        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);
        List<List<String>> result = readXlsx(workBook);

        int num = 8;
        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,8,7,rs.getString("stand"));
            for (int i = 0;i<5;i++){
                putsheet(sheet,9+i,7,rs.getString("stand")+";"+searchstand(prodno));
                System.out.println(rs.getString("stand")+";"+searchstand(prodno));
            }
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 10");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,14,7,rs.getString("stand"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,15,7,rs.getString("stand")+";"+searchstand(prodno));
            ps1 = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 11");
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                putsheet(sheet,16,7,rs.getString("stand")+";"+searchmainstand(prodno)+""+rs1.getString("stand"));
            }
            rs1.close();
            ps1.close();
            for (int i =0;i<3;i++){
                putsheet(sheet,17+i,7,rs.getString("stand"));
            }
            putsheet(sheet,20,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,21,7,rs.getString("stand"));
            putsheet(sheet,22,7,rs.getString("stand"));
            putsheet(sheet,23,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,24,7,rs.getString("stand")+";"+searchstand(prodno));

            ps1 = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 12");
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                putsheet(sheet,25,7,rs.getString("stand") + "" + rs1.getString("stand"));
            }
            rs1.close();
            ps1.close();

        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 11");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,26,7,rs.getString("stand"));
            ps1 = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 12");
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                putsheet(sheet,27,7,rs.getString("stand") + "" + rs1.getString("stand"));
            }
            rs1.close();
            ps1.close();
            ps1 = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 12");
            rs1 = ps1.executeQuery();
            if(rs1.next()){
                putsheet(sheet,28,7,rs.getString("stand") + "" + rs1.getString("stand"));
            }
            rs1.close();
            ps1.close();
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,29,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,30,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,31,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,32,7,rs.getString("stand")+";"+searchstand(prodno));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 11");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,33,7,rs.getString("stand")+";设计文件");
            putsheet(sheet,34,7,rs.getString("stand")+";设计文件");
            putsheet(sheet,35,7,rs.getString("stand")+";设计文件");
            putsheet(sheet,36,7,rs.getString("stand")+";设计文件");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 13");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,37,7,rs.getString("stand"));
            putsheet(sheet,38,7,rs.getString("stand"));
            putsheet(sheet,39,7,rs.getString("stand"));
            putsheet(sheet,40,7,rs.getString("stand"));
            putsheet(sheet,41,7,rs.getString("stand"));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,42,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,43,7,rs.getString("stand")+";"+searchstand(prodno));
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 11");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,44,7,rs.getString("stand")+";设计文件");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,45,7,rs.getString("stand")+";"+searchstand(prodno));
            putsheet(sheet,46,7,rs.getString("stand")+";"+searchstand(prodno));
        }
        rs.close();
        ps.close();

        if((searchmainstand(prodno)+";")==null || (searchmainstand(prodno)+";").equals("")){
            putsheet(sheet,47,7,searchmainstand(prodno)+";设计图样");
            putsheet(sheet,48,7,searchmainstand(prodno)+";设计图样");
            putsheet(sheet,49,7,searchmainstand(prodno)+";设计图样");
            putsheet(sheet,50,7,searchmainstand(prodno)+";设计图样");
        }


        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,51,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,52,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,53,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,54,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,55,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,56,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 5");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,57,7,rs.getString("stand") + ";设计图样");
        }
        rs.close();
        ps.close();

        ps = conn.prepareStatement("SELECT * FROM datacontraststandstand WHERE id = 9");
        rs = ps.executeQuery();
        if(rs.next()){
            putsheet(sheet,58,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,59,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,60,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
            putsheet(sheet,61,7,rs.getString("stand")+";"+searchstand(prodno) + ";设计图样");
        }
        rs.close();
        ps.close();

        putsheet(sheet,11,11,"待定");         //4

        ps = conn.prepareStatement("SELECT * FROM promanparlist WHERE prodno = ? AND status = 1");
        ps.setString(1,prodno);
        rs = ps.executeQuery();
        if(rs.next()){
            if(rs.getString("dealter").equals("无") && rs.getString("submatl").equals("无")){
                putsheet(sheet,11,11,"R/E");        //4
            }else {
                putsheet(sheet,11,11,"/");        //4
            }

            if(rs.getString("nprocess").equals("有")){
                putsheet(sheet,17,11,"R");        //10
            }else {
                putsheet(sheet,17,11,"/");        //10
            }

            if(rs.getString("matlretest").equals("有")){
                putsheet(sheet,20,11,"R");        //13
            }else {
                putsheet(sheet,20,11,"/");        //13
            }

            if(rs.getString("overmatl").equals("有")){
                putsheet(sheet,21,11,"R");        //14
            }else {
                putsheet(sheet,21,11,"/");        //14
            }

            if(rs.getString("specmatl").equals("有")){
                putsheet(sheet,24,11,"R/E");        //17
            }else {
                putsheet(sheet,24,11,"/");        //17
            }

            if(rs.getString("submatl").equals("有")){
                putsheet(sheet,25,11,"R");        //18
            }else {
                putsheet(sheet,25,11,"/");        //18
            }

            if(rs.getString("nwpq").equals("有")){
                putsheet(sheet,26,11,"R");        //19
                putsheet(sheet,27,11,"H");        //20
                putsheet(sheet,28,11,"R");        //21
                putsheet(sheet,29,11,"R");        //22
                putsheet(sheet,32,11,"E");        //25
            }else {
                putsheet(sheet,26,11,"/");        //19
                putsheet(sheet,27,11,"/");        //20
                putsheet(sheet,28,11,"/");        //21
                putsheet(sheet,29,11,"/");        //22
                putsheet(sheet,32,11,"/");        //25

            }

            if(rs.getString("copsitu").equals("有")){
                putsheet(sheet,63,11,"W");        //56
            }else {
                putsheet(sheet,63,11,"/");        //56
            }

        }
        rs.close();
        ps.close();

        ps1 = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps1.setString(1,prodno);
        rs1 = ps1.executeQuery();
        if(rs1.next()){
            ps = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps.setString(1,rs1.getString("dwgno"));
            rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString("testplatesitu").equals("有")){
                    putsheet(sheet,34,11,"E");        //27
                    putsheet(sheet,35,11,"H");        //28
                    putsheet(sheet,36,11,"R/E");        //29
                    putsheet(sheet,37,11,"R/E");        //30
                }else {
                    putsheet(sheet,34,11,"/");        //27
                    putsheet(sheet,35,11,"/");        //28
                    putsheet(sheet,36,11,"/");        //29
                    putsheet(sheet,37,11,"/");        //30
                }

                if(rs.getString("httsetplate").equals("有")){
                    putsheet(sheet,43,11,"E");        //36
                    putsheet(sheet,44,11,"H");        //37
                    putsheet(sheet,45,11,"E");        //38
                }else {
                    putsheet(sheet,43,11,"/");        //36
                    putsheet(sheet,44,11,"/");        //37
                    putsheet(sheet,45,11,"/");        //38
                }

                if(rs.getString("httype").equals("--")){
                    putsheet(sheet,46,11,"/");        //39
                    putsheet(sheet,47,11,"/");        //40
                }else {
                    putsheet(sheet,46,11,"R");        //39
                    putsheet(sheet,47,11,"R");        //40
                }

                if(rs.getString("analyde").equals("有")){
                    putsheet(sheet,51,11,"E");        //44
                }else {
                    putsheet(sheet,51,11,"/");        //44
                }

                if(rs.getString("crytank").equals("有")){
                    putsheet(sheet,58,11,"E");        //51
                }else {
                    putsheet(sheet,58,11,"/");        //51
                }

                if(rs.getString("saferel").equals("有")){
                    putsheet(sheet,61,11,"R");        //54
                }else {
                    putsheet(sheet,61,11,"/");        //54
                }
            }
            rs.close();
            ps.close();
        }
        rs1.close();
        ps1.close();

        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();



        FileInputStream fileXlsx1 = new FileInputStream(url1);                              //隐藏行
        XSSFWorkbook workBook1 = new XSSFWorkbook(fileXlsx1);
        fileXlsx1.close();
        Sheet sheet1=workBook1.getSheetAt(0);
        List<List<String>> result1 = readXlsx(workBook1);
        for (int i = 0;i<64;i++){
            System.out.println(result1.get(i).get(11)+i);
            if(result1.get(i).get(11).equals("/")){
                System.out.println(result1.get(i).get(11)+i);
                Row row = sheet1.getRow(i+1);
                row.setZeroHeight(true);
            }
        }
        OutputStream out1 = new FileOutputStream(url1);
        workBook1.write(out1);
        out1.close();



        excel2Pdf(url1,url2);                                       //转PDF
        File filepdf = new File(uploadPath, "123.pdf");
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "123.pdf");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }


    public String searchstand(String prodno) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;

        String stand = null;

        ps1 = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps1.setString(1,prodno);
        rs1 = ps1.executeQuery();
        if(rs1.next()){
            ps2 = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps2.setString(1,rs1.getString("dwgno"));
            rs2 = ps2.executeQuery();
            if(rs2.next()){
                if(rs2.getString("mainstand").equals(rs2.getString("minorstand"))){
                    stand = rs2.getString("mainstand");
                }else {
                    stand = rs2.getString("mainstand") + ";" + rs2.getString("minorstand");
                }
            }
            rs2.close();
            ps2.close();
        }
        rs1.close();
        ps1.close();
        conn.close();
        return stand;
    }


    public String searchmainstand(String prodno) throws ClassNotFoundException, SQLException {
        jdbc j = new jdbc();
        Class.forName(j.getDBDRIVER());
        Connection conn = DriverManager.getConnection(j.getDBURL(),j.getDBUSER(),j.getDBPASS());
        PreparedStatement ps1 = null;
        ResultSet rs1=null;
        PreparedStatement ps2 = null;
        ResultSet rs2=null;

        String stand = null;

        ps1 = conn.prepareStatement("SELECT * FROM prenotiform WHERE prodno = ?");
        ps1.setString(1,prodno);
        rs1 = ps1.executeQuery();
        if(rs1.next()){
            ps2 = conn.prepareStatement("SELECT * FROM proparlist WHERE dwgno = ? AND audit = 1");
            ps2.setString(1,rs1.getString("dwgno"));
            rs2 = ps2.executeQuery();
            if(rs2.next()){
                stand = rs2.getString("mainstand");
            }
            rs2.close();
            ps2.close();
        }
        rs1.close();
        ps1.close();
        conn.close();
        return stand;
    }
}
