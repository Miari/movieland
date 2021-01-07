package com.boroday.movieland.service;

import com.boroday.movieland.entity.Movie;
import java.util.List;

public interface MovieService {
    List<Movie> getAll();

    List<Movie> getRandom();
}
