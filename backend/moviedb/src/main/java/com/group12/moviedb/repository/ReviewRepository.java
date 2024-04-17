package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Review;

@Repository
public interface ReviewRepository<Movie> extends JpaRepository<Review, Integer> {
    List<Review> findByUserId(Integer user_id);
    List<Review> findByMovie(Movie movie_id);
    Optional<Review> findById(@SuppressWarnings("null") Integer review_id);
    List<Review> findByMovieId(Integer movie_id);
}
