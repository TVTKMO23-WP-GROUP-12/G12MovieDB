import { Link } from 'react-router-dom';
import useFetchFavorites from '../hooks/useFetchFavorites';

// On: 
// - users own page (User)

const Favorites = ({ favoriteId }) => {
  const favorites = useFetchFavorites(favoriteId);

  return (
    <div className="user-tabs-content">
      <h2>Suosikit</h2>
      <ul>
        {favorites.map(favorite => (
          <div key={favorite.favoriteId}>
            <p>
              <Link to={`/movie/${favorite.movieId}`}>{favorite.title}</Link>
            </p>
            <hr></hr>
          </div>
        ))}
      </ul>
    </div>
  );
};

export default Favorites;

