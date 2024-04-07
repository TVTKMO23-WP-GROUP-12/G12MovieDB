package com.group12.moviedb.controller;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.MovieScore;
import com.group12.moviedb.repository.MovieScoreRepository;

@RestController
public class MovieScoreController {
    
    private final MovieScoreRepository movieScoreRepository;

    public MovieScoreController(MovieScoreRepository movieScoreRepository) {
        this.movieScoreRepository = movieScoreRepository;
    }

    @GetMapping("/movie_scores")
    public Iterable<MovieScore> findAllMovieScores() {
        return this.movieScoreRepository.findAll();
    }

    @GetMapping("/movie_scores/movie={movie_id}")
    public ResponseEntity<MovieScore> findOneMovieScoreByMovieId(@PathVariable("movie_id") Integer movie_id) {
        MovieScore movieScore = this.movieScoreRepository.findByMovieId(movie_id);
        if (movieScore != null) {
            return ResponseEntity.ok(movieScore);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/movie_scores/{movie_score_id}")
    public ResponseEntity<MovieScore> findOneMovieScoreById(@PathVariable("movie_score_id") Integer movie_score_id) {
        Optional<MovieScore> optionalMovieScore = this.movieScoreRepository.findById(movie_score_id);
        return optionalMovieScore.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/movie_scores")
    public MovieScore addOneMovieScore(@RequestBody MovieScore movieScore) {
        return this.movieScoreRepository.save(movieScore);
    }

    @PatchMapping("/movie_scores/{id}")
    public MovieScore updateOneMovieScore(@PathVariable int movie_score_id, @RequestBody Map<String, Object> updates) {
        MovieScore movieScore = this.movieScoreRepository.findById(movie_score_id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "movie_score_id":
                    movieScore.setMovieScoreId((int) value);
                    break;
                case "score":
                    movieScore.setScore((int) value);
                    break;
                case "created_at":
                    movieScore.setCreatedAt((Date) value);
                    break;
                case "updated_at":
                    movieScore.setUpdatedAt((Date) value);
                    break;
            }
        });
        return this.movieScoreRepository.save(movieScore);
    }
    
    @DeleteMapping("/movie_scores/{id}")
    public void deleteOneMovieScore(@PathVariable int movie_score_id) {
        this.movieScoreRepository.deleteById(movie_score_id);
    }
}
