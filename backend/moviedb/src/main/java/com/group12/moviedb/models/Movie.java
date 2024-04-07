package com.group12.moviedb.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

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

    @SuppressWarnings("unused")
    public Movie() {}

    public Movie(String title) {
        this.title = title;
    }

    public int getId() {
        return this.movie_id;
    }

    public String getTitle() {
        return this.title;
    }

    public List<Favorites> getFavorites() {
        return this.favorites;
    }

    public void setId(int id) {
        this.movie_id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

}
