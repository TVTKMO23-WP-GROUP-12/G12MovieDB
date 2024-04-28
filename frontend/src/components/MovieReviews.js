import React from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import useFetchUserId from '../hooks/useFetchUserId';
import useFetchMovieReviews from '../hooks/useFetchMovieReviews';
import useFetchUserReviewMovie from '../hooks/useFetchUserReviewMovie';
import { Link } from 'react-router-dom';

const MovieReviews = ({ movieId, UserId }) => {
    console.log(movieId);
    const { userId } = useFetchUserId(localStorage.getItem('username'));
    const movieReviews = useFetchMovieReviews(movieId);
    const userReview = useFetchUserReviewMovie(movieId, userId);

    return(
        <div className="movie-tabs-content">
            <h2>Arvostelut</h2>
            <div>
                <h3>Sinun arvostelusi:</h3>
                <useFetchUserReviewMovie movieId={movieId} userId={userId} />
            </div>
            <div className="movie-reviews">
                
            </div>
        </div>

    );
};

export default MovieReviews;