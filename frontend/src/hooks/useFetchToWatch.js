import { useState, useEffect } from 'react';

//Fetches to watch movies from the database.

const useFetchToWatch = (id) => {
    const [toWatch, setToWatchMovies] = useState([]);

    useEffect(() => {
      fetch(`http://localhost:8080/movies_to_watch/user/${id}`)
        .then(response => response.json())
        .then(data => {
          setToWatchMovies(data);
        })
        .catch(error => console.error('Error:', error));
    }, [id]);
    
return toWatch;
};

export default useFetchToWatch;