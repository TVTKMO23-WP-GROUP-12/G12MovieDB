import { useEffect, useState } from 'react';

function useFetchMovieCredits(tmdbId) {
  const [credits, setCredits] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/movie/tmdb/${tmdbId}/credits`)
      .then(response => response.json())
      .then(data => setCredits(data))
      .catch(error => console.error('Error:', error));
  }, [tmdbId]);
  
  return credits;
}

export default useFetchMovieCredits;