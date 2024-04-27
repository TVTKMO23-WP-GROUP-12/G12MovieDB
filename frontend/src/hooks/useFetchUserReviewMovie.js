import { useState, useEffect } from 'react';

function useFetchUserReviewMovie(reviewId, userId) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/review/movie=${reviewId}&user=${userId}`)
      .then(response => {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error('Error fetching review');
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
  }, [reviewId, userId]);

  return { data, loading, error };
}

export default useFetchUserReviewMovie;