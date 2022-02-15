package online.jtools.cimanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("allusers")
    public String allusers() {
        return "index";
    }

    @GetMapping("passwords")
    public String passwords() {
        return "index";
    }

    @GetMapping("apps")
    public String apps() {
        return "index";
    }

    @GetMapping("news")
    public String news() {
        return "index";
    }

    @GetMapping("story")
    public String story() {
        return "index";
    }

    @GetMapping("guides")
    public String guides() {
        return "index";
    }

    @GetMapping("guide")
    public String guide() {
        return "index";
    }

    @GetMapping("createuser")
    public String createuser() {
        return "index";
    }

    @GetMapping("setpassword")
    public String setpassword() {
        return "index";
    }

}
