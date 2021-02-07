package com.boroday.movieland.web.controller;

import com.boroday.movieland.entity.Genre;
import com.boroday.movieland.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "api/v1")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/genres")
    public List<Genre> listOfGenres () {
        log.info("Page for getting all genres is requested");
        List<Genre> genreList = genreService.getAll();
        return genreList;
    }
}
