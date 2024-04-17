package com.group12.moviedb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.repository.MovieRepository;
import com.group12.moviedb.repository.MoviesWatchedRepository;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.User;

@RestController
public class MoviesWatchedController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MoviesWatchedRepository MoviesWatchedRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched")
    public List<MoviesWatched> getMoviesWatched() {
        return MoviesWatchedRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/user/{user_id}")
    public Optional<MoviesWatched> getMoviesWatchedById(@PathVariable("user_id") int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return MoviesWatchedRepository.findByUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/movie/{movie_id}")
    public Optional<MoviesWatched> getMoviesWatchedByMovieId(@PathVariable("movie_id") int movieId) {
        Movie movie = new Movie();
        return MoviesWatchedRepository.findByMovie(movie);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/user/{user_id}/movie/{movie_id}")
    public List<MoviesWatched> getMoviesWatchedByIds(@PathVariable("user_id") int userId, @PathVariable("movie_id") int movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (user == null || movie == null) {
            return null;
        }
        return MoviesWatchedRepository.findByUserAndMovie(user, movie);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched")
    public MoviesWatched addMoviesWatched(@RequestBody MoviesWatched MoviesWatched) {
        return MoviesWatchedRepository.save(MoviesWatched);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched/user/{user_id}")
    public MoviesWatched addMoviesWatchedById(@PathVariable("user_id") int userId, @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        return MoviesWatchedRepository.save(MoviesWatched);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched/user/{user_id}&movie/{movie_id}")
    public MoviesWatched addOrUpdateMoviesWatched(@PathVariable("user_id") int userId, @PathVariable("movie_id") int movieId, @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        return MoviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}")
    public MoviesWatched updateMoviesWatchedByUserId(@PathVariable("user_id") int userId,
                                                     @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        return MoviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}/movie/{movie_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                   @PathVariable("movie_id") int movieId,
                                                   @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        return MoviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}/movie/{movie_id}/note/{note}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                                   @PathVariable("movie_id") int movieId,
                                                   @PathVariable("note") String note,
                                                   @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        MoviesWatched.setNote(note);
        return MoviesWatchedRepository.save(MoviesWatched);
    }
    
    @DeleteMapping("/movies_watched/user/{user_id}")
    public void deleteMoviesWatchedByUserId(@PathVariable("user_id") int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        MoviesWatchedRepository.deleteByUser(user);
    }
    
    @DeleteMapping("/movies_watched/user/{user_id}/movie/{movie_id}")
    public void deleteMoviesWatchedByIds(@PathVariable("user_id") int userId,
                                          @PathVariable("movie_id") int movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (user == null || movie == null) {
            return;
        }
        MoviesWatchedRepository.deleteByUserAndMovie(user, movie);
    }
}