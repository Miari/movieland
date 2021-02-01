package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.GenreDao;
import com.boroday.movieland.dao.jdbc.JdbcGenreDao;
import com.boroday.movieland.entity.Genre;
import com.boroday.movieland.util.TestDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class JdbcGenreDaoCacheTest {

    private static TestDataSource testDataSource = new TestDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(testDataSource);
    private static List<Genre> expectedGenres;

    @BeforeAll
    public static void createGenres() {
        /*log.info("Testing for getting genres is started");
        testDataSource.init();
        Genre firstGenre = new Genre();
        firstGenre.setId(8);
        firstGenre.setName("фантастика");

        Genre secondGenre = new Genre();
        secondGenre.setId(13);
        secondGenre.setName("мультфильм");

        expectedGenres = new LinkedList<>();
        expectedGenres.add(firstGenre);
        expectedGenres.add(secondGenre);*/
    }

    @AfterAll
    public static void removeGenres() {
        /*testDataSource.cleanup();*/
    }

    @Test
    public void testGetAll() {
        //to remove
        testDataSource.init();
        /*Genre firstGenre = new Genre();
        firstGenre.setId(8);
        firstGenre.setName("фантастика");

        Genre secondGenre = new Genre();
        secondGenre.setId(13);
        secondGenre.setName("мультфильм");

        expectedGenres = new LinkedList<>();
        expectedGenres.add(firstGenre);
        expectedGenres.add(secondGenre);*/
        //end

        //prepare
        GenreDao genreDao = new JdbcGenreDao(jdbcTemplate);

        //when
        log.info("Testing for getting genres from DB is started");
        List<Genre> genres = genreDao.getAll();
        log.info("Testing for getting cached genres is started");
        List<Genre> genres1 = genreDao.getAll();

        //then
        /*assertEquals(genres.size(), 15);
        for (Genre expectedGenre : expectedGenres) {
            assertTrue(genres.contains(expectedGenre));
        }*/

        testDataSource.cleanup();
    }
}
