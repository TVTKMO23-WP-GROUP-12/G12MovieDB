import ReactDOM from 'react-dom'
import React, { useState } from 'react';
import './NavBar.css';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

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
        <FontAwesomeIcon icon="fa-solid fa-bars" />
      </div>
      <ul className={isOpen ? 'nav-menu-mobile active' : 'nav-menu-mobile'}>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/" onClick={closeMenu}>Etusivu</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/user" onClick={closeMenu}>Käyttäjä</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/group" onClick={closeMenu}>Ryhmät</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/public/showtimes" onClick={closeMenu}>Näytösajat</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/public/search" onClick={closeMenu}>Hakuportaali</Link>
        </li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/public/signup" onClick={closeMenu}>Rekisteröidy</Link>
        </li>
      </ul>
    </nav>
  );
}
