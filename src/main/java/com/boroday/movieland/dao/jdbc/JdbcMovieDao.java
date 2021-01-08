package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.MovieDao;
import com.boroday.movieland.dao.jdbc.mapper.MovieRowMapper;
import com.boroday.movieland.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcMovieDao implements MovieDao {

    private static final String GET_ALL_MOVIES = "select id, nameRu, nameEn, yearOfProduction, description, rating, price, picturePath from movies";
    private static final String GET_ALL_MOVIES_RATING_DESC = "select id, nameRu, nameEn, yearOfProduction, description, rating, price, picturePath from movies order by rating desc";
    private static final String GET_ALL_MOVIES_PRICE_ASC = "select id, nameRu, nameEn, yearOfProduction, description, rating, price, picturePath from movies order by price asc";
    private static final String GET_ALL_MOVIES_PRICE_DESC = "select id, nameRu, nameEn, yearOfProduction, description, rating, price, picturePath from movies order by price desc";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll(String rating, String price) {
        log.info("Getting all movies from DB");
        if (rating == null && price == null) {
            return jdbcTemplate.query(GET_ALL_MOVIES, MOVIE_ROW_MAPPER);
        } else if (price == null && rating.equals("desc")) {
            return jdbcTemplate.query(GET_ALL_MOVIES_RATING_DESC, MOVIE_ROW_MAPPER);
        } else if (rating == null && price.equals("asc")) {
            return jdbcTemplate.query(GET_ALL_MOVIES_PRICE_ASC, MOVIE_ROW_MAPPER);
        } else if (rating == null && price.equals("desc")) {
            return jdbcTemplate.query(GET_ALL_MOVIES_PRICE_DESC, MOVIE_ROW_MAPPER);
        } else {
            log.info("Incorrect parameter(s) for movies' order. Only one sorting parameter is allowed. Provided rating order value: " + rating + ", provided price order value: " + price);
            throw new RuntimeException("Incorrect parameter(s) for movies' order. Only one sorting parameter is allowed. Provided value was: " + rating + ", provided price order value: " + price);
        }
    }
}
