import { Link } from 'react-router-dom';
import useFetchWatched from '../hooks/useFetchWatched';

// On: 
// - users own page (User)

const Watched = ({ id }) => {
  const watchedMovie = useFetchWatched(id);
  
  return (
    <div className="user-tabs-content">
      <h2>On katsonut</h2>
        <ul>
          {watchedMovie.map((watchedMovie, index) => (
            <div key={index} style={{ display: 'flex', justifyContent: 'flex-start' }}>
              <div className="review-image">
                <img src={`https://image.tmdb.org/t/p/w500/${watchedMovie.poster_path}`} alt={watchedMovie.movie.title} />
              </div>
              <div className="review-content">
                <h3>
                  <Link to={`/public/movie/${watchedMovie.movie.tmdbId}`}>
                    {watchedMovie.movie.title}
                  </Link>
                </h3>
              </div>
            </div>
          ))}
        </ul>
      </div>
  );
}

export default Watched;
