package com.boroday.movieland.dao;

import com.boroday.movieland.entity.Genre;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface GenreDao {
    List<Genre> getAll();

    void changeTheFirst(); //for test purpose only
}
