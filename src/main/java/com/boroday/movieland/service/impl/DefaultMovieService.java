package com.boroday.movieland.service.impl;

import com.boroday.movieland.dao.MovieDao;
import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.service.MovieService;

import java.util.List;

public class DefaultMovieService implements MovieService {
    private MovieDao movieDao;

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
