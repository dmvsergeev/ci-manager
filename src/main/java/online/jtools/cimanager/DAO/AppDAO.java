package online.jtools.cimanager.DAO;

import online.jtools.cimanager.DAO.database.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import online.jtools.cimanager.models.pojo.User;

import java.util.List;

@Component
public class AppDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static int USERS_COUNT;

    private List<User> users;

    public List<User> index() { return jdbcTemplate.query("SELECT * FROM public.\"Apps\"", new UserMapper()); }

    public User show(int id) {
        return null;/*users.stream().filter(user -> user.getId() == id).findAny().orElse(null)*/
    }

    public void save(User user) {
//        user.setId(++USERS_COUNT);
        users.add(user);
    }
}