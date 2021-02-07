package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.MovieDao;
import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.util.TestDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class JdbcMovieDaoTest {

    private static TestDataSource testDataSource = new TestDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(testDataSource);
    private static List<Movie> expectedMovies;

    @BeforeAll
    public static void createMovies() {
        /*log.info("BeforeAll for getting movies is started");
        testDataSource.init();
        Movie firstMovie = new Movie();
        firstMovie.setId(2);
        firstMovie.setNameRu("Зеленая миля");
        firstMovie.setNameEn("The Green Mile");
        firstMovie.setYearOfProduction(1999);
        firstMovie.setDescription("Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.");
        firstMovie.setRating(8.9);
        firstMovie.setPrice(134.67);
        firstMovie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");

        Movie secondMovie = new Movie();
        secondMovie.setId(19);
        secondMovie.setNameRu("Молчание ягнят");
        secondMovie.setNameEn("The Silence of the Lambs");
        secondMovie.setYearOfProduction(1990);
        secondMovie.setDescription("Психопат похищает и убивает молодых женщин по всему Среднему Западу Америки. ФБР, уверенное в том, что все преступления совершены одним и тем же человеком, поручает агенту Клариссе Старлинг встретиться с заключенным-маньяком, который мог бы объяснить следствию психологические мотивы серийного убийцы и тем самым вывести на его след.");
        secondMovie.setRating(8.3);
        secondMovie.setPrice(150.5);
        secondMovie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNjNhZTk0ZmEtNjJhMi00YzFlLWE1MmEtYzM1M2ZmMGMwMTU4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1._SY209_CR1,0,140,209_.jpg");

        expectedMovies = new LinkedList<>();
        expectedMovies.add(firstMovie);
        expectedMovies.add(secondMovie);*/
    }

    @AfterAll
    public static void removeMovies() {
        /*log.info("AfterAll for getting movies is started");
        testDataSource.cleanup();*/
    }

    @Test
    public void testGetAll() {
        //to remove
        testDataSource.init();
        Movie firstMovie = new Movie();
        firstMovie.setId(2);
        firstMovie.setNameRu("Зеленая миля");
        firstMovie.setNameEn("The Green Mile");
        firstMovie.setYearOfProduction(1999);
        firstMovie.setDescription("Обвиненный в страшном преступлении, Джон Коффи оказывается в блоке смертников тюрьмы «Холодная гора». Вновь прибывший обладал поразительным ростом и был пугающе спокоен, что, впрочем, никак не влияло на отношение к нему начальника блока Пола Эджкомба, привыкшего исполнять приговор.");
        firstMovie.setRating(8.9);
        firstMovie.setPrice(134.67);
        firstMovie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMzQyNjA5MF5BMl5BanBnXkFtZTYwOTU2NTY3._V1._SY209_CR0,0,140,209_.jpg");

        Movie secondMovie = new Movie();
        secondMovie.setId(19);
        secondMovie.setNameRu("Молчание ягнят");
        secondMovie.setNameEn("The Silence of the Lambs");
        secondMovie.setYearOfProduction(1990);
        secondMovie.setDescription("Психопат похищает и убивает молодых женщин по всему Среднему Западу Америки. ФБР, уверенное в том, что все преступления совершены одним и тем же человеком, поручает агенту Клариссе Старлинг встретиться с заключенным-маньяком, который мог бы объяснить следствию психологические мотивы серийного убийцы и тем самым вывести на его след.");
        secondMovie.setRating(8.3);
        secondMovie.setPrice(150.5);
        secondMovie.setPicturePath("https://images-na.ssl-images-amazon.com/images/M/MV5BNjNhZTk0ZmEtNjJhMi00YzFlLWE1MmEtYzM1M2ZmMGMwMTU4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1._SY209_CR1,0,140,209_.jpg");

        expectedMovies = new LinkedList<>();
        expectedMovies.add(firstMovie);
        expectedMovies.add(secondMovie);

        //end

        log.info("GetAll test is started");

        //prepare
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);


        //when
        List<Movie> movies = movieDao.getAll(null, null);

        //then
        assertEquals(movies.size(), 25);
        for (Movie expectedMovie : expectedMovies) {
            assertTrue(movies.contains(expectedMovie));
        }

        testDataSource.cleanup();
    }

    @Test
    public void testGetAllSortingByRating() {
        //to remove
        testDataSource.init();
        //end

        log.info("GetAll order by rating test is started");
        //prepare
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);

        //when
        List<Movie> movies = movieDao.getAll("desc", null);

        //then
        assertThat(movies.get(0).getId(), anyOf(is(1L),is(2L)));
        assertThat(movies.get(1).getId(), anyOf(is(1L),is(2L)));
        assertThat(movies.get(2).getId(), anyOf(is(4L),is(13L)));
        assertThat(movies.get(3).getId(), anyOf(is(4L),is(13L)));

        testDataSource.cleanup();
    }

    @Test
    public void testGetAllSortingByPriceAsc() {
        //to remove
        testDataSource.init();
        //end

        log.info("GetAll sorting by price asc test is started");
        //prepare
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);

        //when
        List<Movie> movies = movieDao.getAll(null, "asc");

        //then
        assertEquals(movies.get(0).getId(), 23L);
        assertEquals(movies.get(1).getId(), 20L);
        assertEquals(movies.get(2).getId(), 8L);

        testDataSource.cleanup();
    }

    @Test
    public void testGetAllSortingByPriceDesc() {
        //to remove
        testDataSource.init();
        //end

        log.info("GetAll sorting by price desc test is started");
        //prepare
        MovieDao movieDao = new JdbcMovieDao(jdbcTemplate);

        //when
        List<Movie> movies = movieDao.getAll(null, "desc");

        //then
        assertEquals(movies.get(0).getId(), 3L);
        assertEquals(movies.get(1).getId(), 17L);
        assertEquals(movies.get(2).getId(), 9L);

        testDataSource.cleanup();
    }
}

