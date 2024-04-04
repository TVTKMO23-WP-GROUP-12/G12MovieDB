package com.group12.moviedb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.group12.moviedb.models.Review;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.repository.MovieRepository;
import com.group12.moviedb.repository.ReviewRepository;

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

    public ReviewController(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/review")
    public Iterable<Review> findAllreview() {
        return this.reviewRepository.findAll();
    }

    @GetMapping("/review/movie={movie_id}")
    public List<Review> findReviewsByMovieId(@PathVariable("movie_id") int movieId) {
        Movie movie = movieRepository.findById(movieId);
        if (movie != null) {
            return reviewRepository.findByMovie(movie);
        } else {
            return Collections.emptyList();
        }
    }
    
    @GetMapping("/review/user={user_id}")
    public List<Review> findReviewsByUserId(@PathVariable("user_id") int userId) {
        Movie movie = movieRepository.findById(userId);
        if (movie != null) {
            return reviewRepository.findByUserId(userId);
        } else {
            return Collections.emptyList();
        }
    }

    @GetMapping("/review/{review_id}")
    public Review findReviewById(@PathVariable int review_id) {
        return this.reviewRepository.findById(review_id);
    }

    @PostMapping("/review")
    public Review addOneReview(@RequestBody Review review) {
        return this.reviewRepository.save(review);
    }

    @PatchMapping("/review/{id}")
    public Review updateOneReview(@PathVariable int review_id, @RequestBody Map<String, Object> updates) {
        Review review = this.reviewRepository.findById(review_id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "review_id":
                    review.setReviewId((int) value);
                    break;
                case "content":
                    review.setContent((String) value);
                    break;
                default:
                    break;
            }
        });
    return this.reviewRepository.save(review);
    }

    @DeleteMapping("/review/{id}")
    public void deleteReview(@PathVariable int id) {
        this.reviewRepository.deleteById(id);
    }

}
