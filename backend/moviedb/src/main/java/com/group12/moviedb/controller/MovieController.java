package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.TMDBAPI.movies.MovieCreditsService;
import com.group12.moviedb.TMDBAPI.movies.MovieDetailsService;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MovieController {
    @Autowired
    private MovieDetailsService movieDetailsService;
    @Autowired
    private MovieCreditsService movieCreditsService;

    private final MovieRepository movieRepository;

    public MovieController (MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/public/movie")
    public Iterable<Movie> findAllMovies() {
        return this.movieRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/public/movie/{movie_id}")
    public Movie findOneMovie(@PathVariable("movie_id") Integer movieId) {
        return this.movieRepository.findById(movieId).orElse(null);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/public/tmdb/{tmdbId}")
    public String getMovieDetails(@PathVariable("tmdbId") Integer tmdbId) {
        return movieDetailsService.getMovieDetails(tmdbId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/public/movie/tmdb/{tmdbId}")
    public Movie findOneMovieByTmdbId(@PathVariable("tmdbId") Integer tmdbId) {
        return this.movieRepository.findByTmdbId(tmdbId).orElse(null);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/public/movie/tmdb/{tmdbId}/credits")
    public String getMovieCredits(@PathVariable String tmdbId) {
        return movieCreditsService.getMovieCredits(tmdbId);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/public/movie")
    public Movie createOneMovie(@RequestBody Movie movie) {
        return this.movieRepository.save(movie);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/public/movie/{tmdbId}/{title}")
    public Movie createOneMovieWithTmdbIdAndTitle(@PathVariable Integer tmdbId, @PathVariable String title) {
        Optional<Movie> existingMovie = this.movieRepository.findByTmdbId(tmdbId);
        if (existingMovie.isPresent()) {
            return existingMovie.get();
        } else {
            Movie movie = new Movie();
            movie.setTmdbId(tmdbId);
            movie.setTitle(title);
            return this.movieRepository.save(movie);
        }
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
                case "tmdbId":
                    movie.setTmdbId((Integer) value);
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