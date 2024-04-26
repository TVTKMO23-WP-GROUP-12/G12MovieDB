import { useEffect, useState } from 'react';

function useFetchMovieReviews(reviewId) {
  const [reviews, setReviews] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/review/movie=${reviewId}`)
      .then(response => response.json())
      .then(data => setReviews(data))
      .catch(error => console.error('Error:', error));
  }, [reviewId]);
  
  return reviews;
}

export default useFetchMovieReviews;