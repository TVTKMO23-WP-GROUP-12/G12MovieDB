package com.group12.moviedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Favorites;
import com.group12.moviedb.models.FavoritesId;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, FavoritesId> {
    Favorites findByUserId(Integer id);
    Favorites findByMovieId(Integer id);
 
    
}
