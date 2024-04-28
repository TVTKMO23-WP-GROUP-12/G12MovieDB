package com.group12.moviedb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "popular")
public class Popular {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="popular_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference  
    private Movie movie;

    @Column(name = "rank")
    private Integer rank;

    public Popular() {}

    public Popular(Movie movie, Integer rank) {
        this.movie = movie;
        this.rank = rank;
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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}

