import { useEffect, useState } from 'react';

function useFetchMovieReviews(id) {
  const [reviews, setReviews] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/review/movie=${id}`)
      .then(response => response.json())
      .then(data => setReviews(data))
      .catch(error => console.error('Error:', error));
  }, [id]);
  
  return reviews;
}

export default useFetchMovieReviews;