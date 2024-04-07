package com.group12.moviedb.models;

import java.io.Serializable;
import java.util.Objects;

public class MoviesWatchedId implements Serializable {
    private Integer userId;
    private Integer movieId;

    public MoviesWatchedId() {
    }

    public MoviesWatchedId(Integer userId, Integer movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesWatchedId)) return false;
        MoviesWatchedId that = (MoviesWatchedId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
               Objects.equals(getMovieId(), that.getMovieId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getMovieId());
    }
}

