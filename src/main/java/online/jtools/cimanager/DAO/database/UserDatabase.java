package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.database.mapper.UserMapper;
import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserDatabase implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabase(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<User> users;

    @Override
    public List<User> list() {
        return jdbcTemplate.query("SELECT id, username, password, active FROM public.\"Users\"", new UserMapper());
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void save(User user) {

    }

}