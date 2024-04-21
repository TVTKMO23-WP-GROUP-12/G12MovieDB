import { useState, useEffect } from 'react';

//Hook that fetches user data and favorites.

const useFetchFavorites = (id) => {
  const [favorites, setFavorites] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/users/${id}`)
      .then(response => response.json())
      .then(data => {
        return Promise.all(data.favorites.map(favorite => 
          fetch(`http://localhost:8080/movie/${favorite.movieId}`)
            .then(response => response.json())
        ));
      })
      .then(movies => {
        setFavorites(movies);
      })
      .catch(error => console.error('Error:', error));
  }, [id]);

  return favorites;
};

export default useFetchFavorites;