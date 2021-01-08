package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.GenreDao;
import com.boroday.movieland.entity.Genre;
import com.boroday.movieland.util.TestDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JdbcGenreDaoTest {
    private TestDataSource testDataSource = new TestDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(testDataSource);
    List<Genre> expectedGenres;

    @BeforeEach
    public void createGenres() {
        testDataSource.init();
        Genre firstGenre = new Genre();
        firstGenre.setId(8);
        firstGenre.setName("фантастика");

        Genre secondGenre = new Genre();
        secondGenre.setId(13);
        secondGenre.setName("мультфильм");

        expectedGenres = new LinkedList<>();
        expectedGenres.add(firstGenre);
        expectedGenres.add(secondGenre);
    }

    @AfterEach
    public void removeMovies() {
        testDataSource.cleanup();
    }

    @Test
    public void testGetAll() {
        //prepare
        GenreDao genreDao = new JdbcGenreDao(jdbcTemplate);

        //when
        List<Genre> genres = genreDao.getAll();

        //then
        assertEquals(genres.size(), 15);
        for (Genre expectedGenre : expectedGenres) {
            assertTrue(genres.contains(expectedGenre));
        }
    }
}
