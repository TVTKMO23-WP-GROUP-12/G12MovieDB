import React, { useState } from 'react';
import useFetchUserReviewMovie from '../hooks/useFetchUserReviewMovie';
import useFetchMovieId from '../hooks/useFetchMovieId';

function MovieUserReview({ movieId, userId }) {
  const { data: userReview, loading, error } = useFetchUserReviewMovie(movieId, userId);
  const [review, setReview] = useState(userReview ? userReview[0] : '');  // initialize state here
  const [score, setScore] = useState(1);
  const movie = useFetchMovieId();
  console.log(localStorage.getItem('movieId'));

  const postReview = () => {

    const scoreData = {
      movie_id: {id: parseInt(localStorage.getItem('movieId'))},
      score: score,
      created_at: new Date()
    };

    fetch('http://localhost:8080/movie_scores', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(scoreData),
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));

    const reviewData = {
      movie: {id: parseInt(localStorage.getItem('movieId'))},
      user: {id: parseInt(localStorage.getItem('userId'))},
      content: review,
    };
    
    fetch('http://localhost:8080/review', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(reviewData),
    })
    .then(response => response.json())
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));

  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      {review && <p>{review}</p>}
      <div className="review">
        <textarea
          placeholder="Kirjoita arvostelusi tähän..."
          onChange={(e) => setReview(e.target.value)}
        />
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
  );
}

export default MovieUserReview;