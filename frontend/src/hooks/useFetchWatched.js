import { useState, useEffect } from 'react';

//Fetches watched movies from the database.

const useFetchWatched = (userId) => {
    const [watched, setWatchedMovies] = useState([]);

    useEffect(() => {
      fetch(`http://localhost:8080/movies_watched/user/${userId}`)
        .then(response => response.json())
        .then(data => {
          setWatchedMovies(data);
        })
        .catch(error => console.error('Error:', error));
    }, [userId]);
    
return watched;
};

export default useFetchWatched;