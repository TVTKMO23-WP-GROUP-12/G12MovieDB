package com.group12.moviedb.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="favorites")
@IdClass(FavoritesId.class)
public class Favorites implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    private int userId;

    @Id
    @ManyToOne
    @JoinColumn(name="movie_id")
    private int movieId;

    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @SuppressWarnings("unused")
    private Favorites() {}

    public Favorites(int userId, int movieId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.movieId = movieId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return this.userId;
    }
    
    public int getMovieId() {
        return this.movieId;
    }
   

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}