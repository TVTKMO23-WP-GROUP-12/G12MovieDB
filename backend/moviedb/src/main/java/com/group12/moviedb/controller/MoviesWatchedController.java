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
import com.group12.moviedb.models.MoviesWatched;

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

    @GetMapping("/movies_watched/user={user_id}")
    public MoviesWatched getMoviesWatchedById(@PathVariable int userId) {
        return moviesWatchedRepository.findByUserId(userId).orElse(null);
    }

    @GetMapping("/movies_watched/movie={movie_id}")
    public MoviesWatched getMoviesWatchedByMovieId(@PathVariable int movieId) {
        return moviesWatchedRepository.findByMovieId(movieId).orElse(null);
    }

    @GetMapping("/movies_watched/user={user_id}/movie={movie_id}")
    public MoviesWatched getMoviesWatchedByIds(@PathVariable int userId, @PathVariable int movieId) {
        return moviesWatchedRepository.findByIds(userId, movieId).orElse(null);
    }

    @PostMapping("/movies_watched")
    public MoviesWatched addMoviesWatched(MoviesWatched moviesWatched) {
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PostMapping("/movies_watched/user={user_id}")
    public MoviesWatched addMoviesWatchedById(@PathVariable int userId) {
        return moviesWatchedRepository.saveById(userId);
    }

    @PostMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public MoviesWatched addOrUpdateMoviesWatchedByIds(@PathVariable int userId, @PathVariable int movieId, @RequestBody MoviesWatched moviesWatched) {
        moviesWatched.setUserId(userId);
        moviesWatched.setMovieId(movieId);
        return moviesWatchedRepository.save(moviesWatched);
    }

    @PatchMapping("/movies_watched/user={user_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable int userId) {
        return moviesWatchedRepository.updateByUserId(userId);
    }

    @PatchMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable int userId, @PathVariable int movieId) {
        return moviesWatchedRepository.updateByIds(userId, movieId);
    }

    @PatchMapping("/movies_watched/user={user_id}&movie={movie_id}&note={note}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable int userId, @PathVariable int movieId, @PathVariable String note) {
        return moviesWatchedRepository.updateNoteByIds(userId, movieId, note);
    }

    @DeleteMapping("/movies_watched/user={user_id}")
    public void deleteMoviesWatchedById(@PathVariable int userId) {
        moviesWatchedRepository.deleteByUserId(userId);
    }

    @DeleteMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public void deleteMoviesWatchedByIds(@PathVariable int userId, @PathVariable int movieId) {
        moviesWatchedRepository.deleteByIds(userId, movieId);
    }
}
