package com.boroday.movieland.web.controller;

import com.boroday.movieland.entity.Movie;
import com.boroday.movieland.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "api/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> allMovies(@RequestParam(value = "rating", required = false) String rating,
                                 @RequestParam(value = "price", required = false) String price) throws IOException {
        log.info("Page for getting all movies is requested");
        List<Movie> movieList = movieService.getAll(rating, price);
        return movieList;
    }

    @GetMapping("/movies/random")
    public List<Movie> RandomMovies() throws IOException {
        log.info("Page for getting all movies is requested");
        List<Movie> movieList = movieService.getRandom();
        return movieList;
    }
}
