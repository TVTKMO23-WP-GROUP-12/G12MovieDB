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
import com.group12.moviedb.models.MoviesToWatch;

@RestController
public class MoviesToWatchController {

    private final MoviesToWatchRepository moviesToWatchRepository;

    public MoviesToWatchController(MoviesToWatchRepository moviesToWatchRepository) {
        this.moviesToWatchRepository = moviesToWatchRepository;
    }

    @GetMapping("/movies_watched")
    public List<MoviesToWatch> getMoviesToWatch() {
        return moviesToWatchRepository.findAll();
    }

    @GetMapping("/movies_watched/user={user_id}")
    public MoviesToWatch getMoviesToWatchById(@PathVariable int userId) {
        return moviesToWatchRepository.findByUserId(userId).orElse(null);
    }

    @GetMapping("/movies_watched/movie={movie_id}")
    public MoviesToWatch getMoviesToWatchByMovieId(@PathVariable int movieId) {
        return moviesToWatchRepository.findByMovieId(movieId).orElse(null);
    }

    @GetMapping("/movies_watched/user={user_id}/movie={movie_id}")
    public MoviesToWatch getMoviesToWatchByIds(@PathVariable int userId, @PathVariable int movieId) {
        return moviesToWatchRepository.findByIds(userId, movieId).orElse(null);
    }

    @PostMapping("/movies_watched")
    public MoviesToWatch addMoviesToWatch(MoviesToWatch moviesToWatch) {
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @PostMapping("/movies_watched/user={user_id}")
    public MoviesToWatch addMoviesToWatchById(@PathVariable int userId) {
        return moviesToWatchRepository.saveById(userId);
    }

    @PostMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public MoviesToWatch addOrUpdateMoviesToWatchByIds(@PathVariable int userId, @PathVariable int movieId, @RequestBody MoviesToWatch moviesToWatch) {
        moviesToWatch.setUserId(userId);
        moviesToWatch.setMovieId(movieId);
        return moviesToWatchRepository.save(moviesToWatch);
    }

    @PatchMapping("/movies_watched/user={user_id}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable int userId) {
        return moviesToWatchRepository.updateByUserId(userId);
    }

    @PatchMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable int userId, @PathVariable int movieId) {
        return moviesToWatchRepository.updateByIds(userId, movieId);
    }

    @PatchMapping("/movies_watched/user={user_id}&movie={movie_id}&note={note}")
    public MoviesToWatch updateMoviesToWatchByIds(@PathVariable int userId, @PathVariable int movieId, @PathVariable String note) {
        return moviesToWatchRepository.updateNoteByIds(userId, movieId, note);
    }

    @DeleteMapping("/movies_watched/user={user_id}")
    public void deleteMoviesToWatchById(@PathVariable int userId) {
        moviesToWatchRepository.deleteByUserId(userId);
    }

    @DeleteMapping("/movies_watched/user={user_id}&movie={movie_id}")
    public void deleteMoviesToWatchByIds(@PathVariable int userId, @PathVariable int movieId) {
        moviesToWatchRepository.deleteByIds(userId, movieId);
    }
}
