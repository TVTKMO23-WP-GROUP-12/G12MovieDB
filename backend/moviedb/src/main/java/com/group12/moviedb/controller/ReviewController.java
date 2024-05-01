package com.group12.moviedb.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.group12.moviedb.models.Review;
import com.group12.moviedb.models.User;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MovieScore;
import com.group12.moviedb.repository.MovieRepository;
import com.group12.moviedb.repository.MovieScoreRepository;
import com.group12.moviedb.repository.ReviewRepository;
import com.group12.moviedb.repository.UserRepository;

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


@RestController
public class ReviewController {
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final MovieRepository movieRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MovieScoreRepository movieScoreRepository;

    public ReviewController(ReviewRepository reviewRepository, 
                            MovieRepository movieRepository, 
                            UserRepository userRepository, 
                            MovieScoreRepository movieScoreRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.movieScoreRepository = movieScoreRepository;
    }

    @GetMapping("/review")
    public Iterable<Review> findAllreview() {
        return this.reviewRepository.findAll();
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/public/review/movie={movie_id}")
    public List<Review> findReviewsByMovieId(@PathVariable("movie_id") Integer movieId) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie != null) {
            return reviewRepository.findByMovie(movie);
        } else {
            return Collections.emptyList();
        }
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/review/user={user_id}")
    public ResponseEntity<List<Map<String, Object>>> findReviewsByUserId(@PathVariable("user_id") Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Review> reviews = reviewRepository.findByUserId(userId);
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Review review : reviews) {
                Map<String, Object> response = new HashMap<>();
                response.put("review", review);
                response.put("movie", review.getMovie()); // get the Movie from the Review
                responseList.add(response);
            }
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping("/review/user={user_id}/movie={movie_id}")
    public ResponseEntity<Object> findReviewsByUserIdAndMovieId(@PathVariable("user_id") Integer userId, @PathVariable("movie_id") Integer movieId) {
        Optional<User> user = userRepository.findById(userId);
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (user.isPresent() && movie != null) {
            List<Review> reviews = reviewRepository.findByUserIdAndMovie(userId, movie);
            List<Map<String, Object>> responseList = new ArrayList<>();
            for (Review review : reviews) {
                MovieScore movieScore = review.getMovieScore(); // get the MovieScore from the Review
                Integer movieScoreId = movieScore.getId(); // get the ID from the MovieScore
                Optional<MovieScore> optionalMovieScore = movieScoreRepository.findById(movieScoreId); // fetch the MovieScore by its ID
                if (optionalMovieScore.isPresent()) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("review", review);
                    response.put("movieScore", optionalMovieScore.get());
                    responseList.add(response);
                }
            }
            return ResponseEntity.ok(responseList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/review/{review_id}")
    public Review findReviewById(@PathVariable Integer reviewId) {
        return this.reviewRepository.findById(reviewId).orElse(null);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/review")
    public Review addOneReview(@RequestBody Map<String, Object> reviewData) {
        // Extract data from the request body
        Map<String, Object> movieData = (Map<String, Object>) reviewData.get("movie");
        Map<String, Object> movieScoreData = (Map<String, Object>) reviewData.get("movieScore");
        String content = (String) reviewData.get("content");
        Integer userId = (Integer) reviewData.get("user");
        Movie movie = new Movie();
            movie.setId((Integer) movieData.get("id"));
            movie.setTmdbId((Integer) movieData.get("tmdbId"));
            movie.setTitle((String) movieData.get("title"));
            movieRepository.save(movie);
        MovieScore movieScore = new MovieScore();
            movieScore.setScore((Integer) movieScoreData.get("score"));
            movieScore.setMovie(movie); 
            movieScoreRepository.save(movieScore);
        Review review = new Review();
            review.setMovie(movie);
            review.setMovieScoreId(movieScore);
            review.setContent(content);
            review.setUserId(userId);
        return reviewRepository.save(review);
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/review/{review_id}")
    public Review updateOneReview(@PathVariable("review_id") Integer reviewId, @RequestBody Map<String, Object> updates) {
        Review review = this.reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return null;
        }
        String content = (String) updates.get("content");
        Map<String, Object> movieScoreData = (Map<String, Object>) updates.get("movieScore");
        MovieScore movieScore = review.getMovieScore();
        movieScore.setScore((Integer) movieScoreData.get("score"));
        movieScoreRepository.save(movieScore);
        review.setContent(content);
        return reviewRepository.save(review);
    }


    @DeleteMapping("/review/{user_id}")
    public void deleteReview(@PathVariable Integer reviewId) {
        this.reviewRepository.deleteById(reviewId);
    }

}
