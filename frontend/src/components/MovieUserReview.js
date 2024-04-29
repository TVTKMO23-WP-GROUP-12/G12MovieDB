import React, { useState } from 'react';
import './MovieReview.css';
import useFetchUserReviewMovie from '../hooks/useFetchUserReviewMovie';
import useFetchMovieId from '../hooks/useFetchMovieId';
import useFetchMovieDB from '../hooks/useFetchMovieDB';

function MovieUserReview({ movieId, userId }) {
  const movie = useFetchMovieId();
  const { data: userReview, loading, error } = useFetchUserReviewMovie(userId);
  const [review, setReview] = useState(userReview ? userReview[0] : ''); 
  const [score, setScore] = useState(1);
  const [isEditing, setIsEditing] = useState(false);
  const user_id = localStorage.getItem('userId');
  const movie_id = localStorage.getItem('movieId');
  
  const toggleEdit = () => {
    setIsEditing(!isEditing);
  };

  const postReview = () => {
  
    const reviewData = {
      userId: user_id,
      movie: {
      id: movie_id,
      // other movie properties
      },
      movieScoreId: {
        movie: {
          id: movie_id,
        },
      score: score,
      dateCreated: new Date(),
      // other movieScore properties
      },
      content: review
    };
  
    fetch('http://localhost:8080/review', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json;charset=UTF-8',
      },
      body: JSON.stringify(reviewData),
    })
    .then(response => {
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      return response.json();
    })
    .then(data => {
      if (data) {
        console.log(data);
      } else {
        console.log('No data returned from server');
      }
    })
    .catch(error => console.error('Error:', error));
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <div className="review">
        <p>{userReview[0].review.content}</p>
        <h3 className='points'>Pisteet: {userReview[0].movieScore.score}/5</h3>
        <div className= "edit-button">
          <button onClick={toggleEdit}>Muokkaa arvostelua</button>
        </div>
        <hr></hr>
      </div>
      {(!userReview[0] || isEditing) && (
        <div className="review">
          <textarea
            placeholder="Kirjoita arvostelusi tähän..."
            onChange={(e) => setReview(e.target.value)}
          />
          <div className="review-buttons">
            <select value={score} onChange={(e) => setScore(e.target.value)}>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
            <button onClick={postReview}>Tallenna arvostelu</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default MovieUserReview;