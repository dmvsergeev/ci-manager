package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.api.DefaultIdentifier;
import online.jtools.cimanager.models.pojo.App;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppDatabaseMapper implements RowMapper<App> {

    @Override
    @NotNull
    public App mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new App(new DefaultIdentifier(rs.getString("id_app")),
                rs.getString("app_name"),
                rs.getString("app_url"));
    }
}
