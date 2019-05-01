package start.useLicense;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class install {
    @RequestMapping(value = "install")
    public @ResponseBody result install() throws Exception{
        EndInt i = new EndInt();
        i.EndInt();
        result r = new result();
        r.setResult("success");
        return r;
    }

}
