package com.group12.moviedb.models;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "movie_scores")
public class MovieScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_score_id")
    private Integer id;

    @OneToMany(mappedBy = "movieScoreId", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Review> review;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference
    @JsonIgnoreProperties("favorites")
    private Movie movie;

    @Column(name = "score")
    private int score;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public MovieScore() {
    }

    public MovieScore(Integer movieScoreId, Movie movie, int score, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = movieScoreId;
        this.movie = movie;
        this.score = score;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getMovieScoreId() {
        return this.id;
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

    public void setMovieScoreId(Integer movieScoreId) {
        this.id = movieScoreId;
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