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
    public void testGetAll() {
        //to remove
        testDataSource.init();

        //prepare
        GenreDao genreDao = new JdbcGenreDao(jdbcTemplate);

        //when
        log.info("Testing for getting genres from DB is started");
        genreDao.getAll();
        log.info("Testing for getting cached genres is started");
        genreDao.getAll(); // почему кэш не работает в тесте? При запуске на сервере данные кэшируются

        testDataSource.cleanup();
    }
}
