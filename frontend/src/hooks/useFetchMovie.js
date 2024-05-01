import { useEffect, useState } from 'react';
import usePostMovie from './usePostMovie';

function useFetchMovie() {
  const [movie, setMovie] = useState(null);
  const tmdbId = localStorage.getItem('movieTmdb');

  useEffect(() => {
    fetch(`http://localhost:8080/public/tmdb/${tmdbId}`)
      .then(response => response.blob())
      .then(blob => {
        const reader = new FileReader();
        reader.onloadend = () => {
          const movieData = JSON.parse(reader.result);
          setMovie(movieData);

          // Store movie.title into local storage
          localStorage.setItem('movieTitle', movieData.title);
        };
        reader.readAsText(blob, 'utf-8');
      })
      .catch(error => console.error('Error:', error));
  }, []);

  usePostMovie();
  return movie;
}

export default useFetchMovie;