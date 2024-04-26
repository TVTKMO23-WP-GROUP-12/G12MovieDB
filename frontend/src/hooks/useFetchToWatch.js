import { useState, useEffect } from 'react';

//Fetches to watch movies from the database.

const useFetchToWatch = (userId) => {
    const [toWatch, setToWatchMovies] = useState([]);

    useEffect(() => {
      fetch(`http://localhost:8080/movies_to_watch/user/${userId}`)
        .then(response => response.json())
        .then(data => {
          setToWatchMovies(data);
        })
        .catch(error => console.error('Error:', error));
    }, [userId]);
    
return toWatch;
};

export default useFetchToWatch;