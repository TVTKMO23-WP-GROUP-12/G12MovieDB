import { useState, useEffect } from 'react';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

const useFetchUser = (id, setUser) => {
  const [user] = useState([]);

  useEffect(() => {
    fetch(`http://localhost:8080/users/${id}`)
      .then(response => response.json())
      .then(data => {
        setUser({ ...data, profilePicture: data.profilePicture || defaultProfilePicture });
        })
      .catch(error => console.error('Error:', error));
  }, [id, setUser]);

  return user;
};

export default useFetchUser;