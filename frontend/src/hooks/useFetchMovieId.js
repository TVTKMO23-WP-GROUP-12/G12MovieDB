import { useEffect, useState } from 'react';

function useFetchMovieId() {
  const [loading, setLoading] = useState(true);
  const [movieId, setMovieId] = useState(null);
  const [movieData, setMovieData] = useState(null);
  const tmdbId = localStorage.getItem('movieTmdb');  // get tmdbId from localStorage
  useEffect(() => {
    fetch(`http://localhost:8080/public/movie/${tmdbId}`)
      .then(response => response.json())
      .then(data => {
        setMovieId(data.id);  
        setMovieData(data);  
        setLoading(false);  
      })
      .catch(error => console.error('Error:', error));
  }, [tmdbId]);

  return { movieId, movieData, loading };
}

export default useFetchMovieId;