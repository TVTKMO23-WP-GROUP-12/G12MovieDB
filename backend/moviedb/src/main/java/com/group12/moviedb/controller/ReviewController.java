package com.group12.moviedb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.group12.moviedb.models.Review;
import com.group12.moviedb.models.User;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;
import com.group12.moviedb.repository.ReviewRepository;
import com.group12.moviedb.repository.UserRepository;

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
    
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/review")
    public Iterable<Review> findAllreview() {
        return this.reviewRepository.findAll();
    }

    @GetMapping("/review/movie={movie_id}")
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
    @GetMapping("/review/{review_id}")
    public Review findReviewById(@PathVariable Integer reviewId) {
        return this.reviewRepository.findById(reviewId).orElse(null);
    }

    @PostMapping("/review")
    public Review addOneReview(@RequestBody Review review) {
        return this.reviewRepository.save(review);
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
