package com.group12.moviedb.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "movies_watched")
@IdClass(MoviesWatchedId.class)
public class MoviesWatched {
    @Id
    @ManyToOne
    @JoinColumn(name = "users_id")
    private int userId;

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private int movieId;

    @Column(name = "note")
    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MoviesWatched() {
    }

    public MoviesWatched(int userId, int movieId, String note, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.movieId = movieId;
        this.note = note;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

	public MoviesWatched orElse(Object object) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'orElse'");
	}

}
