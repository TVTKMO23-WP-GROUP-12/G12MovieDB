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
    public List<Review> findReviewsByUserId(@PathVariable("user_id") Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return reviewRepository.findByUserId(userId);
        } else {
            return Collections.emptyList();
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
    public Review addOneReview(@RequestBody Review review) {
        // Save the MovieScore to the database
        MovieScore movieScore = review.getMovieScore();
        movieScoreRepository.save(movieScore);
        // Update the MovieScore in the Review
        review.setMovieScoreId(movieScore);
        // Save the Review to the database
        return reviewRepository.save(review);
    }
    

    @PatchMapping("/review/{review_id}")
    public Review updateOneReview(@PathVariable Integer reviewId, @RequestBody Map<String, Object> updates) {
        Review review = this.reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "review_id":
                        review.setReviewId((Integer) value);
                        break;
                    case "content":
                        review.setContent((String) value);
                        break;
                    case "user_id":
                        review.setUserId((Integer) value);
                        break; // Add break statement here
                    default:
                        break;
                }
            });
            return this.reviewRepository.save(review);
        } else {
            return null;
        }
    }


    @DeleteMapping("/review/{user_id}")
    public void deleteReview(@PathVariable Integer reviewId) {
        this.reviewRepository.deleteById(reviewId);
    }

}
