package com.group12.moviedb.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @Column(name="movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @OneToMany(mappedBy="movieId", cascade = CascadeType.ALL)
    private List<Favorites> favorites;
    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    private List<MoviesWatched> moviesWatched;
    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    private List<MoviesToWatch> moviesToWatch;
    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    private List<Review> review;
    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    private List<MovieScore> movieScores;

    @Column(name="title")
    private String title;

    @Column(name="tmdb_id")
    private Integer tmdbId;

    @SuppressWarnings("unused")
    public Movie() {}

    public Movie(int movieId, String title, Integer tmdbId) {
        this.movieId = movieId;
        this.title = title;
        this.tmdbId = tmdbId;
    }

    public int getId() {
        return this.movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTmdbId() {
        return this.tmdbId;
    }

    public List<Favorites> getFavorites() {
        return this.favorites;
    }

    public void setId(int movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTmdbId(Integer tmdbId) {
        this.tmdbId = tmdbId;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

}