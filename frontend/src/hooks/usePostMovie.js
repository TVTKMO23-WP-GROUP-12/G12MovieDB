import { useEffect } from 'react';

function usePostMovie() {
  useEffect(() => {
    const tmdbId = parseInt(localStorage.getItem('movieTmdb'));
    const title = localStorage.getItem('movieTitle');
    fetch(`http://localhost:8080/public/movie/tmdb/${parseInt(tmdbId)}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Movie not found');
        }
      })
      .then(movie => {
        console.log('Movie found:', movie);
        localStorage.setItem('movieId', movie.id);
      })
      .catch(error => {
        console.log('Movie not found, adding new movie:', title);
        fetch(`http://localhost:8080/public/movie/${tmdbId}/${title}`, {
          method: 'POST',
        })
        .then(response => {
          console.log(response);
          return response.json();
        })
        .then(data => console.log('New movie added:', data))
        .catch(error => console.error('Error:', error));
      });
  }, []); // Empty dependency array ensures this runs only on initial render
}

export default usePostMovie;