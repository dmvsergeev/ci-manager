package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.api.*;
import online.jtools.cimanager.controllers.mapper.AppMapper;
import online.jtools.cimanager.controllers.mapper.PasswordMapper;
import online.jtools.cimanager.controllers.mapper.UserMapper;
import online.jtools.cimanager.controllers.validator.AppValidator;
import online.jtools.cimanager.controllers.validator.PasswordValidator;
import online.jtools.cimanager.controllers.validator.UserValidator;
import online.jtools.cimanager.controllers.validator.exception.ValidationException;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.error.ResponseError;
import online.jtools.cimanager.models.pojo.*;
import online.jtools.cimanager.security.Permission;
import online.jtools.cimanager.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080/api")
public class ApiController {

    private final UserDAO userDAO;
    private final PasswordDAO passwordDAO;
    private final AppDAO appDAO;
    private final NewsDAO newsDAO;
    private final GuideDAO guidesDAO;
    private final UserValidator userValidator;
    private final AppValidator appValidator;
    private final PasswordValidator passwordValidator;

    @Autowired
    public ApiController(UserDAO userDAO, PasswordDAO passwordDAO, AppDAO appDAO,
                         NewsDAO newsDAO, GuideDAO guidesDAO, UserValidator userValidator, AppValidator appValidator, PasswordValidator passwordValidator) {
        this.userDAO = userDAO;
        this.passwordDAO = passwordDAO;
        this.appDAO = appDAO;
        this.newsDAO = newsDAO;
        this.guidesDAO = guidesDAO;
        this.userValidator = userValidator;
        this.appValidator = appValidator;
        this.passwordValidator = passwordValidator;
    }

    @GetMapping("users")
    public List<User> index() {
        return userDAO.list();
    }

    @GetMapping("passwords")
    public List<PasswordsList> passwords() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();

        return passwordDAO.getForUser(currentName);
    }

    @GetMapping("apps")
    public List<App> apps() {
        return appDAO.getAll();
    }

    @GetMapping("news")
    public List<News> news() {

        //return new Execution(new User()).exec(Permission.NEWS, () -> newsDAO.getAll() );
        return newsDAO.getAll();
    }

    @GetMapping("news/get/{id}")
    public News newsGet(@PathVariable("id") String id) {
        return newsDAO.get(id);
    }

    @GetMapping("guides")
    public List<Guide> guides() {
        return guidesDAO.getAll();
    }

    @GetMapping("guides/get/{id}")
    public Guide guideGet(@PathVariable("id") String id) {
        return guidesDAO.get(id);
    }

    @GetMapping("topmenu")
    public List<MenuLink> topmenu() {
        final Role role = Role.of("user"); // TODO
        return new Menu(Collections.singletonList(role))
                .addLink(Permission.PASSWORDS, "Мои пароли", "/passwords")
                .addLink(Permission.NEWS, "Новости", "/news")
                .addLink(Permission.GUIDES, "Инструкции", "/guides")
                .addLink(Permission.ALL_USERS, "Пользователи", "/allusers")
                .addLink(Permission.CREATE_USER, "Создать пользователя", "/createuser")
                .addLink(Permission.APPS, "Приложения", "/apps")
                .addLink(Permission.CREATE_APP, "Создать Приложение", "/createapp")
                .links();
    }

    @PostMapping("user/create")
    @ResponseBody
    public Identifier createUser(@RequestBody Map<String, Object> body) {
        User user = new UserMapper().createUserRequest(body);
        userValidator.validate(user);
        return userDAO.save(user);
    }

    @PostMapping("password/set")
    @ResponseBody
    public boolean setPassword(@RequestBody Map<String, Object> body) {
        Password password = new PasswordMapper().createPasswordRequest(body);
        passwordValidator.validate(password);
        passwordDAO.save(password);
        return true;
    }

    @PostMapping("app/create")
    public String createApp(@RequestBody Map<String, Object> body) {
        try {
            App app = new AppMapper().createAppRequest(body);
            appValidator.validate(app);
            return appDAO.save(app);
        } catch (ValidationException e) {
//            System.out.println(e);
            return "false";
        }
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseError handleException(ValidationException ex, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ResponseError(ex.getCode(), ex.getMessage());
    }


}
