package com.boroday.movieland.dao;

import com.boroday.movieland.entity.Movie;

import java.util.List;

public interface MovieGenreDao {
    List<Movie> getMoviesByGenre(int genreId);
}
