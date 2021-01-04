package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
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
        movie.setPicturePath(resultSet.getString("picturePath"));
        if (movie.getId() == 0 ||
                movie.getNameRu() == null ||
                movie.getNameEn() == null ||
                movie.getYearOfProduction() == 0 ||
                movie.getDescription() == null ||
                movie.getRating() == 0 ||
                movie.getPrice() == 0 ||
                movie.getPicturePath() == null ) {
            log.info("Incorrect data for movie mapping");
            throw new RuntimeException("Incorrect data for movie mapping");
        }
        return movie;
    }
}
