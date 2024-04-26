package com.group12.moviedb.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class MovieSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer movieId;
    private String name;
    private String genre;
    private Long releaseYear;
    private String person;
    private String poster;

    public Integer getMovieId() {
        return this.movieId;
    }
    
    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Long releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }









}



