import * as React from 'react';
import './NavBar.css';
import { Link, useState } from 'react-router-dom';
import auth from '../auth/auth.js';
import ReactDOM from 'react-dom'
import './NavBar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';



class NavBar extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			isAuthenticated : auth.isLoggedIn(),
			username: '',
			isMenuOpen: false //track the menu visibility
		};
	}
	componentDidMount() {
		// fetch the username if authenticated
		if (this.state.isAuthenticated) {
			this.fetchUsername();
		}
	}

	fetchUsername() { //TODO
	/* axios.get('/user')
	 * .then((response) => {
	 * this.setState(( username: response.data.username });
	 * })
	 * .catch((error) => {
	 * console.error('Error fetching usernme:', error);
	 *})
*/
		//setting a dummy
		this.setState({ username: 'User123' });
	}

	handleLogout = () => {
		auth.logout();
		this.setState({ isAuthenticated: false, username: ''});
	};

	toggleMenu = () => {
		this.setState((prevState) => ({
			isMenuOpen: !prevState.isMenuOpen
		}));
	};

	handleClickOutside = (event) =>  {
		// close the menu when clicking a link
		if (event.target.tagName === "A" && this.state.isMenuOpen) {
			this.setState({ isMenuOpen: false });
		}
	};

	render() {

		const { isAuthenticated, username, isMenuOpen } = this.state;

		return (
			<nav className="navbar">
			<div className="menu-icon" onClick={this.toggleMenu}>
			 <FontAwesomeIcon icon="fa-solid fa-bars" />
			</div>
			<ul className={isMenuOpen ? 'nav-menu-mobile-active' : 'nav-menu-mobile-closed'}>
			<li className="nav-item">
			<Link to="/" onClick={this.toggleMenu}>Etusivu</Link>
			</li>
			<li className="nav-item">
			<Link to="/user" onClick={this.toggleMenu}>Käyttäjä</Link>
			</li>
			<li className="nav-item">
			<Link to="/group" onClick={this.toggleMenu}>Ryhmät</Link>
			</li>
			<li className="nav-item">
			<Link to="/public/showtimes" onClick={this.toggleMenu}>Näytösajat</Link>
			</li>
			<li className="nav-item">
			<Link to="/public/search" onClick={this.toggleMenu}>Hakuportaali</Link>
			</li>
			{isAuthenticated ? (
			<li className="nav-item">
				{username}</li>
			) : (
			<>
			<li className="nav-item">
				<Link to="/login" onClick={this.toggleMenu}>Kirjaudu sisään</Link>
			</li>
			<li className="nav-item" onClick={this.toggleMenu}>
				<Link to="/public/signup" onClick={this.ToggleMenu}>Rekisteröidy</Link>
			</li>
			</>
			)}
			{(
			<li className="nav-item" onClick={this.handleLogout}>Kirjaudu ulos
			</li>		
			)}
			</ul>
			</nav>
		);
	}
}

export default NavBar;
