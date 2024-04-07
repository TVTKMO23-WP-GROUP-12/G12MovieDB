package com.group12.moviedb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.repository.MoviesWatchedRepository;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.User;

@RestController
public class MoviesWatchedController {

    private final MoviesWatchedRepository moviesWatchedRepository;

    public MoviesWatchedController(MoviesWatchedRepository moviesWatchedRepository) {
        this.moviesWatchedRepository = moviesWatchedRepository;
    }

    @GetMapping("/movies_watched")
    public List<MoviesWatched> getMoviesWatched() {
        return moviesWatchedRepository.findAll();
    }

    @GetMapping("/movies_watched/user/{user_id}")
    public MoviesWatched getMoviesWatchedById(@PathVariable("user_id") int userId) {
        User user = new User();
        user.setId(userId);
        return moviesWatchedRepository.findByUser(user).orElse(null);
    }

    @GetMapping("/movies_watched/movie/{movie_id}")
    public MoviesWatched getMoviesWatchedByMovieId(@PathVariable("movie_id") int movieId) {
        Movie movie = new Movie();
        movie.setId(movieId);
        return moviesWatchedRepository.findByMovie(movie).orElse(null);
    }

    @GetMapping("/movies_watched/user/{user_id}/movie/{movie_id}")
    public MoviesWatched getMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                @PathVariable("movie_id") int movieId) {
        User user = new User();
        user.setId(userId);
        Movie movie = new Movie();
        movie.setId(movieId);
        return moviesWatchedRepository.findByUserAndMovie(user, movie);
    }

    @PostMapping("/movies_watched")
    public MoviesWatched addMoviesWatched(@RequestBody MoviesWatched moviesWatched) {
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PostMapping("/movies_watched/user/{user_id}")
    public MoviesWatched addMoviesWatchedById(@PathVariable("user_id") int userId,
                                              @RequestBody MoviesWatched moviesWatched) {
        User user = new User();
        user.setId(userId);
        moviesWatched.setUser(user);
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PostMapping("/movies_watched/user/{user_id}&movie/{movie_id}")
    public MoviesWatched addOrUpdateMoviesWatched(@PathVariable("user_id") int userId,
                                                  @PathVariable("movie_id") int movieId,
                                                  @RequestBody MoviesWatched moviesWatched) {
        User user = new User();
        user.setId(userId);
        Movie movie = new Movie();
        movie.setId(movieId);
        MoviesWatched existingRecord = moviesWatchedRepository.findByUserAndMovie(user, movie);
        if (existingRecord != null) {
            existingRecord.setNote(moviesWatched.getNote());
            return moviesWatchedRepository.save(existingRecord);
        } else {
            moviesWatched.setUser(user);
            moviesWatched.setMovie(movie);
            return moviesWatchedRepository.save(moviesWatched);
        }
    }

    @PatchMapping("/movies_watched/user/{user_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                  @RequestBody MoviesWatched moviesWatched) {
        User user = new User();
        user.setId(userId);
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PatchMapping("/movies_watched/user/{user_id}&movie/{movie_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                  @PathVariable("movie_id") int movieId,
                                                  @RequestBody MoviesWatched moviesWatched) {
        User user = new User();
        user.setId(userId);
        Movie movie = new Movie();
        movie.setId(movieId);
        moviesWatched.setUser(user);
        moviesWatched.setMovie(movie);
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PatchMapping("/movies_watched/user/{user_id}&movie/{movie_id}&note/{note}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                  @PathVariable("movie_id") int movieId,
                                                  @PathVariable("note") String note,
                                                  @RequestBody MoviesWatched moviesWatched) {
        User user = new User();
        user.setId(userId);
        Movie movie = new Movie();
        movie.setId(movieId);
        moviesWatched.setUser(user);
        moviesWatched.setMovie(movie);
        moviesWatched.setNote(note);
        return moviesWatchedRepository.save(moviesWatched);
    }

    @DeleteMapping("/movies_watched/user/{user_id}")
    public void deleteMoviesWatchedById(@PathVariable("user_id") int userId) {
        User user = new User();
        user.setId(userId);
        moviesWatchedRepository.deleteByUser(user);
    }

    @DeleteMapping("/movies_watched/user/{user_id}&movie/{movie_id}")
    public void deleteMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                         @PathVariable("movie_id") int movieId) {
        User user = new User();
        user.setId(userId);
        Movie movie = new Movie();
        movie.setId(movieId);
        moviesWatchedRepository.deleteByUserAndMovie(user, movie);
    }
}
