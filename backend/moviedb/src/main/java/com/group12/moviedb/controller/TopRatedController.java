package com.group12.moviedb.controller;

import com.group12.moviedb.TMDBAPI.movieLists.TopRatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/movies/top-rated")
public class TopRatedController {

    private final TopRatedService topRatedService;

    @Autowired
    public TopRatedController(TopRatedService topRatedService) {
        this.topRatedService = topRatedService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<String> getTopRatedMovies() {
        try {
            String topRatedMovies = topRatedService.getTopRatedMovies();
            return new ResponseEntity<>(topRatedMovies, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while fetching top rated movies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

