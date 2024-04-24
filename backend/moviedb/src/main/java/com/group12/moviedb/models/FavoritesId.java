package com.group12.moviedb.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
public class FavoritesId implements Serializable {

    public FavoritesId() {}
    
    private User user;
    private Movie movie;

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public User setUser(User user) {
        return this.user = user;
    }

  

    public Movie setMovie(Movie movie) {
        return this.movie = movie;
    }
}

