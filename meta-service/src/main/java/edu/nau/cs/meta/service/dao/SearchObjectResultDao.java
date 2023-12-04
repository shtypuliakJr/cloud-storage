package edu.nau.cs.meta.service.dao;

import edu.nau.cs.meta.service.dto.search.SearchResultObjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SearchObjectResultDao {

    private static final String SEARCH_QUERY = """
            SELECT
            	id as "ID",
            	folder_name as "OBJECT_NAME",
            	folder_path as "OBJECT_PATH",
            	true as "IS_FOLDER"
              FROM folder_object
             WHERE user_id = :userId
               AND folder_name LIKE :objectNameTemplate || '%'
            UNION ALL
            SELECT
            	id as "ID",
            	file_name as "OBJECT_NAME",
            	file_path as "OBJECT_PATH",
            	false as "IS_FOLDER"
              FROM file_object
             WHERE user_id = :userId
               AND file_name LIKE :objectNameTemplate || '%'
            """;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<SearchResultObjectDTO> searchObjectsByTemplate(String objectNameTemplate, String userId) {
        return namedParameterJdbcTemplate.query(SEARCH_QUERY,
                new MapSqlParameterSource()
                        .addValue("userId", userId, Types.VARCHAR)
                        .addValue("objectNameTemplate", objectNameTemplate, Types.VARCHAR),
                (rs, rowNum) -> new SearchResultObjectDTO(
                        rs.getString("ID"),
                        rs.getString("OBJECT_NAME"),
                        rs.getString("OBJECT_PATH"),
                        rs.getBoolean("IS_FOLDER"),
                        rowNum
                ));
    }

}
