package com.group12.moviedb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @ManyToOne
    @JoinColumn(name="movie_id")
    @JsonBackReference
    @JsonIgnoreProperties("favorites")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="movie_score_id")
    @JsonManagedReference
    private MovieScore movieScoreId;

    @Column(name="content", nullable = false)
    private String content;

    public Review() {
    }

    public Review(Integer reviewId, Integer userId, Movie movie, MovieScore movieScoreId, String content) {
        this.id = reviewId;
        this.userId = userId;
        this.movie = movie;
        this.movieScoreId = movieScoreId;
        this.content = content;
    }

    public Integer getReviewId(Integer reviewId) {
        return this.id;
    }

    public Integer getReviewId() {
        return this.id;
    }

    public Integer getUser() {
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

    public void setReviewId(Integer reviewId) {
        this.id = reviewId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Movie setMovie(Movie movie) {
        return this.movie = movie;
    }

    public void setMovieScoreId(MovieScore movieScoreId) {
        this.movieScoreId = movieScoreId;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}