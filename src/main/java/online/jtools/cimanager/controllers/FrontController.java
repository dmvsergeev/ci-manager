package online.jtools.cimanager.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class FrontController {

    @GetMapping()
    @NotNull
    public String index() {
        return "index";
    }

    @GetMapping("allusers")
    @NotNull
    public String allusers() {
        return "index";
    }

    @GetMapping("passwords")
    @NotNull
    public String passwords() {
        return "index";
    }

    @GetMapping("apps")
    @NotNull
    public String apps() {
        return "index";
    }

    @GetMapping("news")
    @NotNull
    public String news() {
        return "index";
    }

    @GetMapping("story")
    @NotNull
    public String story() {
        return "index";
    }

    @GetMapping("guides")
    @NotNull
    public String guides() {
        return "index";
    }

    @GetMapping("guide")
    @NotNull
    public String guide() {
        return "index";
    }

    @GetMapping("createuser")
    @NotNull
    public String createuser() {
        return "index";
    }

    @GetMapping("setpassword")
    @NotNull
    public String setpassword() {
        return "index";
    }

}
