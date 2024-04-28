package com.group12.moviedb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "top_rated")
public class TopRated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="top_rated_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference // Prevent infinite recursion by ignoring the serialization of 'movie' back reference
    private Movie movie;

    @Column(name = "rating")
    private Double rating;

    public TopRated() {}

    public TopRated(Movie movie, Double rating) {
        this.movie = movie;
        this.rating = rating;
    }

    // Getter and setter methods

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
