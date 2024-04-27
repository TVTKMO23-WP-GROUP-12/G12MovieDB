import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';
import { setAuthHeader } from '../auth/authContent';

function LoginPage() {
  const [error, setError] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const history = useNavigate();
  const[currentUser, setCurrentUser] = useState('')
 
  const onLoginSubmit = async (e) => {
    e.preventDefault();

    try {

        const response = "POST"('http://localhost:8080/login', JSON.stringify({
        username: username,
        password: password
      }));

      const token = response.data.token; // Assuming backend sends JWT token upon successful login
      localStorage.setItem('token', token); // Store token in local storage
      setAuthHeader(token); // Set JWT token as authorization header for future requests


      setError('');
      console.log('Login successful');
      setCurrentUser(true);
      history("/user/currentUser"); // Redirect user
    } catch (error) {
      setError('Invalid username or password');
      console.error('Login failed:', error);
    }
};
            // Redirect 
    const onSubmitLogin = (e) => {
        e.preventDefault(); // Prevent default form submission behavior

        try {
            if (!login || !password) {
                setError('Please enter both username and password.');
                return;
            }
        } catch (error) {
            setError('Invalid username or password');
        }
            // Perform login operation here
            console.log('Login successful');
            localStorage.setItem('username', login);
            isLoggedIn = true;
            history("/"); // Redirect to user page after successful login  

       
    };

  return (
    <section className="main-content">
      <div className="content">
        <h2 className="title">Kirjaudu sisään</h2>
        <form onSubmit={onLoginSubmit}>
          <div className='form-group'>    
            <div className="field">
              <input
                type="text"
                autoComplete="off"
                name="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                id="username"
                placeholder="Käyttäjätunnus"
              />
            </div>
            <div className="field">
              <input
                type="password"
                autoComplete="off"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                id="password"
                placeholder="Salasana"
              />
            </div>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit" className="btn-login">
              Kirjaudu
            </button>
          </div>
        </form>
        <div className="forgot-pass">
          <Link to="#">Unohtuiko salasana?</Link>
        </div>
        <div className="new-user">
          <h5 className="new">
            Eikö sinulla ole tiliä? <Link to="/signup">Rekisteröidy</Link>
          </h5>
        </div>
      </div>
    </section>
  );
}

export default LoginPage;