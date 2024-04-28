import { useEffect, useState } from 'react';

function useFetchMovie(tmdbId) {
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/tmdb/${tmdbId}`)
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
  }, [tmdbId]);
  console.log(movie);
  return movie;
}

export default useFetchMovie;