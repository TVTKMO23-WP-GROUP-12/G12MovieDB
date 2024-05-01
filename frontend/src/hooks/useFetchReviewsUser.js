import { useState, useEffect } from 'react';

const useFetchReviews = (id, onReviewsLoaded) => {
  const [reviews, setReviews] = useState([]);

  useEffect(() => {
    if (id) {
      fetch(`http://localhost:8080/review/user=${id}`)
        .then(response => response.json())
        .then(data => {
          const promises = data.map(review =>
            fetch(`http://localhost:8080/public/tmdb/${review.movie.tmdbId}`)
              .then(response => response.json())
              .then(movieDetails => ({ 
                ...review, 
                movieDetails, 
                poster_path: movieDetails.poster_path 
              }))
          );
          return Promise.all(promises);
        })
        .then(reviewsWithDetails => {
          setReviews(reviewsWithDetails);
          if (onReviewsLoaded) {
            onReviewsLoaded();
          }
        })
        .catch(error => console.error('Error:', error));
    }
  }, [id, onReviewsLoaded]);
  return reviews;
};

export default useFetchReviews;