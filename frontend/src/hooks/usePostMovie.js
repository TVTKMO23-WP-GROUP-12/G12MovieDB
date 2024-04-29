import { useEffect } from 'react';

function usePostMovie() {
  const tmdbId = localStorage.getItem('movieTmdb');
  const title = localStorage.getItem('movieTitle');

  fetch(`http://localhost:8080/public/movie/${parseInt(tmdbId)}`)
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Movie not found');
      }
    })
    .then(movie => {
      console.log('Movie found:', movie);
    })
    .catch(error => {
      console.log('Movie not found, adding new movie:', error);
      const newMovie = {
        title: title,
        tmdbId: parseInt(tmdbId)
      };
      fetch('http://localhost:8080/public/movie', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(newMovie),
      })
      .then(response => response.json())
      .then(data => console.log('New movie added:', data))
      .catch(error => console.error('Error:', error));
    });
}

export default usePostMovie;