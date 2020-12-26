package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Genre;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        GenreRowMapper genreRowMapper = new GenreRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn((long) 1);
        when(mockResultSet.getString("name")).thenReturn("NameOfGenre");

        Genre testGenre = genreRowMapper.mapRow(mockResultSet, 1);

        assertNotNull(testGenre);
        assertEquals(1, testGenre.getId());
        assertEquals("NameOfGenre", testGenre.getName());

    }
}
