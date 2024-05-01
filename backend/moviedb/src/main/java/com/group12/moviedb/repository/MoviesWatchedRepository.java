package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.MoviesWatchedId;
import com.group12.moviedb.models.User;

@Repository
public interface MoviesWatchedRepository extends JpaRepository<MoviesWatched, MoviesWatchedId> {
    List<MoviesWatched> findByUser(User user);
    List<MoviesWatched> findByMovie(Movie movie);
    List<MoviesWatched> findByUserAndMovie(User user, Movie movie);
    void deleteByUser(User user);
    void deleteByMovie(Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
}
