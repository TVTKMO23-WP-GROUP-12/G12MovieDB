import { useState, useEffect } from 'react';

const useFetchFavorites = (userId, onFavoritesLoaded) => {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/favorites/${userId}`)
      .then(response => response.json())
      .then(data => {
        const promises = data.map(favorite =>
          fetch(`http://localhost:8080/public/tmdb/${favorite.movie.tmdbId}`)
            .then(response => response.json())
            .then(movieDetails => ({ 
              ...favorite, 
              movieDetails, 
              poster_path: movieDetails.poster_path 
            }))
        );
        return Promise.all(promises);
      })
      .then(favoritesWithDetails => {
        setFavorites(favoritesWithDetails);
        if (onFavoritesLoaded) {
          onFavoritesLoaded();
        }
      })
      .catch(error => console.error('Error:', error));
  }, [userId, onFavoritesLoaded]);

  return favorites;
};

export default useFetchFavorites;