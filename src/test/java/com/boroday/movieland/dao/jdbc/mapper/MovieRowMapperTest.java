package com.boroday.movieland.dao.jdbc.mapper;

import com.boroday.movieland.entity.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {
    @Test
    public void testMapRow() throws SQLException {
        MovieRowMapper movieRowMapper = new MovieRowMapper();
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getLong("id")).thenReturn((long) 1);
        when(mockResultSet.getString("nameRu")).thenReturn("Название фильма");
        when(mockResultSet.getString("nameEn")).thenReturn("Name for the movie");
        when(mockResultSet.getInt("yearOfProduction")).thenReturn( 2000);
        when(mockResultSet.getString("description")).thenReturn("Description of the movie");
        when(mockResultSet.getDouble("rating")).thenReturn(5.5);
        when(mockResultSet.getDouble("price")).thenReturn(70.00);
        when(mockResultSet.getString("picturePath")).thenReturn("https:// link to the picture of the movie");



        Movie testMovie = movieRowMapper.mapRow(mockResultSet, 1);

        assertNotNull(testMovie);
        assertEquals(1, testMovie.getId());
        assertEquals("Название фильма", testMovie.getNameRu());
        assertEquals("Name for the movie", testMovie.getNameEn());
        assertEquals(2000, testMovie.getYearOfProduction());
        assertEquals("Description of the movie", testMovie.getDescription());
        assertEquals(5.5, testMovie.getRating());
        assertEquals(70.00, testMovie.getPrice());
        assertEquals("https:// link to the picture of the movie", testMovie.getPicturePath());

    }
}
