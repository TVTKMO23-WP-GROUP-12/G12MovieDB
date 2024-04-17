import React, {useEffect, useState } from 'react';
import auth from '../auth/auth.js';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';


const LoginPage = () => {

	const[username, setUsername] = useState('');
	const[password, setPassword] = useState('');
  	const [error, setError] = useState(''); //error handling msg
    	const history = useNavigate(); // Redirect with history object


    	const handleLogin = async () => {
        try {
            //check for empty fields
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }
            
            await auth.login(username, password); // attempt login

            //Handle successful Login
            console.log('Login successful');
            history('/user');
        } catch (error) {
            // Handle login error
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    };


	return (
		<section className="main-content">
		<div className="content">
		<h2 className="title">Login</h2>
		<div>
		<div className="field">
		<input type='text' autoComplete="off"
		value={username}
		onChange={(e) => setUsername(e.target.value)}
		name="username" id="username" placeholder="Username"/>
		</div>
		<div className="field">
		<input type='password' autoComplete="off"
		value={password}
		onChange={(e) => setPassword(e.target.value)}
		name="password" id="password" placeholder="Password"/>
		</div>
		{error && <p className="text-danger">{error}</p>}
		{/* render error message if exists*/} 
		<button className="btn-login" onClick={handleLogin}>
		Login</button>
		</div>

		<div className="forgot-pass"><Link to="#">Forgot password?</Link></div>

		<div className="new-user"><h5 className="new">Not a member yet? <Link to="/signup">SignUp</Link></h5></div>
		</div>
		</section>
	);
}

export default LoginPage;

