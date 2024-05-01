import { useState, useEffect } from 'react';

const useFetchWatched = (userId, onWatchedLoaded) => {
  const [watched, setWatchedMovies] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/movies_watched/user/${userId}`)
      .then(response => response.json())
      .then(data => {
        const promises = data.map(watchedMovie =>
          fetch(`http://localhost:8080/public/tmdb/${watchedMovie.movie.tmdbId}`)
            .then(response => response.json())
            .then(movieDetails => ({ 
              ...watchedMovie, 
              movieDetails, 
              poster_path: movieDetails.poster_path 
            }))
        );
        return Promise.all(promises);
      })
      .then(watchedMoviesWithDetails => {
        setWatchedMovies(watchedMoviesWithDetails);
        if (onWatchedLoaded) {
          onWatchedLoaded();
        }
      })
      .catch(error => console.error('Error:', error));
  }, [userId, onWatchedLoaded]);

  return watched;
};

export default useFetchWatched;