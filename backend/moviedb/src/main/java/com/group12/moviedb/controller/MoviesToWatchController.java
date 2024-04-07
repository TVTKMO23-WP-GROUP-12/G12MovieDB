package com.group12.moviedb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.repository.MoviesToWatchRepository;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.User;

@RestController
public class MoviesToWatchController {

    private final MoviesToWatchRepository moviesToWatchRepository;

    public MoviesToWatchController(MoviesToWatchRepository moviesToWatchRepository) {
        this.moviesToWatchRepository = moviesToWatchRepository;
    }

    @GetMapping("/movies_to_watch")
    public List<MoviesToWatch> getMoviesToWatch() {
        return moviesToWatchRepository.findAll();
    }

    @GetMapping("/movies_to_watch/user/{user_id}")
    public MoviesToWatch getMoviesToWatchById(@PathVariable("user_id") int userId) {
        User user = new User(); // Create a User object using userId
        return moviesToWatchRepository.findByUser(user).orElse(null);
    }

    @GetMapping("/movies_to_watch/movie/{movie_id}")
    public MoviesToWatch getMoviesToWatchByMovieId(@PathVariable("movie_id") int movieId) {
        Movie movie = new Movie(); // Create a Movie object using movieId
        return moviesToWatchRepository.findByMovie(movie).orElse(null);
    }

    @GetMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public MoviesToWatch getMoviesToWatchByIds(@PathVariable("user_id") int userId, @PathVariable("movie_id") int movieId) {
        User user = new User(); // Create a User object using userId
        Movie movie = new Movie(); // Create a Movie object using movieId
        return moviesToWatchRepository.findByUserAndMovie(user, movie).orElse(null);
    }

    @PostMapping("/movies_to_watch")
    public MoviesToWatch addMoviesToWatch(@RequestBody MoviesToWatch moviesToWatch) {
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @PostMapping("/movies_to_watch/user/{user_id}")
    public MoviesToWatch addMoviesToWatchById(@PathVariable("user_id") int userId, @RequestBody MoviesToWatch moviesToWatch) {
        User user = new User(); // Create a User object using userId
        moviesToWatch.setUser(user);
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @PostMapping("/movies_to_watch/user/{user_id}&movie/{movie_id}")
    public MoviesToWatch addOrUpdateMoviesToWatch(@PathVariable("user_id") int userId, @PathVariable("movie_id") int movieId, @RequestBody MoviesToWatch moviesToWatch) {
        User user = new User(); // Create a User object using userId
        Movie movie = new Movie(); // Create a Movie object using movieId
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    @PatchMapping("/movies_to_watch/user/{user_id}")
    public MoviesToWatch updateMoviesToWatchByUserId(@PathVariable("user_id") int userId,
                                                     @RequestBody MoviesToWatch moviesToWatch) {
        User user = new User(); // Create a User object using userId
        moviesToWatch.setUser(user);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @PatchMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable("user_id") int userId,
                                                   @PathVariable("movie_id") int movieId,
                                                   @RequestBody MoviesToWatch moviesToWatch) {
        User user = new User(); // Create a User object using userId
        Movie movie = new Movie(); // Create a Movie object using movieId
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @PatchMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}/note/{note}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable("user_id") int userId,
                                                   @PathVariable("movie_id") int movieId,
                                                   @PathVariable("note") String note,
                                                   @RequestBody MoviesToWatch moviesToWatch) {
        User user = new User(); // Create a User object using userId
        Movie movie = new Movie(); // Create a Movie object using movieId
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        moviesToWatch.setNote(note);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @DeleteMapping("/movies_to_watch/user/{user_id}")
    public void deleteMoviesToWatchByUserId(@PathVariable("user_id") int userId) {
        User user = new User(); // Create a User object using userId
        moviesToWatchRepository.deleteByUser(user);
    }
    
    @DeleteMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public void deleteMoviesToWatchByIds(@PathVariable("user_id") int userId,
                                          @PathVariable("movie_id") int movieId) {
        User user = new User(); // Create a User object using userId
        Movie movie = new Movie(); // Create a Movie object using movieId
        moviesToWatchRepository.deleteByUserAndMovie(user, movie);
    }
}
    