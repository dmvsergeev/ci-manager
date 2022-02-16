package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.User;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDatabaseMapper implements RowMapper<User> {
    @NotNull
    @Override
    public User mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new User(new DefaultIdentifier(rs.getString("user_id")),
                rs.getString("user_name"),
                rs.getString("user_email"),
                rs.getString("username"));
    }
}
