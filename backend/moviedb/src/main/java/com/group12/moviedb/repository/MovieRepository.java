package com.group12.moviedb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String group_name);
    @SuppressWarnings("null")
    Optional<Movie> findById(Integer id);
    void deleteById(@SuppressWarnings("null") Integer id);
    Movie save(Optional<Movie> movie);
}
