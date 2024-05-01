import React, { useState, useEffect } from 'react';
import './MovieReview.css';

const MovieOtherReviews = () => {
  const [reviews, setReviews] = useState([]);
  const [users, setUsers] = useState({});
  
  useEffect(() => {
    const movieId = localStorage.getItem('movieId');
    const currentUserId = Number(localStorage.getItem('userId'));
    fetch(`http://localhost:8080/public/review/movie=${movieId}`)
      .then(response => response.json())
      .then(data => {
        const otherUsersReviews = data.filter(review => review.user !== currentUserId);
        setReviews(otherUsersReviews);
        otherUsersReviews.forEach(review => {
          const userId = review.user;
          if (!users[userId]) {
            fetch(`http://localhost:8080/users/${userId}`)
              .then(response => response.json())
              .then(userData => setUsers(prevUsers => ({ ...prevUsers, [userId]: userData })))
              .catch(error => console.error('Error:', error));
          }
        });
      })
      .catch(error => console.error('Error:', error));
  }, []);

  return (
    <div>
      {reviews.map((review, index) => (
        <div key={index}>
            <h3>Käyttäjältä: {users[review.user]?.username || 'Loading...'}</h3>
            <p>{review.content}</p>
            <h3 className='points'>Pisteet: {review.movieScore.score}/5</h3> 
        </div>
      ))}
    </div>
  );
};

export default MovieOtherReviews;