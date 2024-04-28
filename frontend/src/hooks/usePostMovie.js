import { useEffect } from 'react';

function usePostMovie() {
  const movieId = localStorage.getItem('movieId');
  const movieTitle = localStorage.getItem('movieTitle');
  const getMovieUrl = `http://localhost:8080/public/movie/${movieId}`;
  const postMovieUrl = 'http://localhost:8080/public/movie';
  
  useEffect(() => {
    if (!movieId) {
      return;
    }

    fetch(getMovieUrl)
      .then(response => {
        return response.json();
      })      
      .then(data => {
        if (data === null) {
          const movieData = {
            tmdbId: movieId,
            title: movieTitle,
          };
          console.log(movieData);
          fetch(postMovieUrl, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(movieData),
          })
          .then(response => {
            return response.json();
          })
          .then(postResponse => console.log(postResponse))
          .catch(error => console.error('Error:', error));
        }
      })
      .catch(error => console.error('Error:', error));
  }, [movieId, movieTitle]);
}

export default usePostMovie;