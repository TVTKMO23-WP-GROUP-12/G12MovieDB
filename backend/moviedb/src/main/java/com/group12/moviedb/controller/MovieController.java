package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @GetMapping("/movie/{id}")
    public Optional<Movie> findOneMovie(@PathVariable int id) {
        return this.movieRepository.findById(id);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/movie/{id}")
    public Movie updateOneMovie(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Optional<Movie> movieOptional = this.movieRepository.findById(id);
    
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "id":
                        // You generally shouldn't update the ID of an entity
                        // movie.setId((int) value);
                        break;
                    case "title":
                        movie.setTitle((String) value);
                        break;
                    default:
                        // 
                        break;
                }
            });
            return this.movieRepository.save(movie);
        } else {
            throw new NoSuchElementException("Movie not found");
        }
    }
    @DeleteMapping("/movie/{id}")
    public void deleteOneMovie(@PathVariable int id) {
        this.movieRepository.deleteById(id);
    }
}