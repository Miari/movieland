package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.GenreDao;
import com.boroday.movieland.dao.MovieGenreDao;
import com.boroday.movieland.entity.Genre;
import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.util.TestDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcMovieGenreTest {
    private static TestDataSource testDataSource = new TestDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(testDataSource);

    @BeforeAll
    public static void createGenres() {
       testDataSource.init();
    }

    @AfterAll
    public static void removeGenres() {
        testDataSource.cleanup();
    }

    @Test
    public void testGetMovieByGenre() {
        //prepare
        MovieGenreDao movieGenreDao = new JdbcMovieGenreDao(jdbcTemplate);

        //when
        List<Movie> movies = movieGenreDao.getMoviesByGenre(15);

        //then
        assertEquals(movies.size(), 3);
        assertEquals(21, movies.get(0).getId());
        assertEquals(24, movies.get(1).getId());
        assertEquals(25, movies.get(2).getId());
    }
}
