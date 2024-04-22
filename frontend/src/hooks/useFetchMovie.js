import { useEffect, useState } from 'react';

function useFetchMovie(id) {
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/movie/${id}`)
      .then(response => response.json())
      .then(data => setMovie(data))
      .catch(error => console.error('Error:', error));
  }, [id]);
  
  return movie;
}

export default useFetchMovie;