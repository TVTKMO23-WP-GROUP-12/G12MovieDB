package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MovieController {
    
    private final MovieRepository movieRepository;

    public MovieController (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movie")
    public Iterable<Movie> findAllMovies() {
        return this.movieRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movie/{movie_id}")
    public Movie findOneMovie(@PathVariable Integer movieId) {
        return this.movieRepository.findById(movieId).orElse(null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movie")
    public Movie createOneMovie(@RequestBody Movie movie) {
        return this.movieRepository.save(movie);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/movie/{movie_id}")
    public Movie updateOneMovie(@PathVariable Integer movieId, @RequestBody Map<String, Object> updates) {
        Movie movie = this.movieRepository.findById(movieId).orElse(null);
        updates.forEach((key, value) -> {
            switch (key) {
                case "movieId":
                    movie.setmovieId((Integer) value);
                    break;
                case "title":
                    movie.setTitle((String) value);
                    break;
                default:
                    break;
            }
        });
        return this.movieRepository.save(movie);
    }
    @DeleteMapping("/movie/{movie_id}")
    public void deleteOneMovie(@PathVariable Integer movieId) {
        this.movieRepository.deleteById(movieId);
    }
}