package com.boroday.movieland.service.impl;

import com.boroday.movieland.dao.GenreDao;
import com.boroday.movieland.entity.Genre;
import com.boroday.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    public void changeTheFirst() {//for test purpose only
        genreDao.changeTheFirst();
    }
}
