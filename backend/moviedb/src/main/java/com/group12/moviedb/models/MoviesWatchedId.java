package com.group12.moviedb.models;

import java.io.Serializable;
import java.util.Objects;

public class MoviesWatchedId implements Serializable {
    private User user;
    private Movie movie;

    public MoviesWatchedId() {
    }

    public MoviesWatchedId(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Movie getMovieId() {
        return movie;
    }

    public void setMovieId(Movie movie) {
        this.movie = movie;
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