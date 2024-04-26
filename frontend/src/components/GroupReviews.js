import React from 'react';
import { Link } from 'react-router-dom';

//fetch user reviews and user details.

const GroupReviews = ({ reviews, users }) => {
    return (
        <div className="groupdetail-tabs-content">
        <h2>Arvostelut</h2>
        {reviews.map(review => {
            const user = users.find(user => user.userId.id === review.user);
            return (
                <div key={review.reviewId}>
                <p>
                    {user && <span> Arvostelu käyttäjältä <Link to={`/users/${user.userId.userId}`}> {user.userId.username} </Link></span>}
                </p>
                <p>
                    <Link to={`/movie/${review.movie.movieId}`}>{review.movie.title}</Link>
                </p>
                <p>
                    {review.content} | Pisteet: {review.movieScore.score}/5
                </p>
                <hr></hr>
                </div>
            );
        })}
        </div>
    );
};

export default GroupReviews;