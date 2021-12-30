package online.jtools.cimanager.DAO.database.mapper;

import online.jtools.cimanager.models.pojo.AppsList;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppsListMapper implements RowMapper<AppsList> {

    @Override
    public AppsList mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppsList(
                rs.getInt("id_app"),
                rs.getString("name"),
                rs.getString("url")
        );
    }
}
