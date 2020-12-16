package com.boroday.movieland.web.controller;

import com.boroday.movieland.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(path = "/movies", method = RequestMethod.GET)
    @ResponseBody
    public Map allMovies(HttpServletResponse response) throws IOException {
        log.info("Page for getting all users is requested");
        Map<String, Object> pageVariables = new HashMap<>();
        System.out.println("here");
        pageVariables.put("movies", movieService.getAll());
        return pageVariables;
    }
}
