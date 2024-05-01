import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import useFetchReviewsUser from '../hooks/useFetchReviewsUser';
import useFetchMovie from '../hooks/useFetchMovie';

const Reviews = ({ id }) => {
  const reviews = useFetchReviewsUser(id);

// On:
// - users own page (User)

  return (
    <>
    <div className="user-tabs-content">
      <h2>Arvostelut</h2>
          {reviews.map(review => (
            <div key={review.reviewId} style={{ display: 'flex', justifyContent: 'flex-start' }}>
              <div className="review-image">
                <img src={`https://image.tmdb.org/t/p/w500/${review.poster_path}`} alt={review.movie.title} />
              </div>
              <div className="review-content">
                <h3>
                  <Link to={`/public/movie/${review.movie.tmdbId}`}>{review.movie.title}</Link>
                </h3>
                <p>
                  {review.review.content} | Pisteet: {review.review.movieScore.score}/5
                </p>
              </div>
            </div>
          ))}
          
        </div>
    </>
  );
};

export default Reviews;