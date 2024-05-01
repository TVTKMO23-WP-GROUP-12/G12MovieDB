import { useState, useEffect } from 'react';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

const useFetchUser = (userId, onUserLoaded) => {
  const [user, setUser] = useState(null);
  const [profilePicture, setProfilePicture] = useState(null);
  
  useEffect(() => {
    const fetchUser = async () => {
      try {
        const userDataResponse = await fetch(`http://localhost:8080/users/${userId}`);
        const userData = await userDataResponse.json();
        setUser(userData);
        // Use default profile picture
        setProfilePicture(defaultProfilePicture);
        // Call the callback function when data fetching is complete
        if (onUserLoaded) {
          onUserLoaded();
        }
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    };
    fetchUser();
  }, [userId, onUserLoaded]);
  // Return user object with profile picture
  return { user, profilePicture };
};

export default useFetchUser;