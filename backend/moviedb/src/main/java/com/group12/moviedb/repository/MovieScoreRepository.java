package com.group12.moviedb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.MovieScore;

@Repository
public interface MovieScoreRepository extends JpaRepository<MovieScore, Integer> {
    MovieScore findByMovieId(int movie_id);
    Optional<MovieScore> findById(int movie_score_id);
    void deleteById(int movie_score_id);
}
