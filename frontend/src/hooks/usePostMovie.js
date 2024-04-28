import { useEffect } from 'react';

function usePostMovie(tmdbId) {
  useEffect(() => {
    // Check if the movie is in the database
    fetch(`http://localhost:8080/public/movie/tmdb=?${tmdbId}`)
      .then(response => {
        if (!response.ok) {
          // If the movie is not in the database, add it
          fetch('http://localhost:8080/public/movie', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              title: tmdbId.title,
              tmdbId: tmdbId.id,
            }),
          });
        }
      });
  }, [tmdbId]);
}

export default usePostMovie;