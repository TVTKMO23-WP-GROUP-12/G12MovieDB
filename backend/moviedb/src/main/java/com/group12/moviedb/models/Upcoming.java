package com.group12.moviedb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
@Table(name = "upcoming")
public class Upcoming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="upcoming_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    @JsonBackReference // Prevent infinite recursion by ignoring the serialization of 'movie' back reference
    private Movie movie;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Upcoming() {}

    public Upcoming(Movie movie, LocalDate releaseDate) {
        this.movie = movie;
        this.releaseDate = releaseDate;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
