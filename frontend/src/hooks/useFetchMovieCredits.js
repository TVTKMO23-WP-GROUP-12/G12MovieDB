import { useEffect, useState } from 'react';

function useFetchMovieCredits(movieScoreId) {
  const [credits, setCredits] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/tmdb/${movieScoreId}/credits`)
      .then(response => response.json())
      .then(data => setCredits(data))
      .catch(error => console.error('Error:', error));
  }, [movieScoreId]);
  
  return credits;
}

export default useFetchMovieCredits;