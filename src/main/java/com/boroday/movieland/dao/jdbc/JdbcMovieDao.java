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

    private static final String GET_ALL_MOVIES = "select id, nameRu, nameEn, yearOfProduction, description, rating, price from movies";
    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getAll() {
        log.info("Getting all movies from DB");
        return jdbcTemplate.query(GET_ALL_MOVIES, MOVIE_ROW_MAPPER);
    }
}
