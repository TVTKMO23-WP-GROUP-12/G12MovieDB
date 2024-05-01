import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import auth from '../auth/auth';
import './NavBar.css';
import Showtimes from '../pages/Showtimes.js';
import Search from '../pages/Search.js';
import Group from '../pages/Group.js';
import User from '../pages/User.js';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';

const NavBar = () => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [username, setUsername] = useState('');
  const [profilePicture, setProfilePicture] = useState('');
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const history = useNavigate();
  const userId = localStorage.getItem('userId');

  useEffect(() => {
    if (isAuthenticated) {
      setIsLoading(true);
      axios.get('/user')
        .then((response) => {
          setUsername(response.data.username);
          setProfilePicture(response.data.profilePicture);
        })
        .catch((error) => {
          console.error('Error fetching user data:', error);
        })
        .finally(() => {
          setIsLoading(false);
        });
    }
  }, [isAuthenticated]);

 
  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    setUsername('');
    setProfilePicture('');  
    // Redirect to login page
    history('/login');
    // Close the menu on logout
    setIsMenuOpen(false);
    // Make a request to backend to logout
    axios.post('/logout')
      .then(() => {
        setIsAuthenticated(false);
        console.log('User logged out successfully');
      })
      .catch((error) => {
        console.error('Error logging out:', error);
      });
  };
  

  const toggleMenu = () => {
    setIsMenuOpen((prevIsMenuOpen) => !prevIsMenuOpen);
  };

  const handleClickOutside = (event) => {
    if (!event.target.closest('.navbar')) {
      setIsMenuOpen(false);
    }
  };

  return (
    <nav className="navbar">
      <div className="menu-icon" onClick={toggleMenu}>
        <FontAwesomeIcon icon={faBars} />
      </div>
      <ul className={isMenuOpen ? 'nav-menu-mobile-active' : 'nav-menu-mobile-closed'} onClick={handleClickOutside}>
        <li className="nav-item">
          <Link to="/" onClick={toggleMenu}>
            Etusivu
          </Link>
        </li>
        <li className="nav-item">
          <Link to={`/user/${userId}`} onClick={toggleMenu}>
            Oma sivu
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/public/showtimes" onClick={toggleMenu}>
            Näytösajat
          </Link>
        </li>
        <li className="nav-item">
          <Link to="/public/search" onClick={toggleMenu}>
            Hakuportaali
          </Link>
        </li>
        {isAuthenticated ? (
          <>
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

            <li className="nav-item">
              <button onClick={handleLogout}>Kirjaudu ulos</button>
            </li>
          </>
        ) : (
          <>
          <hr></hr>
            <li className="nav-item">
              <Link to="/login" onClick={toggleMenu}>
                Kirjaudu sisään
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/signup" onClick={toggleMenu}>
                Rekisteröidy
              </Link>
            </li>
          </>
        )}
      </ul>
    </nav>
  );
}

export default NavBar;