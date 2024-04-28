import { useState, useEffect } from 'react';
import axios from 'axios';

const useFetchUpcoming = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUpcomingMovies = async () => {
      try {
        const response = await axios.get('http://localhost:8080/movies/upcoming');
        if (response && response.data && Array.isArray(response.data.results)) {
          // Only keep the top 5 upcoming movies
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

    fetchUpcomingMovies();
  }, []);

  return { movies, loading, error };
};

export default useFetchUpcoming;