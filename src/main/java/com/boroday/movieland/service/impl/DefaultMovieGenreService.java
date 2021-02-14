package com.boroday.movieland.service.impl;

import com.boroday.movieland.dao.MovieGenreDao;
import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.service.MovieGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMovieGenreService implements MovieGenreService {
    private final MovieGenreDao movieGenreDao;

    @Override
    public List<Movie> getMoviesByGenre(int genreId) {
        return movieGenreDao.getMoviesByGenre(genreId);
    }
}
