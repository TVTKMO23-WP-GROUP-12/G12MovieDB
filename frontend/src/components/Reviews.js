import { Link } from 'react-router-dom';
import useFetchReviews from '../hooks/useFetchReviews';

const Reviews = ({ reviewId }) => {
  const reviews = useFetchReviews(reviewId);
  
// On:
// - users own page (User)

  return (
    <div className="user-tabs-content">
    <h2>Arvostelut</h2>
      {reviews.map(review => (
        <div key={review.reviewId}>
          <p>
            <Link to={`/movie/${review.movie.reviewId}`}>{review.movie.title}</Link>
          </p>
          <p>
            {review.content} | Pisteet: {review.movieScore.score}/5
          </p>
          <hr></hr>
        </div>
      ))}
  </div>
  );
};

export default Reviews;