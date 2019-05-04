package start.useLicense;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class install {
    @RequestMapping("install")
    public String installp(HttpServletRequest request, HttpServletResponse response){
        new Thread(() -> {
            EndInt i = new EndInt();
            i.EndInt();
        }).run();
        return "install";
    }

}