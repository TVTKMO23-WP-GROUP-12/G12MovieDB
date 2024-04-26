import { useState, useEffect } from 'react';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

const useFetchUser = (userId) => {
  const [user, setUser] = useState(null);
  const [profilePicture, setProfilePicture] = useState(null);

useEffect(() => {
  const fetchUser = async () => {
    try {
      const userDataResponse = await fetch(`http://localhost:8080/users/${userId}`);
      const userData = await userDataResponse.json();
      setUser(userData);

      const profilePictureResponse = await fetch(`http://localhost:8080/users/${userId}/profile-picture`);
      if (profilePictureResponse.ok) {
        const profilePictureData = await profilePictureResponse.json();
        setProfilePicture(profilePictureData.url);
      } else {
        // Use default profile picture if the user does not have one
        setProfilePicture(defaultProfilePicture);
      }
    } catch (error) {
      console.error('Error fetching user data:', error);
    }
  };
  fetchUser();
}, [userId]);

// Return user object with profile picture
return { user, profilePicture };
};

export default useFetchUser;