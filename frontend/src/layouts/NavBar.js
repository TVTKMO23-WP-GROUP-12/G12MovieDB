import * as React from 'react';
import './NavBar.css';
import { Link } from 'react-router-dom';
import auth from '../auth/auth.js';



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
		//close the menu when clicking a link
		if (event.target.tagName === {Link} && this.state.isMenuOpen) {
			this.setState({ isMenuOpen: false });
		}
	};

	render() {

		const { isAuthenticated, username, isMenuOpen } = this.state;

		return (
			<nav className="navbar">
			<div className="menu-icon" onClick={this.toggleMenu}>
			HAMBURGER
			</div>
			<ul className={isMenuOpen ? 'nav-menu-mobile-active' : 'nav-menu-mobile-closed'}>
			<li className="nav-item">
			<Link to="/" onClick={this.toggleMenu}>Home</Link>
			</li>
			<li className="nav-item">
			<Link to="/user" onClick={this.toggleMenu}>User</Link>
			</li>
			<li className="nav-item">
			<Link to="/group" onClick={this.toggleMenu}>Group</Link>
			</li>
			<li className="nav-item">>
			<Link to="/showtimes" onClick={this.toggleMenu}>Showtimes</Link>
			</li>
			<li className="nav-item">
			<Link to="/search" onClick={this.toggleMenu}>Search Portal</Link>
			</li>
			{isAuthenticated ? (
			<li className="nav-item">
				{username}</li>
			) : (
			<li className="nav-item">
				<Link to="/login" onClick={this.toggleMenu}>Login</Link>
				</li>
			)}
			{isAuthenticated && (
				<li className="nav-item">
				onClick={this.handleLogout}Logout
				</li>
        <li className="nav-item" onClick={closeMenu}>
          <Link to="/signup" onClick={closeMenu}>SignUp</Link>
        </li>
			)}
		</ul>
		</nav>
		);
}

}
export default NavBar;
