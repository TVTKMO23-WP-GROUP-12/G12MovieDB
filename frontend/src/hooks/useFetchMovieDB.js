import { useState, useEffect } from 'react';

function useFetchMovie() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const movieId = localStorage.getItem('movieId');

  useEffect(() => {
    fetch(`http://localhost:8080/movie/${movieId}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error fetching movie data');
        }
      })
      .then(data => {
        setData(data);
        setLoading(false);
      })
      .catch(error => {
        setError(error);
        setLoading(false);
      });
  }, [movieId]);  // re-run the effect when movieId changes
  console.log (data);
  return { data };
  
}

export default useFetchMovie;