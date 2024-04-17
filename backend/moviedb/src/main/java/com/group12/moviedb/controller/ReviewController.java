package com.group12.moviedb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    
    @SuppressWarnings("rawtypes")
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public ReviewController(@SuppressWarnings("rawtypes") ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/review")
    public Iterable<Review> findAllreview() {
        return this.reviewRepository.findAll();
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/review/movie={movie_id}")
    public List<Review> findReviewsByMovieId(@PathVariable("movie_id") Integer movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie != null) {
            return reviewRepository.findByMovieId(movieId);
        } else {
            return Collections.emptyList();
        }
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/review/user={user_id}")
    public List<Review> findReviewsByUserId(@PathVariable("user_id") Integer userId) {
        Optional<Movie> movie = movieRepository.findById(userId);
        if (movie != null) {
            return reviewRepository.findByUserId(userId);
        } else {
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/review/{review_id}")
    public Optional<Review> findReviewById(@PathVariable Integer review_id) {
        return this.reviewRepository.findById(review_id);
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/review")
    public Object addOneReview(@RequestBody Review review) {
        return this.reviewRepository.save(review);
    }

    @SuppressWarnings("unchecked")
    @PatchMapping("/review/{id}")
    public Object updateOneReview(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<Review> optionalReview = this.reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "content":
                        review.setContent((String) value);
                        break;
                    // Add other fields here if needed...
                    default:
                        break;
                }
            });
            return this.reviewRepository.save(review);
        } else { 
            // Handle the case where review with given id is not found... 
            throw new RuntimeException("Review not found with id: " + id);
        }
    }

    @SuppressWarnings("unchecked")
    @DeleteMapping("/review/{id}")
    public void deleteReview(@PathVariable Integer id) {
        this.reviewRepository.deleteById(id);
    }

}
