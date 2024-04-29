import React from 'react';
import './MovieReview.css';
import noImageAvailable from '../media/noImageAvailable.png';
import MovieUserReview from '../components/MovieUserReview';
import useFetchUserId from '../hooks/useFetchUserId';
import MovieOtherReviews from '../components/MovieOtherReviews';
import useFetchMovieReviews from '../hooks/useFetchMovieReviews';
import useFetchUserReviewMovie from '../hooks/useFetchUserReviewMovie';
import { Link } from 'react-router-dom';

const MovieReviews = ({ movieId }) => {
    const userId  = localStorage.getItem('userId');
    const movieReviews = useFetchMovieReviews(movieId);
    const userReview = useFetchUserReviewMovie(userId, movieId );

    return(
        <div className="movie-reviews-content">
            <div>
                <h3>Sinun arvostelusi:</h3>
                <MovieUserReview movieId={movieId} userId={userId} />
            </div>
            <div>
                <MovieOtherReviews movieReviews={movieReviews} />
            </div>
        </div>
    );
};

export default MovieReviews;