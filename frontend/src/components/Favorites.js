import { Link } from 'react-router-dom';
import useFetchFavorites from '../hooks/useFetchFavorites';

// On: 
// - users own page (User)

const Favorites = ({ id }) => {;
  const favorites = useFetchFavorites(id);

  return (
    <div className="user-tabs-content">
      <h2>Suosikit</h2>
      <ul>
        {favorites.map(favorite => (
          <div key={favorite.favoriteId} style={{ display: 'flex', justifyContent: 'flex-start' }}>
            <div className="review-image">
                <img src={`https://image.tmdb.org/t/p/w500/${favorite.poster_path}`} alt={favorite.movie.title} />
              </div>
            <div className="review-content">
              <h3>
                <Link to={`/public/movie/${favorite.movie.tmdbId}`}>{favorite.movie.title}</Link>
              </h3>
            </div>
          </div>
        ))}
      </ul>
    </div>
  );
};

export default Favorites;

