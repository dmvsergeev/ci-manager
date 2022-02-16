package online.jtools.cimanager.DAO.database.mapper;

import online.jtools.cimanager.models.pojo.Password;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordDatabaseMapper implements RowMapper<Password> {

    @NotNull
    @Override
    public Password mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new Password(new AppDatabaseMapper().mapRow(rs,rowNum),
                new UserDatabaseMapper().mapRow(rs,rowNum),
                rs.getString("password"));
    }
}
