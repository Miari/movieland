package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Genre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class GenreRowMapper implements RowMapper<Genre> {

    public Genre mapRow(ResultSet resultSet, int countOfRecords) throws SQLException {
        Genre genre = new Genre();
            genre.setId(resultSet.getLong("id"));
            genre.setName(resultSet.getString("name"));
            if (genre.getId() == 0 || genre.getName() == null) {
                log.info("Incorrect data for genre mapping");
                throw new RuntimeException("Incorrect data for genre mapping");
            }
            return genre;

    }
}
