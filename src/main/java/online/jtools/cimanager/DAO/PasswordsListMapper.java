package online.jtools.cimanager.DAO;

import online.jtools.cimanager.models.Password;
import online.jtools.cimanager.models.PasswordsList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordsListMapper implements RowMapper<PasswordsList> {

    @Override
    public PasswordsList mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        PasswordsList passwordsList = new PasswordsList();

        passwordsList.setId(resultSet.getInt("id"));
        passwordsList.setApp(resultSet.getString("app"));
        passwordsList.setPassword(resultSet.getString("password"));

        return passwordsList;
    }
}
