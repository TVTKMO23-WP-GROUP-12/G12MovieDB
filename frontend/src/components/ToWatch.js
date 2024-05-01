import { Link } from 'react-router-dom';
import useFetchToWatch from '../hooks/useFetchToWatch';

// On: 
// - users own page (User)

const ToWatch = ({ id }) => {
  const toWatchMovies = useFetchToWatch(id);

  return (
    <div className="user-tabs-content">
      <h2>Haluaa katsoa</h2>
      <ul>
          {toWatchMovies.map((toWatchMovies, index) => (
            <div key={index} style={{ display: 'flex', justifyContent: 'flex-start' }}>
              <div className="review-image">
                <img src={`https://image.tmdb.org/t/p/w500/${toWatchMovies.poster_path}`} alt={toWatchMovies.movie.title} />
              </div>
              <div className="review-content">
                <h3>
                  <Link to={`/public/movie/${toWatchMovies.movie.tmdbId}`}>
                    {toWatchMovies.movie.title}
                  </Link>
                </h3>
              </div>
            </div>
          ))}
        </ul>
    </div>
  );
}

export default ToWatch;
