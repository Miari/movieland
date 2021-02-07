package com.boroday.movieland.dao.jdbc;

import com.boroday.movieland.dao.GenreDao;
import com.boroday.movieland.dao.jdbc.mapper.GenreRowMapper;
import com.boroday.movieland.entity.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JdbcGenreDao implements GenreDao {
    private static final String GET_ALL_GENRES = "select id, name from genres";
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("genres")
    public List<Genre> getAll() {
        log.info("Getting all genres from DB");
        return jdbcTemplate.query(GET_ALL_GENRES, GENRE_ROW_MAPPER);
    }
}
