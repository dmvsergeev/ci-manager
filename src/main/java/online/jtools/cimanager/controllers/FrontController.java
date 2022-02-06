package online.jtools.cimanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {

    @GetMapping()
    public String index(Model model) {

        return "index";
    }

    @GetMapping("allusers")
    public String allusers(Model model) {

        return "index";
    }

    @GetMapping("passwords")
    public String passwords(Model model) {

        return "index";
    }

    @GetMapping("apps")
    public String apps(Model model) {

        return "index";
    }

    @GetMapping("createuser")
    public String createuser(Model model) {

        return "index";
    }

    @GetMapping("setpassword")
    public String setpassword(Model model) {

        return "index";
    }

}
