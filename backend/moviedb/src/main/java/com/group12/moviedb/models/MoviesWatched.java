package com.group12.moviedb.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "movies_watched")
@IdClass(MoviesWatchedId.class)
public class MoviesWatched implements Serializable {
    @Id
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "note", nullable = false)
    private String note;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MoviesWatched() {
    }

    public MoviesWatched(User user, Movie movie, String note, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.movie = movie;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

  
}