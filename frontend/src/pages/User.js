import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './User.css';
import defaultProfilePicture from '../media/defaultProfilePicture.png';

function UserPage() {
  const [error, setError] = useState(''); // Error handling message
  const [userInfo, setUserInfo] = useState({
    username: '',
    email: '',
    profilePicture: defaultProfilePicture
  });

  useEffect(() => {
    async function fetchUserData() {
      try {
        // Fetch user data from the backend (is this right?)
        const response = await axios.get("http://localhost:8080/users/userData"); // PLACEHOLDER

        // Set user data to state
        setUserInfo({
          username: response.data.username,
          email: response.data.email,
          profilePicture: defaultProfilePicture // How do we fetch this?
        });
      } catch (error) {
        // Handle error
        console.error('Failed to fetch user data:', error.response ? error.response.data : error.message);
        setError(error.response ? JSON.stringify(error.response.data) : error.message);
      }
    }

    // Call the function to fetch user data
    fetchUserData();
  }, []);


  const handleEdit = () => {
    // Logic for editing user information. Extra task?
    console.log('Edit user clicked');
  };

  const handleDelete = async () => {
    const confirmDelete = window.confirm("Are you sure you want to delete your account? This action cannot be undone, and your data will be permanently removed!");
  
    if (confirmDelete) {
      const secondConfirmDelete = window.confirm("WARNING! Your account will be deleted now. All data will be destroyed and it cannot be recovered. Continue by pressing OK or revert changes by pressing Cancel.");
  
      if (secondConfirmDelete) {
        try {
          console.log('Deleting user account...');
        
          const response = await axios.delete(`http://localhost:8080/users/{userId}`); // PLACEHOLDER

          console.log('User account deleted successfully:', response.data);
          // Here we update the UI or perform any necessary actions after successful deletion
        } catch (error) {
          console.error('Failed to delete user account:', error.response ? error.response.data : error.message);
          setError(error.response ? error.response.data : error.message);
        }
      } else {
        console.log('Delete operation canceled by user.');
      }
    }
  };

  const handleProfilePictureClick = () => {
    document.getElementById('fileInput').click();
  };

  const handleProfilePictureChange = (event) => {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onloadend = () => {
      setUserInfo(prevState => ({
        ...prevState,
        profilePicture: reader.result
      }));
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="user-container">
      {/*Render error message if exists*/}
      {error && <p className="text-danger">{error}</p>}
      <img
        src={userInfo.profilePicture}
        alt="Profile"
        className="profile-picture"
        onClick={handleProfilePictureClick}
      />
      <input
        type="file"
        accept="image/*"
        id="fileInput"
        style={{ display: 'none' }}
        onChange={handleProfilePictureChange}
      />
      <p className="user-info">Username: {userInfo.username}</p>
      <p className="user-info">Email: {userInfo.email}</p>
      <button className="edit-btn" onClick={handleEdit}>Edit</button>
      <button className="delete-btn" onClick={handleDelete}>Delete</button>
    </div>
  );
}

export default UserPage;
