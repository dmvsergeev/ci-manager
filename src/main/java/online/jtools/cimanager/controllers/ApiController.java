package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.api.AppDAO;
import online.jtools.cimanager.DAO.api.PasswordDAO;
import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.controllers.mapper.AppMapper;
import online.jtools.cimanager.controllers.mapper.UserMapper;
import online.jtools.cimanager.controllers.validator.AppValidator;
import online.jtools.cimanager.controllers.validator.UserValidator;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.App;
import online.jtools.cimanager.models.pojo.PasswordsList;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080/api")
public class ApiController {

    private final UserDAO userDAO;
    private final PasswordDAO passwordDAO;
    private final AppDAO appDAO;
    private final UserValidator userValidator;
    private final AppValidator appValidator;

    @Autowired
    public ApiController(UserDAO userDAO, PasswordDAO passwordDAO, AppDAO appDAO,
                         UserValidator userValidator, AppValidator appValidator) {
        this.userDAO = userDAO;
        this.passwordDAO = passwordDAO;
        this.appDAO = appDAO;
        this.userValidator = userValidator;
        this.appValidator = appValidator;
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
    @ResponseBody
    public Identifier createUser(@RequestBody Map<String,Object> body) {
        User user = new UserMapper().createUserRequest(body);
        userValidator.validate(user);
        return userDAO.save(user);
    }

    @PostMapping("app/create")
    public String createApp(@RequestBody Map<String,Object> body) {
        try {
            App app = new AppMapper().createAppRequest(body);
            appValidator.validate(app);
            return appDAO.save(app);
        } catch (ValidationException e) {
            System.out.println(e);
            return "false";
        }

    }

}
