package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.MoviesWatched;
import com.group12.moviedb.models.MoviesWatchedId;

@Repository
public interface MoviesWatchedRepository extends JpaRepository<MoviesWatched, MoviesWatchedId> {
    MoviesWatched findByUserId(int userId);
    MoviesWatched findByMovieId(int movieId);
    MoviesWatched findByIds(int userId, int movieId);
    MoviesWatched saveById(int userId);
    MoviesWatched saveByIds(int userId, int movieId);
    MoviesWatched updateByUserId(int userId);
    MoviesWatched updateByMovieId(int movieId);
    MoviesWatched updateByIds(int userId, int movieId);
    MoviesWatched updateNoteByIds(int userId, int movieId, String note);
    MoviesWatched deleteByIds(int userId, int movieId);
    MoviesWatched deleteByUserId(int userId);
}