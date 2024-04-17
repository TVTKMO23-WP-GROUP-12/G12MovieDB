package com.group12.moviedb.models;

import java.io.Serializable;
import java.util.Objects;

public class MoviesToWatchId implements Serializable {
    private User user;
    private Movie movie;

    public MoviesToWatchId() {
    }

    public MoviesToWatchId(User user, Movie movie) {
        this.user = user;
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviesToWatchId)) return false;
        MoviesToWatchId that = (MoviesToWatchId) o;
        return Objects.equals(getUser(), that.getUser()) &&
               Objects.equals(getMovie(), that.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getMovie());
    }
}