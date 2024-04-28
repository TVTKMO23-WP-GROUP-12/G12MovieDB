package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Review;
import com.group12.moviedb.models.Movie;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByUserId(Integer id);
    Optional<Review> findById(Integer reviewId);
    List<Review> findByMovie(Movie movie);
    List<Review> findByUserIdAndMovie(Integer userId, Movie movie);
}
