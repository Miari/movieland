package com.boroday.movieland.service;

import com.boroday.movieland.entity.Movie;
import java.util.List;

public interface MovieGenreService {
    List<Movie> getMoviesByGenre(int genreId);
}
