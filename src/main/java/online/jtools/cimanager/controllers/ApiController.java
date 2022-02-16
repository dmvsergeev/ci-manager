package online.jtools.cimanager.controllers;

import online.jtools.cimanager.DAO.api.*;
import online.jtools.cimanager.controllers.mapper.AppRestMapper;
import online.jtools.cimanager.controllers.mapper.PasswordRestMapper;
import online.jtools.cimanager.controllers.mapper.UserRestMapper;
import online.jtools.cimanager.controllers.validator.AppValidator;
import online.jtools.cimanager.controllers.validator.PasswordValidator;
import online.jtools.cimanager.controllers.validator.UserValidator;
import online.jtools.cimanager.controllers.validator.exception.CimanagerException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.error.ResponseError;
import online.jtools.cimanager.models.pojo.*;
import online.jtools.cimanager.security.Permission;
import online.jtools.cimanager.security.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080/api")
public class ApiController {

    @NotNull
    private final UserDAO userDAO;
    @NotNull
    private final PasswordDAO passwordDAO;
    @NotNull
    private final AppDAO appDAO;
    @NotNull
    private final NewsDAO newsDAO;
    @NotNull
    private final GuideDAO guidesDAO;
    @NotNull
    private final UserValidator userValidator;
    @NotNull
    private final AppValidator appValidator;
    @NotNull
    private final PasswordValidator passwordValidator;

    @Autowired
    public ApiController(@NotNull UserDAO userDAO, @NotNull PasswordDAO passwordDAO, @NotNull AppDAO appDAO,
                         @NotNull NewsDAO newsDAO, @NotNull GuideDAO guidesDAO,
                         @NotNull UserValidator userValidator, @NotNull AppValidator appValidator,
                         @NotNull PasswordValidator passwordValidator) {
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
    @NotNull
    public List<Map<String, String>> index() {
        return new UserRestMapper().createUserResponse(userDAO.list());
    }

    @GetMapping("passwords")
    @NotNull
    public List<Map<String, Object>> passwords() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentName = authentication.getName();

        return new PasswordRestMapper().createPasswordResponse(passwordDAO.getForUser(currentName));
    }

    @GetMapping("apps")
    @NotNull
    public List<Map<String, String>> apps() {
        return new AppRestMapper().createAppResponse(appDAO.getAll());
    }

    @GetMapping("news")
    @NotNull
    public List<News> news() {
        //return new Execution(new User()).exec(Permission.NEWS, () -> newsDAO.getAll() );
        return newsDAO.getAll();
    }

    @GetMapping("news/get/{id}")
    @NotNull
    public News newsGet(@PathVariable("id") String id) {
        return newsDAO.get(new DefaultIdentifier(id));
    }

    @GetMapping("guides")
    @NotNull
    public List<Guide> guides() {
        return guidesDAO.getAll();
    }

    @GetMapping("guides/get/{id}")
    @NotNull
    public Guide guideGet(@PathVariable("id") String id) {
        return guidesDAO.get(new DefaultIdentifier(id));
    }

    @GetMapping("topmenu")
    @NotNull
    public List<MenuLink> topmenu() {
        final List<Role> roles = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::of).collect(Collectors.toList());

        return new Menu(roles)
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
    @NotNull
    public Map<String, String> createUser(@RequestBody @NotNull Map<String, Object> body) {
        final UserRestMapper mapper = new UserRestMapper();
        User user = mapper.createUserRequest(body);
        userValidator.validate(user);
        return mapper.createUserResponse(userDAO.save(user));
    }

    @PostMapping("password/set")
    @ResponseBody
    public boolean setPassword(@RequestBody @NotNull Map<String, Object> body) {
        Password password = new PasswordRestMapper().createPasswordRequest(body);
        passwordValidator.validate(password);
        passwordDAO.save(password);
        return true;
    }

    @PostMapping("app/create")
    @ResponseBody
    @NotNull
    public Map<String, String> createApp(@RequestBody @NotNull Map<String, Object> body) {
        final AppRestMapper mapper = new AppRestMapper();
        App app = mapper.createAppRequest(body);
        appValidator.validate(app);
        return mapper.createAppResponse(appDAO.save(app));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @NotNull
    public ResponseError handleException(@NotNull RuntimeException ex, @NotNull  HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        if (ex instanceof CimanagerException) {
            return new ResponseError(((CimanagerException)ex).getCode(), ex.getMessage());
        } else {
            return new ResponseError("unknown", ex.getMessage());
        }
    }
}
