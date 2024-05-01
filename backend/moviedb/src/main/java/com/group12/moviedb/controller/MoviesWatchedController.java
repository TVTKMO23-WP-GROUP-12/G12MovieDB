package com.group12.moviedb.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.User;

@RestController
public class MoviesWatchedController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MoviesWatchedRepository moviesWatchedRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched")
    public List<MoviesWatched> getMoviesWatched() {
        return moviesWatchedRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/user/{user_id}")
    public List<MoviesWatched> getMoviesWatchedById(@PathVariable("user_id") Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return moviesWatchedRepository.findByUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/movie/{movie_id}")
    public List<MoviesWatched> getMoviesWatchedByMovieId(@PathVariable("movie_id") Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return null;
        }
        return moviesWatchedRepository.findByMovie(movie);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_watched/{user_id}/{movie_id}")
    public List<MoviesWatched> getMoviesWatchedByIds(@PathVariable("user_id") Integer userId, @PathVariable("movie_id") Integer movieId) {
        User user = userRepository.findById(userId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        return moviesWatchedRepository.findByUserAndMovie(user, movie);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched")
    public MoviesWatched addMoviesWatched(@RequestBody Map<String, Object> moviesWatched) {
        User user = userRepository.findById((Integer) moviesWatched.get("userId")).orElseThrow();
        Movie movie = movieRepository.findById((Integer) moviesWatched.get("movieId")).orElseThrow();
        String note = (String) moviesWatched.get("note");

        MoviesWatched newMoviesWatched = new MoviesWatched(user, movie, note, LocalDateTime.now(), LocalDateTime.now());

        return this.moviesWatchedRepository.save(newMoviesWatched);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched/user/{user_id}")
    public MoviesWatched addMoviesWatchedById(@PathVariable("user_id") Integer UserId, @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(UserId).orElse(null);
        if (user == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        return moviesWatchedRepository.save(MoviesWatched);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_watched/user/{user_id}&movie/{movie_id}")
    public MoviesWatched addOrUpdateMoviesWatched(@PathVariable("user_id") Integer UserId, @PathVariable("movie_id") Integer movieId, @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(UserId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        return moviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}")
    public MoviesWatched updateMoviesWatchedByUserId(@PathVariable("user_id") Integer userId,
                                                     @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        return moviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}/movie/{movie_id}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") Integer userId,
                                                   @PathVariable("movie_id") Integer movieId,
                                                   @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);

        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        return moviesWatchedRepository.save(MoviesWatched);
    }
    
    @PatchMapping("/movies_watched/user/{user_id}/movie/{movie_id}/note/{note}")
    public MoviesWatched updateMoviesWatchedByIds(@PathVariable("user_id") Integer userId,
                                                   @PathVariable("movie_id") Integer movieId,
                                                   @PathVariable("note") String note,
                                                   @RequestBody MoviesWatched MoviesWatched) {
        User user = userRepository.findById(userId).orElse(null);

        Movie movie = movieRepository.findById(movieId).orElse(null);

        if (user == null || movie == null) {
            return null;
        }
        MoviesWatched.setUser(user);
        MoviesWatched.setMovie(movie);
        MoviesWatched.setNote(note);
        return moviesWatchedRepository.save(MoviesWatched);
    }
    
    @DeleteMapping("/movies_watched/user/{user_id}")
    public void deleteMoviesWatchedByUserId(@PathVariable("user_id") Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        moviesWatchedRepository.deleteByUser(user);
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping("/movies_watched/{user_id}/{movie_id}")
    public ResponseEntity<Void> deleteOneMovieWatched(@PathVariable("user_id") Integer userId, @PathVariable("movie_id") Integer movieId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        Movie movie = movieRepository.findById(movieId)
            .orElseThrow(() -> new NoSuchElementException("Movie not found"));
        LocalDateTime now = LocalDateTime.now();
        MoviesWatched movieWatched = new MoviesWatched(user, movie, "", now, now);
        moviesWatchedRepository.delete(movieWatched);
        return ResponseEntity.noContent().build();
    }
}