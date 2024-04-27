import React from 'react';
import './MovieInfo.css';

const OtherReviews = ({ reviews, user }) => {
  return (
    <div className="movie-reviews">
      <h3>Muiden arvostelut:</h3>
      {reviews && Array.isArray(reviews) && reviews
        .filter(review => review && review.user !== user)
        .map(review => (
          <div key={review.review_id}>
            <p>Arvostelu: {review.content}</p>
            <p>Käyttäjä: {review.user}</p>
            <p>Pisteet: {review.movieScore ? review.movieScore.score : 'No score'}/5</p>
            <hr></hr>
          </div>
        ))}
    </div>
  );
}

export default OtherReviews;