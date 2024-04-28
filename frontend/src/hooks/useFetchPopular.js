import { useState, useEffect } from 'react';
import axios from 'axios';

const useFetchPopular = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPopularMovies = async () => {
      try {
        const response = await axios.get('http://localhost:8080/movies/popular');
        if (response && response.data && Array.isArray(response.data.results)) {
          // Correctly set only the top 5 popular movies
          setMovies(response.data.results.slice(0, 5));
        } else {
          throw new Error('Invalid data format');
        }
        setLoading(false);
      } catch (error) {
        setError(error);
        setLoading(false);
      }
    };

    fetchPopularMovies();
  }, []);

  return { movies, loading, error };
};

export default useFetchPopular;