package online.jtools.cimanager.DAO;

import online.jtools.cimanager.models.pojo.Password;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordMapper implements RowMapper<Password> {

    @Override
    public Password mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Password password = new Password();

        password.setId(resultSet.getInt("id"));
        password.setId_app(resultSet.getInt("id_app"));
        password.setId_user(resultSet.getInt("id_user"));
        password.setPassword(resultSet.getString("password"));

        return password;
    }
}
