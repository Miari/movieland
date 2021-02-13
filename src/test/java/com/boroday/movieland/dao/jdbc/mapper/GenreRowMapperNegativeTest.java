package com.boroday.movieland.dao.jdbc.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperNegativeTest {
    @Test
    public void testMapRowIncorrectName() throws SQLException {
        GenreRowMapper genreRowMapper = new GenreRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn((long) 1);
        when(mockResultSet.getInt("name")).thenReturn(111);

        Assertions.assertThrows(RuntimeException.class, () -> {
            genreRowMapper.mapRow(mockResultSet, 1);
        });
    }

    @Test
    public void testMapRowIncorrectId() throws SQLException {
        GenreRowMapper genreRowMapper = new GenreRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("TestName");

        Assertions.assertThrows(RuntimeException.class, () -> {
            genreRowMapper.mapRow(mockResultSet, 1);
        });
    }
}
