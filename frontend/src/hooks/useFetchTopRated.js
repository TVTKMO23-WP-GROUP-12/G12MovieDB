import { useState, useEffect } from 'react';
import axios from 'axios';

const useFetchTopRated = () => {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTopRatedMovies = async () => {
      try {
        const response = await axios.get('http://localhost:8080/movies/top-rated');
        if (response && response.data && Array.isArray(response.data.results)) {
          // Only set the top 5 top-rated movies, correct the oversight of setting the movies state twice
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

    fetchTopRatedMovies();
  }, []);

  return { movies, loading, error };
};

export default useFetchTopRated;