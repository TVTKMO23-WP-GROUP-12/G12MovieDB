package com.group12.moviedb.models;

import java.io.Serializable;
import java.util.Date;

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
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    @SuppressWarnings("unused")
    private Favorites() {}

    public Favorites(int userId, int movieId, Date createdAt, Date updatedAt) {
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
   

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
