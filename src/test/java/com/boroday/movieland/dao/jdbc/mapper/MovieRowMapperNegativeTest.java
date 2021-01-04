package com.boroday.movieland.dao.jdbc.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperNegativeTest {
    @Test
    public void testMapRow() throws SQLException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn((long) 1);
        when(mockResultSet.getString("nameRu")).thenReturn("Название фильма");
        when(mockResultSet.getString("nameEn")).thenReturn("Name for the movie");
        when(mockResultSet.getInt("yearOfProduction")).thenReturn( 2000);
        when(mockResultSet.getInt("description")).thenReturn(111);
        when(mockResultSet.getDouble("rating")).thenReturn(5.5);
        when(mockResultSet.getDouble("price")).thenReturn(70.00);
        when(mockResultSet.getString("picturePath")).thenReturn("https:// link to the picture of the movie");

        Assertions.assertThrows(RuntimeException.class, () -> {
            movieRowMapper.mapRow(mockResultSet, 1);;
        });
    }
}
