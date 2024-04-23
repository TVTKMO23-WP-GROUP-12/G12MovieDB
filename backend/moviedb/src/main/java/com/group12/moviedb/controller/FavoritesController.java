package com.group12.moviedb.controller;

import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Favorites;
import com.group12.moviedb.models.FavoritesId;
import com.group12.moviedb.models.Movie;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.FavoritesRepository;
import com.group12.moviedb.repository.MovieRepository;
import com.group12.moviedb.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class FavoritesController {

    private final FavoritesRepository favoritesRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public FavoritesController(FavoritesRepository favoritesRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.favoritesRepository = favoritesRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }
    @GetMapping("/favorites")
    public Iterable<Favorites> findAllFavorites() {
        return this.favoritesRepository.findAll();
    }

    @GetMapping("/favorites/{user_id}")
    public Favorites findOneFavoriteByUser(Integer userId) {
        return this.favoritesRepository.findByUser(null);
    }

    @PostMapping("/favorites")
    public Favorites addOneFavorite(@RequestBody Favorites favorite) {
        return this.favoritesRepository.save(favorite);
    }

    @PatchMapping("/favorites/{user_id}/{movie_id}")
    public Favorites updateOneFavorite(@PathVariable Integer userId, @PathVariable Integer movieId, @RequestBody Favorites favorite) {
        // Retrieve the User and Movie entities using their userId:s
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));
    
        // Construct the composite key FavoritesId using the fetched User and Movie
        FavoritesId favoritesId = new FavoritesId(user, movie);
    
        // Find the existing favorite using the composite key
        Favorites existingFavorite = this.favoritesRepository.findById(favoritesId)
                .orElseThrow(() -> new NoSuchElementException("Favorite not found"));
    
        // Update the movie
        existingFavorite.setMovie(favorite.getMovie());
    
        // Save and return the updated favorite
        return this.favoritesRepository.save(existingFavorite);
    }
}