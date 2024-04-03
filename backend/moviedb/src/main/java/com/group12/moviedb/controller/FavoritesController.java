package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Favorites;
import com.group12.moviedb.models.FavoritesId;
import com.group12.moviedb.repository.FavoritesRepository;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class FavoritesController {

    private final FavoritesRepository favoritesRepository;

    public FavoritesController(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    @GetMapping("/favorites")
    public Iterable<Favorites> findAllFavorites() {
        return this.favoritesRepository.findAll();
    }

    @GetMapping("/favorites/{user_id}")
    public Favorites findOneFavoriteByUserId(int users_id) {
        return this.favoritesRepository.findByUserId(users_id);
    }

    @PostMapping("/favorites")
    public Favorites addOneFavorite(@RequestBody Favorites favorite) {
        return this.favoritesRepository.save(favorite);
    }

    @PatchMapping("/favorites/{id}")
    public Favorites updateOneFavorite(@PathVariable int user_id, @RequestBody FavoritesId favorite) {
        Favorites existingFavorite = this.favoritesRepository.findByUserId(user_id);
        existingFavorite.setMovieId(favorite.getMovieId());
        return this.favoritesRepository.save(existingFavorite);
    }

}
