package start.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Controller
@CrossOrigin
public class index {
    @RequestMapping("index")
    public String login1(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }
    @RequestMapping("/")
    public String login2(HttpServletRequest request, HttpServletResponse response){
        return "index";
    }
}
