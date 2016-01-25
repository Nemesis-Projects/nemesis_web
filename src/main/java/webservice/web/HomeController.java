package webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index(@RequestHeader(value="User-Agent", defaultValue="foo") String userAgent,Model model) {
    	
    	model.addAttribute("browser", userAgent);
        return "home";
    }

    /*
     @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false) String name, Model model) {
    	if(StringUtils.isNotEmpty(name))
    	{
        	model.addAttribute("name", name);
        }
        return "home";
    }
    */

}