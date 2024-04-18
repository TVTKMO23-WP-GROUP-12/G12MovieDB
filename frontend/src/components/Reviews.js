import { Link } from 'react-router-dom';
import useFetchReviews from '../hooks/useFetchReviews';

const Reviews = ({ id }) => {
  const reviews = useFetchReviews(id);
  
// On:
// - users own page (User)

  return (
    <div className="user-tabs-content">
    <h2>Arvostelut</h2>
      {reviews.map(review => (
        <div key={review.reviewId}>
          <h3>
            <Link to={`/movie/${review.movie.id}`}>{review.movie.title}</Link>
          </h3>
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