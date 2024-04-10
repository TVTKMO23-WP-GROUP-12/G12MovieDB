import React, { useState } from 'react';
import './NavBar.css';
import { Link } from 'react-router-dom';

export default function NavBar() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const closeMenu = () => {
    setIsOpen(false);
  };

  return (
    <nav className="navbar">
      <div className="menu-icon" onClick={toggleMenu}>
        HAMBURGER
      </div>
      <ul className={isOpen ? 'nav-menu-mobile active' : 'nav-menu-mobile'}>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/" onClick={closeMenu}>Home</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/user" onClick={closeMenu}>User</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/group" onClick={closeMenu}>Group</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/showtimes" onClick={closeMenu}>Showtimes</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/search" onClick={closeMenu}>Search Portal</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/signup" onClick={closeMenu}>SignUp</Link>
        </li>
      </ul>
    </nav>
  );
}
