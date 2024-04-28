package com.group12.moviedb.controller;

import com.group12.moviedb.TMDBAPI.movieLists.UpcomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/movies/upcoming")
public class UpcomingController {

    private final UpcomingService upcomingService;

    @Autowired
    public UpcomingController(UpcomingService upcomingService) {
        this.upcomingService = upcomingService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public ResponseEntity<String> getUpcomingMovies() {
        try {
            String upcomingMovies = upcomingService.getUpcomingMovies();
            return new ResponseEntity<>(upcomingMovies, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred while fetching upcoming movies", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
