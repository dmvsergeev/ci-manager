package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.database.PasswordDatabase;
import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.models.pojo.PasswordsList;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080/api")
public class ApiController {

    private final UserDAO userDAO;
    private final PasswordDatabase passwordDAO;

    @Autowired
    public ApiController(UserDAO userDAO, PasswordDatabase passwordDAO) {
        this.userDAO = userDAO;
        this.passwordDAO = passwordDAO;
    }

    @GetMapping("users")
    public List<User> index(Model model) {
        return userDAO.list();
    }

    @GetMapping("passwords")
    public List<PasswordsList> passwords(Model model) {
        return passwordDAO.getForUser();
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
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
