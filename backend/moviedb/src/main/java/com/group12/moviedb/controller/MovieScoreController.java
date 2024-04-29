package com.group12.moviedb.controller;


import java.time.LocalDateTime;
import java.util.Map;
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

import com.group12.moviedb.models.MovieScore;
import com.group12.moviedb.repository.MovieScoreRepository;

@RestController
public class MovieScoreController {
    
    @Autowired
    private final MovieScoreRepository movieScoreRepository;

    public MovieScoreController(MovieScoreRepository movieScoreRepository) {
        this.movieScoreRepository = movieScoreRepository;
    }

    @GetMapping("/movie_scores")
    public Iterable<MovieScore> findAllMovieScores() {
        return this.movieScoreRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movie_scores/movie={movie_score_id}")
    public ResponseEntity<MovieScore> findOneMovieScoreByMovieId(@PathVariable("movieScoreId") Integer movieScoreId) {
        MovieScore movieScore = this.movieScoreRepository.findByMovieId(movieScoreId);
        if (movieScore != null) {
            return ResponseEntity.ok(movieScore);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/movie_scores/{movie_score_id}")
    public ResponseEntity<MovieScore> findOneMovieScoreById(@PathVariable("movie_score_id") Integer movieScoreId) {
        Optional<MovieScore> optionalMovieScore = this.movieScoreRepository.findById(movieScoreId);
        return optionalMovieScore.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/movie_scores")
    public MovieScore addOneMovieScore(@RequestBody MovieScore movieScore) {
        return this.movieScoreRepository.save(movieScore);
    }

    @PatchMapping("/movie_scores/{movie_score_id}")
    public MovieScore updateOneMovieScore(@PathVariable Integer movieScoreId, @RequestBody Map<String, Object> updates) {
        MovieScore movieScore = this.movieScoreRepository.findById(movieScoreId).orElse(null);
        updates.forEach((key, value) -> {
            switch (key) {
                case "movieScoreId":
                    movieScore.setMovieScoreId((Integer) value);
                    break;
                case "score":
                    movieScore.setScore((int) value);
                    break;
                case "created_at":
                    movieScore.setCreatedAt((LocalDateTime) value);
                    break;
                case "updated_at":
                    movieScore.setUpdatedAt((LocalDateTime) value);
                    break;
            }
        });
        return this.movieScoreRepository.save(movieScore);
    }
    
    @DeleteMapping("/movie_scores/{movie_score_id}")
    public void deleteOneMovieScore(@PathVariable Integer movieScoreId) {
        this.movieScoreRepository.deleteById(movieScoreId);
    }
}
