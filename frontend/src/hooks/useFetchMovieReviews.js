import { useEffect, useState } from 'react';

function useFetchMovieReviews(movieId) {
  const [reviews, setReviews] = useState(null);
  useEffect(() => {
    fetch(`http://localhost:8080/public/review/movie=${movieId}`)
      .then(response => response.json())
      .then(data => setReviews(data))
      .catch(error => console.error('Error:', error));
  }, [movieId]);
  
  return reviews;
}

export default useFetchMovieReviews;