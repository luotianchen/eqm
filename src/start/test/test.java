package start.test;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@Controller
public class test {
    @RequestMapping(value = "test")
    public @ResponseBody String test(@RequestParam(value="excel") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("");
        InputStream inputStream = multipartFile.getInputStream();                           //服务器根目录的路径
        String path = realPath;                                                             //根目录下新建文件夹upload，存放上传图片
        String uploadPath = path + "upload";                                                //获取文件名称
        String filename = getUploadFileName(multipartFile);                                 //将文件上传的服务器根目录下的upload文件夹
        File file = new File(uploadPath, filename);
        FileUtils.copyInputStreamToFile(inputStream, file);
        String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/eqm/upload/" + filename;
        String url2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/eqm/upload/" + "压力容器产品质量计划.pdf";
        return url2;
    }

    private String getUploadFileName(MultipartFile multipartFile) {
        String uploadFileName = multipartFile.getOriginalFilename();
        String fileName = uploadFileName.substring(0,
                uploadFileName.lastIndexOf("."));
        String type = uploadFileName.substring(uploadFileName.lastIndexOf("."));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String name = fileName + "_" + timeStr + type;
        return name;
    }

    public static void excel2Pdf(String inFilePath, String outFilePath) throws Exception {
        ActiveXComponent ax = null;
        Dispatch excel = null;
        try {
            ComThread.InitSTA();
            ax = new ActiveXComponent("Excel.Application");
            ax.setProperty("Visible", new Variant(false));
            ax.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();
            Object[] obj = new Object[]{
                    inFilePath,
                    new Variant(false),
                    new Variant(true)
            };
            excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();
            Dispatch sheets = Dispatch.call(excel, "Worksheets").toDispatch();
            Dispatch sheet = Dispatch.call(sheets, "Item", new Integer(1)).toDispatch();
            Dispatch pageSetup = Dispatch.call(sheet, "PageSetup").toDispatch();
            Dispatch.put(pageSetup, "Orientation", new Variant(2));
            Dispatch.put(pageSetup, "PaperSize", new Integer(9));//A3是8，A4是9，A5是11等等
            File tofile = new File(outFilePath);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if (tofile.exists()) {
                tofile.delete();
            }
            // 转换格式
            Object[] obj2 = new Object[]{
                    new Variant(0), // PDF格式=0
                    outFilePath,
                    new Variant(0)  //0=标准 (生成的PDF图片不会变模糊) ; 1=最小文件
            };
            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method,obj2, new int[1]);
            System.out.println("转换完毕！");
        } catch (Exception es) {
            es.printStackTrace();
            throw es;
        } finally {
            if (excel != null) {
                Dispatch.call(excel, "Close", new Variant(false));
            }
            if (ax != null) {
                ax.invoke("Quit", new Variant[] {});
                ax = null;
            }
            ComThread.Release();
        }
    }
}
