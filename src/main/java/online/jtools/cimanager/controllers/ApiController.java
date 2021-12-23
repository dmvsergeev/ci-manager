package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.PasswordDAO;
import online.jtools.cimanager.DAO.UserDAO;
import online.jtools.cimanager.models.PasswordsList;
import online.jtools.cimanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "/api")
public class ApiController {

    private final UserDAO userDAO;
    private final PasswordDAO passwordDAO;

    @Autowired
    public ApiController(UserDAO userDAO, PasswordDAO passwordDAO) {
        this.userDAO = userDAO;
        this.passwordDAO = passwordDAO;
    }

    @GetMapping("users")
    public List<User> index(Model model) {
        return userDAO.index();
    }

    @GetMapping("passwords")
    public List<PasswordsList> passwords(Model model) {
        return passwordDAO.getForUser();
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "users/detail";
    }

    @GetMapping("user/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public boolean create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return true;
    }

}
