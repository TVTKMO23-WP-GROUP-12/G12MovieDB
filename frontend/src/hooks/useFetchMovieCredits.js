import { useEffect, useState } from 'react';

function useFetchMovieCredits(id) {
  const [credits, setCredits] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/movie/${id}/credits`)
      .then(response => response.json())
      .then(data => setCredits(data))
      .catch(error => console.error('Error:', error));
  }, [id]);
  
  return credits;
}

export default useFetchMovieCredits;