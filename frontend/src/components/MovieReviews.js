import React from 'react';
import './MovieInfo.css';
import noImageAvailable from '../media/noImageAvailable.png';
import useFetchMovieReviews from '../hooks/useFetchMovieReviews';
import useFetchUserReviewMovie from '../hooks/useFetchUserReviewMovie';

import { Link } from 'react-router-dom';

const MovieReviews = ({ id }) => {
    const movieReviews = useFetchMovieReviews(id);
    const userReview = useFetchUserReviewMovie(id);

    return(
        <div className="movie-tabs-content">
            <h2>Arvostelut</h2>
            <div>
                <h3>Sinun arvostelusi:</h3>
                <useFetchUserReviewMovie id={id} />
            </div>
            <div className="movie-reviews">
                <h3>Muiden arvostelut:</h3>
                {movieReviews.map(review => (
                <div key={review.reviewId}>
                    <p>
                    <Link to={`/public/movie/${review.movie.id}`}>{review.movie.title}</Link>
                    </p>
                    <p>
                    {review.content} | Pisteet: {review.movieScore.score}/5
                    </p>
                    <hr></hr>
                </div>
                ))}
            </div>
        </div>

    );
};

export default MovieReviews;