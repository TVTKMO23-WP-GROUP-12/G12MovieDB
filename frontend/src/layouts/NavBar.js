import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios'; // Import axios
import auth from '../auth/auth.js';
import './NavBar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const NavBar = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(auth.isLoggedIn());
  const [username, setUsername] = useState('');
  const [profilePicture, setProfilePicture] = useState('');
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false); // Add loading state

  useEffect(() => {
    if (isAuthenticated) {
      setIsLoading(true); // Start loading
      axios.get('/user')
        .then((response) => {
          setUsername(response.data.username);
          setProfilePicture(response.data.profilePicture);
        })
        .catch((error) => {
          console.error('Error fetching user data:', error);
        })
        .finally(() => {
          setIsLoading(false); // Stop loading
        });
    }
  }, [isAuthenticated]);

  const handleLogout = () => {
    auth.logout();
    setIsAuthenticated(false);
    setUsername('');
    setProfilePicture('');
  };

  const toggleMenu = () => {
    setIsMenuOpen((prevIsMenuOpen) => !prevIsMenuOpen);
  };

  const handleClickOutside = (event) => {
    // Close the menu when clicking outside it
    if (!event.target.closest('.navbar')) {
      setIsMenuOpen(false);
    }
  };

  return (
    <nav className="navbar">
      <div className="menu-icon" onClick={toggleMenu}>
        <FontAwesomeIcon icon="fa-solid fa-bars" />
      </div>
      <ul className={isMenuOpen ? 'nav-menu-mobile-active' : 'nav-menu-mobile-closed'}>
        <li className="nav-item">
          <Link to="/" onClick={toggleMenu}>
            Etusivu
          </Link>
        </li>
        {isAuthenticated ? (
          // Render username and profile picture if authenticated
          <li className="nav-item user-info">
            <div className="profile-picture">
              {isLoading ? (
                <span>Loading...</span>
              ) : (
                profilePicture && <img src={profilePicture} alt="Profile" />
              )}
            </div>
            <span>{username}</span>
          </li>
        ) : (
          // Render login and signup links if not authenticated
          <>
            <li className="nav-item">
              <Link to="/login" onClick={toggleMenu}>
                Kirjaudu sisään
              </Link>
            </li>
            <li className="nav-item" onClick={toggleMenu}>
              <Link to="/public/signup">Rekisteröidy</Link>
            </li>
          </>
        )}
        {/* Render logout link if authenticated */}
        {isAuthenticated && (
          <li className="nav-item">
            <button onClick={handleLogout}>Kirjaudu ulos</button>
          </li>
        )}
      </ul>
    </nav>
  );
};

export default NavBar;