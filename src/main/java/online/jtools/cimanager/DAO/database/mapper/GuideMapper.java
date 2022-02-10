package online.jtools.cimanager.DAO.database.mapper;


import online.jtools.cimanager.models.pojo.Guide;
import online.jtools.cimanager.models.api.DefaultIdentifier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GuideMapper implements RowMapper<Guide> {

    @Override
    public Guide mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Guide(new DefaultIdentifier(rs.getString("id_guide")),
                rs.getString("title"),
                rs.getString("content"));
    }
}
