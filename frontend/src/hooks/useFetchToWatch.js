import { useState, useEffect } from 'react';

const useFetchToWatch = (userId, onToWatchLoaded) => {
  const [toWatch, setToWatchMovies] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/movies_to_watch/user/${userId}`)
      .then(response => response.json())
      .then(data => {
        const promises = data.map(toWatchMovie =>
          fetch(`http://localhost:8080/public/tmdb/${toWatchMovie.movie.tmdbId}`)
            .then(response => response.json())
            .then(movieDetails => ({ 
              ...toWatchMovie, 
              movieDetails, 
              poster_path: movieDetails.poster_path 
            }))
        );
        return Promise.all(promises);
      })
      .then(toWatchMoviesWithDetails => {
        setToWatchMovies(toWatchMoviesWithDetails);
        if (onToWatchLoaded) {
          onToWatchLoaded();
        }
      })
      .catch(error => console.error('Error:', error));
  }, [userId, onToWatchLoaded]);

  return toWatch;
};

export default useFetchToWatch;