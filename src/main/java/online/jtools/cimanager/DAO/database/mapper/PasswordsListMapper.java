package online.jtools.cimanager.DAO.database.mapper;

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
                rs.getInt("id_password"),
                rs.getString("password"),
                new App(rs.getInt("id_app"), rs.getString("app_name"),rs.getString("url")),
                new User(new DefaultIdentifier(rs.getInt("id_user")),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("username"))
        );
    }
}
