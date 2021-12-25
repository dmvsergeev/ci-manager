package online.jtools.cimanager.DAO;

import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.App;
import online.jtools.cimanager.models.pojo.PasswordsList;
import online.jtools.cimanager.models.pojo.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordsListMapper implements RowMapper<PasswordsList> {

    @Override
    public PasswordsList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PasswordsList(
                rs.getInt("password_id"),
                rs.getString("password_value"),
                new App(),
                new User(new DefaultIdentifier(rs.getInt("user_id")),
                        rs.getString("user_name"),
                        rs.getString("user_name"),
                        rs.getString("user_name"))
        );
    }
}
