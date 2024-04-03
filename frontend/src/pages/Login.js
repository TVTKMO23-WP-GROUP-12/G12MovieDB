import React, {useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';


function LoginPage() {

	const[username, setUsername] = useState('');
	const[password, setPassword] = useState('');
	const navigate = useNavigate();

	async function handleLogin(event) {
		event.preventDefault();
		try {
			const response = await axios.post("http://localhost:8080/login", {
				username: username,
				pw: password,
			});

			const data = response.data;
			if (data.message === "Username not exists") {
				alert("Username not found.");
			} else if (data.message === "Login success") {
				navigate('/user');
			} else {
				alert("Username and Password do not match.");
			}
		} catch (error) {
			console.error(error);
			alert("An error occurred during login.");
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

