import React, { useState, useEffect } from 'react';
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
  const tmdbId = localStorage.getItem('movieTmdb');
  const movieTitle = localStorage.getItem('movieTitle');
  const [reviewUpdated, setReviewUpdated] = useState(false);
  const [inputValue, setInputValue] = useState('');

  const toggleEdit = () => {
    setIsEditing(!isEditing);
  };

  useEffect(() => {
    if (userReview && userReview[0]) {
      setInputValue(userReview[0].review.content);
    }
  }, [userReview]);

  useEffect(() => {
    if (reviewUpdated) {
      setReviewUpdated(false);
    }
  }, [reviewUpdated]);

  const handleInputChange = (event) => {
    setInputValue(event.target.value);
  };

  const postReview = () => {
    const reviewData = {
      movie: {
        id: parseInt(movie_id),
        title: movieTitle,
        tmdbId: parseInt(tmdbId),
      },
      movieScoreId: {
        score: parseInt(score),
      },
      content: inputValue,
      movieScore: {
        score: parseInt(score),
      },
      user: parseInt(user_id),
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
      setReviewUpdated(true);
    })
    .catch(error => { 
      // handle error
    });
  };

  const patchReview = () => {
    const reviewId = parseInt(userReview[0].review.reviewId);
    const reviewData = {
      content: inputValue,
      movieScore: {
        score: parseInt(score),
      },
    };

    fetch(`http://localhost:8080/review/${reviewId}`, {
      method: 'PATCH',
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
      setReviewUpdated(true);
    })
    .catch(error => {
      // handle error
    });
  };

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
      <>
        {userReview && userReview.length > 0 ? (
          <>
            <div className="review">
              <p>{userReview[0].review.content}</p>
              {localStorage.setItem('reviewId', userReview[0].review.reviewId)}
              <h3 className='points'>Pisteet: {userReview[0].movieScore.score}/5</h3>
              <div className= "edit-button">
                <button onClick={toggleEdit}>Muokkaa arvostelua</button>
              </div>
              <hr></hr>
            </div>
            {isEditing && (
              <div className="review">
                <input type="text"
                  value={inputValue}
                  onChange={(e) => setInputValue(e.target.value)}
                />
                <div className="review-buttons">
                  <select value={score} onChange={(e) => setScore(e.target.value)}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                  </select>
                  <button onClick={patchReview}>Tallenna arvostelu</button>
                </div>
              </div>
            )}
          </>
        ) : (
          <div className="review">
            <input type="text"
              placeholder="Kirjoita arvostelusi tähän..."
              onChange={(e) => setInputValue(e.target.value)}
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
      </>
    );
  };

export default MovieUserReview;