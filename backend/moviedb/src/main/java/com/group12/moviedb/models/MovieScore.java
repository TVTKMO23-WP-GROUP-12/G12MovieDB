package com.group12.moviedb.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="movie_scores")
public class MovieScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_score_id")
    private int movieScoreId;
    @OneToMany(mappedBy="movieScoreId", cascade = CascadeType.ALL)
    private List<Review> review;

    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonIgnoreProperties("favorites")
    private Movie movie;

    @Column(name="score")
    private int score;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public MovieScore() {
    }

    public MovieScore(int movieScoreId, Movie movie, int score, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.movieScoreId = movieScoreId;
        this.movie = movie;
        this.score = score;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getMovieScoreId() {
        return this.movieScoreId;
    }
    public Movie getMovie() {
        return this.movie;
    }

    public int getScore() {
        return this.score;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setMovieScoreId(int movieScoreId) {
        this.movieScoreId = movieScoreId;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
