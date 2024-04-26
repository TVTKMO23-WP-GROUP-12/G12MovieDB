import { useEffect } from 'react';

const useFetchUserId = (username) => {
  useEffect(() => {
    fetch(`http://localhost:8080/users/{username}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
      .then(response => response.json())
      .then(data => {
        localStorage.setItem('userId', data.id);
      })
      .catch(error => console.error('Error:', error));
  }, [token]);
};

export default useFetchUserId;