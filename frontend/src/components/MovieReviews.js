import React from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import { Link } from 'react-router-dom';
import UserReview from './UserReviews'; // Import UserReview
import OtherReviews from './OtherReviews'; // Import OtherReviews

const MovieReviews = ({ reviews, user }) => { // Use reviews and user props
  
  function flattenReviews(reviews) {
    return reviews.flatMap(review => 
      review.movieScore && review.movieScore.movie.reviews.filter(r => typeof r === 'object')
    );
  }
  
  return(
    console.log(reviews),
    console.log(user),
    
    <div className="movie-tabs-content">
      <h2>Arvostelut</h2>
      <UserReview reviews={reviews} user={user} />
      <OtherReviews reviews={flattenReviews(reviews)} user={user} />
    </div>
  );
}

export default MovieReviews;