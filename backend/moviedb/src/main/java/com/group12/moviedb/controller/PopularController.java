package com.group12.moviedb.controller;

import com.group12.moviedb.TMDBAPI.movieLists.PopularService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies/popular")
public class PopularController {

    @Autowired
    private PopularService popularService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getPopularMovies() throws IOException {
        return popularService.getPopularMovies();
    }
}
