package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.Guide;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideDatabaseMapper implements RowMapper<Guide> {

    @Override
    @NotNull
    public Guide mapRow(@NotNull ResultSet rs, int rowNum) throws SQLException {
        return new Guide(new DefaultIdentifier(rs.getString("id_guide")),
                rs.getString("guide_title"),
                rs.getString("guide_content"));
    }
}
