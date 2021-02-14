package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.MovieGenreDao;
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
public class JdbcMovieGenreDao implements MovieGenreDao {

    private static final String GET_MOVIES_BY_GENRE = "select movies.id, nameRu, nameEn, yearOfProduction, description, rating, price, picturePath " +
            "from movies, genres, genre_movie " +
            "where genre_movie.id_movie = movies.id AND genre_movie.id_genre = genres.id AND genres.id = ?";

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> getMoviesByGenre(int genreId) { //for test purpose only
        log.info("Getting movies by genre id={} from DB", genreId);
        Object[] args = new Object[]{genreId};
        List<Movie> listOfMovies = jdbcTemplate.query(GET_MOVIES_BY_GENRE, args, MOVIE_ROW_MAPPER);
        if (listOfMovies.size() > 0) {
            return listOfMovies;
        } else {
            log.info("There is no movies with genreId {} in DB", genreId);
            return null;
        }
    }
}


