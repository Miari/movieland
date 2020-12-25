package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieRowMapper implements RowMapper<Movie> {

    public Movie mapRow(ResultSet resultSet, int countOfRecords) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getLong("id"));
        movie.setNameRu(resultSet.getString("nameRu"));
        movie.setNameEn(resultSet.getString("nameEn"));
        movie.setYearOfProduction(resultSet.getInt("yearOfProduction"));
        movie.setDescription(resultSet.getString("description"));
        movie.setRating(resultSet.getDouble("rating"));
        movie.setPrice(resultSet.getDouble("price"));
        return movie;
    }
}
