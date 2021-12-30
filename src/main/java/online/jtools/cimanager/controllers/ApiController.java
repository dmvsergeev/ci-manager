package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.api.AppDAO;
import online.jtools.cimanager.DAO.api.PasswordDAO;
import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.controllers.validator.UserValidator;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.pojo.App;
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
    private final PasswordDAO passwordDAO;
    private final AppDAO appDAO;
    private final UserValidator userValidator;

    @Autowired
    public ApiController(UserDAO userDAO, PasswordDAO passwordDAO, AppDAO appDAO,
                         UserValidator userValidator) {
        this.userDAO = userDAO;
        this.passwordDAO = passwordDAO;
        this.appDAO = appDAO;
        this.userValidator = userValidator;
    }

    @GetMapping("users")
    public List<User> index(Model model) {
        return userDAO.list();
    }

    @GetMapping("passwords")
    public List<PasswordsList> passwords(Model model) {
        return passwordDAO.getForUser();
    }

    @GetMapping("apps")
    public List<App> apps() {
        return appDAO.getAll();
    }

    @GetMapping("user/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.get(id));
        return "users/detail";
    }

    @PostMapping("user/create")
    public boolean createUser(@ModelAttribute("user") User user) {
        try {
            userValidator.validate(user);
            return userDAO.save(user);
        } catch (ValidationException e) {
//            logger.log(e);
            return false;
        }
    }

}
