import { useState, useEffect } from 'react';

function useFetchUserReviewMovie(userId) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const movieId = localStorage.getItem('movieId');

  useEffect(() => {
    fetch(`http://localhost:8080/review/user=${userId}/movie=${movieId}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error: ' + response.status);
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
  }, [userId, movieId]);
  return { data, loading, error };
}

export default useFetchUserReviewMovie;