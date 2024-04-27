import { useEffect, useState } from 'react';

function useFetchMovieReviews(reviewId) {
  const [reviews, setReviews] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/public/movie/tmdb/${reviewId}`)
      .then(response => response.json())
      .then(data => {
        const movieId = data.id;
        fetch(`http://localhost:8080/public/review/movie/${movieId}`)
        .then(response => response.json())
        .then(reviewsData => {
          // No need to fetch movieScore separately, it's included in the review data
          setReviews(reviewsData); // Update the reviews state
        })
        .catch(error => console.error('Error:', error));
      })
      .catch(error => console.error('Error:', error));
  }, [reviewId]);
  
  return reviews;
}

export default useFetchMovieReviews;