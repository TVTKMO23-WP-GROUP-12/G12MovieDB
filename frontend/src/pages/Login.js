import React, {useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';


function LoginPage() {

	const[username, setUsername] = useState('');
	const[password, setPassword] = useState('');
  	const [error, setError] = useState(''); //error handling msg
    	const history = useNavigate(); // Redirect with history object

    	async function handleLogin(event) {
        event.preventDefault();
        try {
            //check for empty fields
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }
            
            const response = await axios.post("http://loalhost:8080/login", {
                username: username,
                password: password,
            });

            //Handle successful Login
            console.log('Login successful:', response.data);
            history('/login');
        } catch (error) {
            // Handle login error
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError('Invalid username or password.');
        }
    }


	return (
		<section className="main-content">
		<div className="content">
		<h2 className="title">Login</h2>
		<form>
		<div className="field">
		<input type='text' autocomplete="off"
		value={username}
		onChange={(event) => {
			setUsername(event.target.value);
		}}
		name="username" id="username" placeholder="Username"></input>
		</div>
		<div className="field">
		<input type='password' autocomplete="off"
		value={password}
		onChange={(event) => {
			setPassword(event.target.value);
		}}
		name="password" id="password" placeholder="Password"></input>
		</div>
		{error && <p className="text-danger">{error}</p>}
		{/* render error message if exists*/} 
		<button className="btn-login" type="submit" onClick={handleLogin}>
		Login</button>
		</form>

		<div className="forgot-pass"><Link to="#">Forgot password?</Link></div>

		<div className="new-user"><h5 className="new">Not a member yet? <Link to="/signup">SignUp</Link></h5></div>
		</div>
		</section>
	);
}

export default LoginPage;

