import { useEffect, useState } from 'react';

function useFetchMovie(movieId) {
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/tmdb/${movieId}`)
      .then(response => response.blob())
      .then(blob => {
        const reader = new FileReader();
        reader.onloadend = () => {
          const movieData = JSON.parse(reader.result);
          setMovie(movieData);
        };
        reader.readAsText(blob, 'ISO-8859-1');
      })
      .catch(error => console.error('Error:', error));
  }, [movieId]);
  
  return movie;
}

export default useFetchMovie;