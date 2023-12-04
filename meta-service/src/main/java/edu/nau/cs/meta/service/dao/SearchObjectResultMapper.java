package edu.nau.cs.meta.service.dao;

import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchObjectResultMapper implements RowMapper<SearchResultObjectDTO> {

    @Override
    public SearchResultObjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SearchResultObjectDTO(
                rs.getString("ID"),
                rs.getString("OBJECT_NAME"),
                rs.getBoolean("IS_FOLDER"),
                rowNum
        );
    }

}
