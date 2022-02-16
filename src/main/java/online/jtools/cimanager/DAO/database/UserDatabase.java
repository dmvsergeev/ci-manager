package online.jtools.cimanager.DAO.database;

import online.jtools.cimanager.DAO.api.UserDAO;
import online.jtools.cimanager.DAO.database.exception.IdentifierTypeException;
import online.jtools.cimanager.DAO.database.mapper.UserDatabaseMapper;
import online.jtools.cimanager.controllers.validator.exception.DbSaveException;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.api.Identifier;
import online.jtools.cimanager.models.pojo.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDatabase implements UserDAO {

    @NotNull
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDatabase(@NotNull JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @NotNull
    @Override
    public List<User> list() {
        return jdbcTemplate.query("SELECT id AS id_user, " +
                "username, " +
                "name AS user_name, " +
                "email AS user_email, " +
                "password AS user_password, " +
                "active AS user_active " +
                "FROM public.\"Users\"", new UserDatabaseMapper());
    }

    @NotNull
    @Override
    public User get(Identifier id) {
        return null;
    }

    @NotNull
    @Override
    public User save(@NotNull User user) {
        final int result;
        switch (user.getId().type()) {
            case NEW:
                result = jdbcTemplate.update("INSERT INTO public.\"Users\" (\"id\",\"username\",\"password\",\"name\",\"email\",\"active\") " +
                        "VALUES(?,?,?,?,?,?)", user.getId().toString(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive());
                break;
            case STORED:
                result = jdbcTemplate.update("UPDATE public.\"Users\" SET \"username\" = ?, \"password\" = ?,\"name\" = ?, \"email\" = ?, \"active\" = ?"
                        + " WHERE \"id\" = ?", user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive(), user.getId().toString());
                break;
            default:
                throw new IdentifierTypeException(user.getId().type().toString());
        }

        if (result != 0) {
            return new User(new DefaultIdentifier(user.getId().toString()), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.isActive());
        } else {
            throw new DbSaveException("DB save error");
        }
    }
}