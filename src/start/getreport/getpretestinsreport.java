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

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static start.excel.excel.putsheet;

@CrossOrigin
@Controller
public class getpretestinsreport {                                                              //液压试验报告
    @RequestMapping(value = "getpretestinsreport")
    public @ResponseBody ResponseEntity<byte[]> getpretestinsreport(String prodno,String name, HttpServletRequest request) throws IOException, ParseException, SQLException, ClassNotFoundException {
        ArrayList<searchpredata> data = new searchpre().searchpre(prodno,name);

        String realPath = request.getSession().getServletContext().getRealPath("");
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        System.out.println(uploadPath);
        File realfile = new File(uploadPath,"液压试验报告.xlsx");
        InputStream inputStream = new FileInputStream(realfile.getAbsoluteFile());                           //服务器根目录的路径

        String filename = "液压试验报告_copy.xlsx";                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);


        Calendar calendar =new GregorianCalendar();                                                     //日期操作方法

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("MMM.dd.yyyy", Locale.US);



        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = uploadPath +"/"+ filename;


        FileInputStream fileXlsx = new FileInputStream(url1);                                       //填写报表
        XSSFWorkbook workBook = new XSSFWorkbook(fileXlsx);
        fileXlsx.close();
        Sheet sheet=workBook.getSheetAt(0);


        for (int i = 0;i<data.size();i++){

            putsheet(sheet,3+i*45,0,data.get(i).getDwgno());
            putsheet(sheet,3+i*45,6,data.get(i).getProdno());
            putsheet(sheet,4+i*45,1,data.get(i).getName());
            putsheet(sheet,5+i*45,1,data.get(i).getEname());

            calendar.setTime(sdf.parse(data.get(i).getDated()));
            putsheet(sheet,4+i*45,4,simpleDateFormat3.format(calendar.getTime()));
            putsheet(sheet,5+i*45,4,simpleDateFormat4.format(calendar.getTime()));
            putsheet(sheet,43+i*45,6,simpleDateFormat3.format(calendar.getTime()));
            putsheet(sheet,44+i*45,6,simpleDateFormat4.format(calendar.getTime()));


            putsheet(sheet,6+i*45,1,data.get(i).getAccuclass());
            putsheet(sheet,6+i*45,4,data.get(i).getMeasrangemin()+"-"+data.get(i).getMeasrangemax());

            if(data.get(i).getCalibdate()!=null && !data.get(i).getCalibdate().equals("")){
                calendar.setTime(sdf.parse(data.get(i).getCalibdate()));
                putsheet(sheet,6+i*45,7,simpleDateFormat3.format(calendar.getTime()));
                putsheet(sheet,7+i*45,7,simpleDateFormat4.format(calendar.getTime()));
                putsheet(sheet,8+i*45,7,simpleDateFormat3.format(calendar.getTime()));
                putsheet(sheet,9+i*45,7,simpleDateFormat4.format(calendar.getTime()));
            }


            putsheet(sheet,10+i*45,1,data.get(i).getPgaugeno1());
            putsheet(sheet,11+i*45,1,data.get(i).getPgaugeno1());
            putsheet(sheet,10+i*45,4,data.get(i).getType());
            putsheet(sheet,10+i*45,7,data.get(i).getTestmedia());
            putsheet(sheet,11+i*45,7,data.get(i).getEtestmedia());
            putsheet(sheet,12+i*45,1,data.get(i).getClcontent());
            putsheet(sheet,12+i*45,4,data.get(i).getCircutemp());
            putsheet(sheet,12+i*45,7,data.get(i).getMediatemp());
            putsheet(sheet,15+i*45,4,data.get(i).getTestpress());
            putsheet(sheet,17+i*45,5,data.get(i).getTestpress());
            putsheet(sheet,27+i*45,4,data.get(i).getTestpress());
            putsheet(sheet,29+i*45,5,data.get(i).getTestpress());
            putsheet(sheet,35+i*45,5,data.get(i).getDewelltime());
        }


        OutputStream out = new FileOutputStream(url1);
        workBook.write(out);
        out.close();


        File filepdf = new File(uploadPath, filename);
        HttpHeaders headers = new HttpHeaders();// 设置一个head
        headers.setContentDispositionFormData("attachment", "液压试验报告.xlsx");// 文件的属性，也就是文件叫什么吧
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);// 内容是字节流
        ResponseEntity<byte[]> download = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(filepdf),headers, HttpStatus.CREATED);
        file.delete();
        filepdf.delete();
        return download;
    }
}