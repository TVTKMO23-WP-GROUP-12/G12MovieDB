import React, { useState } from 'react';
import './MovieContent.css';

const UserReviews = ({ reviews, user, setReviews }) => {
  const [isEditing, setIsEditing] = useState(false);
  const [editedReview, setEditedReview] = useState('');

  const submitReview = (reviewId) => {
    console.log(reviewId);
    fetch(`http://localhost:8080/review/${reviewId}`, {
      method: 'PATCH',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        content: editedReview,
      }),
    })
      .then(response => response.json())
      .then(data => {
        // Update review state
        setReviews(prevReviews => prevReviews.map(review => 
          review.movieScore.id === reviewId ? { ...review, content: data.content } : review
        ));
        setEditedReview("");
      })
      .catch(error => console.error('Error:', error));
  };

  return (
    <div className="movie-reviews">
      <h3>Your Reviews:</h3>
      {reviews && Array.isArray(reviews) && reviews
      .filter(review => review.user === user)
      .map(review => (
        <div key={review.review_id}>
          {isEditing ? (
            <>
              <input
                type="text"
                value={editedReview}
                onChange={e => setEditedReview(e.target.value)} 
              />
              <button onClick={() => {
                setIsEditing(false);
                submitReview(review.review_id);
              }}>Tallenna</button>
            </>
          ) : (
            <>
              <p>Arvostelu: {review.content}</p>
              <p>Pisteet: {review.movieScore.score}/5</p>
              <button onClick={() => {
                setIsEditing(true);
                setEditedReview(review.content);
              }}>Muokkaa</button>
            </>
          )}
          <hr></hr>
        </div>
      ))}
    </div>
  );
}

export default UserReviews;