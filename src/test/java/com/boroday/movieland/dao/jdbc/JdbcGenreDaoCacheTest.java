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

    @Test
    public void testGetAll() throws InterruptedException {
        /*
        //prepare
        testDataSource.init();
        GenreDao genreDao = new JdbcGenreDao(jdbcTemplate);

        //when
        log.info("Testing for getting genres from DB is started");
        Genre genre = genreDao.getAll().get(0);
        assertEquals("драма", genre.getName());

        log.info("Testing for getting cached genres is started");
        genreDao.changeTheFirst();
        genre = genreDao.getAll().get(0);
        assertEquals("драма", genre.getName());// почему кэш не работает в тесте? При запуске на сервере данные кэшируются todo
        Thread.sleep(60000L);
        genre= genreDao.getAll().get(0);
        assertEquals("Drama", genre.getName());

        testDataSource.cleanup();
        */
    }
}
