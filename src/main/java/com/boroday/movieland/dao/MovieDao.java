package com.boroday.movieland.dao;

import com.boroday.movieland.entity.Movie;

import java.util.List;

public interface MovieDao {

    List<Movie> getAll(String rating, String price);

    List<Movie> getRandom();
}
