package com.boroday.movieland.service.impl;

import com.boroday.movieland.dao.MovieDao;
import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultMovieService implements MovieService {
    private final MovieDao movieDao;

    @Override
    public List<Movie> getAll(String rating, String price) {
        return movieDao.getAll(rating, price);
    }

    @Override
    public  List<Movie> getRandom(){
        return movieDao.getRandom();
    }
}
