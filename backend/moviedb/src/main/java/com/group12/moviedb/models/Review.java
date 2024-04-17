package com.group12.moviedb.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private int reviewId;

    @Column(name="user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonIgnoreProperties("favorites")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="movie_score_id")
    private MovieScore movieScoreId;

    @Column(name="content")
    private String content;

    public Review() {
    }

    public Review(int reviewId, int userId, Movie movie, MovieScore movieScoreId, String content) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.movie = movie;
        this.movieScoreId = movieScoreId;
        this.content = content;
    }

    public int getReviewId() {
        return this.reviewId;
    }

    public int getUser() {
        return this.userId;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public MovieScore getMovieScore() {
        return this.movieScoreId;
    }

    public String getContent() {
        return this.content;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMovieId(Movie movie) {
        this.movie = movie;
    }

    public void setMovieScoreId(MovieScore movieScoreId) {
        this.movieScoreId = movieScoreId;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}