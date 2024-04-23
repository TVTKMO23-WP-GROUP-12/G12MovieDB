package com.group12.moviedb.controller;


import java.util.List;

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
import com.group12.moviedb.repository.MoviesToWatchRepository;
import com.group12.moviedb.repository.UserRepository;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesToWatch;
import com.group12.moviedb.models.User;

@RestController
public class MoviesToWatchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MoviesToWatchRepository moviesToWatchRepository;

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_to_watch")
    public List<MoviesToWatch> getMoviesToWatch() {
        return moviesToWatchRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_to_watch/user/{user_id}")
    public List<MoviesToWatch> getMoviesWatchedById(@PathVariable("user_id") Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return moviesToWatchRepository.findByUser(user);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_to_watch/movie/{movie_id}")
    public List<MoviesToWatch> getMoviesToWatchByMovieId(@PathVariable("movie_id") Integer movieId) {
        Movie movie = new Movie(movieId, null, movieId);
        return moviesToWatchRepository.findByMovie(movie);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public List<MoviesToWatch> getMoviesToWatchByIds(@PathVariable("user_id") Integer userId, @PathVariable("movie_id") Integer movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        return moviesToWatchRepository.findByUserAndMovie(user, movie);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_to_watch")
    public MoviesToWatch addMoviesToWatch(@RequestBody MoviesToWatch moviesToWatch) {
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_to_watch/user/{user_id}")
    public MoviesToWatch addMoviesToWatchById(@PathVariable("user_id") Integer userId, @RequestBody MoviesToWatch moviesToWatch) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        moviesToWatch.setUser(user);
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/movies_to_watch/user/{user_id}&movie/{movie_id}")
    public MoviesToWatch addOrUpdateMoviesToWatch(@PathVariable("user_id") Integer userId, @PathVariable("movie_id") Integer movieId, @RequestBody MoviesToWatch moviesToWatch) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @PatchMapping("/movies_to_watch/user/{user_id}")
    public MoviesToWatch updateMoviesToWatchByUserId(@PathVariable("user_id") Integer userId,
                                                     @RequestBody MoviesToWatch moviesToWatch) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        moviesToWatch.setUser(user);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @PatchMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable("user_id") Integer userId,
                                                   @PathVariable("movie_id") Integer movieId,
                                                   @RequestBody MoviesToWatch moviesToWatch) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @PatchMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}/note/{note}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable("user_id") Integer userId,
                                                   @PathVariable("movie_id") Integer movieId,
                                                   @PathVariable("note") String note,
                                                   @RequestBody MoviesToWatch moviesToWatch) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return null;
        }
        moviesToWatch.setUser(user);
        moviesToWatch.setMovie(movie);
        moviesToWatch.setNote(note);
        return moviesToWatchRepository.save(moviesToWatch);
    }
    
    @DeleteMapping("/movies_to_watch/user/{user_id}")
    public void deleteMoviesToWatchByUserId(@PathVariable("user_id") Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }
        moviesToWatchRepository.deleteByUser(user);
    }
    
    @DeleteMapping("/movies_to_watch/user/{user_id}/movie/{movie_id}")
    public void deleteMoviesToWatchByIds(@PathVariable("user_id") Integer userId,
                                          @PathVariable("movie_id") Integer movieId) {
        User user = userRepository.findById(userId).orElse(null);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user == null || movie == null) {
            return;
        }
        moviesToWatchRepository.deleteByUserAndMovie(user, movie);
    }
}