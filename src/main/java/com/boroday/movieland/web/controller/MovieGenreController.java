package com.boroday.movieland.web.controller;

import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.service.MovieGenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "api/v1")
public class MovieGenreController {

    @Autowired
    private MovieGenreService movieGenreService;

    @GetMapping("/movie/genre")
    public List<Movie> MoviesByGenre(@RequestParam(value = "id") int genreId) throws IOException {
        log.info("Page for getting movies by genre {} is requested", genreId);
        List<Movie> movieList = movieGenreService.getMoviesByGenre(genreId);
        return movieList;
    }
}
