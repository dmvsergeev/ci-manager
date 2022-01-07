package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.User;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(new DefaultIdentifier(rs.getString("id")),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("username"));
    }
}
