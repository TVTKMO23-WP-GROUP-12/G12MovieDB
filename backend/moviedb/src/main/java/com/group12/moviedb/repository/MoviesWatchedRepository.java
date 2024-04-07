package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.MoviesWatchedId;
import com.group12.moviedb.models.User;

@Repository
public interface MoviesWatchedRepository extends JpaRepository<MoviesWatched, MoviesWatchedId> {
    MoviesWatched findByUser(User user);
    MoviesWatched findByMovie(Movie movie);
    MoviesWatched findByUserAndMovie(User user, Movie movie);

    @Modifying
    @Query("UPDATE MoviesWatched mw SET mw.user = :user WHERE mw.id = :id")
    void updateByUser(User user, MoviesWatchedId id);

    @Modifying
    @Query("UPDATE MoviesWatched mw SET mw.movie = :movie WHERE mw.id = :id")
    void updateByMovie(Movie movie, MoviesWatchedId id);

    @Modifying
    @Query("UPDATE MoviesWatched mw SET mw.user = :user, mw.movie = :movie WHERE mw.id = :id")
    void updateByUserAndMovie(User user, Movie movie, MoviesWatchedId id);

    @Modifying
    @Query("UPDATE MoviesWatched mw SET mw.note = :note WHERE mw.user = :user AND mw.movie = :movie")
    void updateNoteByUserAndMovie(User user, Movie movie, String note);

    void deleteByUser(User user);
    void deleteByMovie(Movie movie);
    void deleteByUserAndMovie(User user, Movie movie);
}
