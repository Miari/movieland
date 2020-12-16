package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper {
    public Movie mapRow(ResultSet resultSet) throws SQLException {
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
