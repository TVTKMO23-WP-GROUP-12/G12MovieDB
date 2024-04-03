package com.group12.moviedb.models;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class FavoritesId implements Serializable {
    @Column(name = "user_id")
    private int userId;

    @Column(name = "movie_id")
    private int movieId;

    public FavoritesId() {
    }

    public FavoritesId(int userId, int movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    
}
