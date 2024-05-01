import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';
import { setAuthHeader } from '../auth/authContent';
import useFetchUserId from '../hooks/useFetchUserId';

function LoginPage() {
  const [error, setError] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const history = useNavigate();
  const[currentUser, setCurrentUser] = useState('false')
  const { userId } = useFetchUserId(localStorage.getItem('username'));

 
  const onLoginSubmit = async (e) => {
    e.preventDefault();
  
    try {
      if (!username || !password) {
        setError('Please enter both username and password.');
        return;
      }
  
      const response = await axios.postForm('http://localhost:8080/login', {
        username: username,
        password: password
      });
      const token = response.data.token;

        localStorage.setItem('token', token);
        localStorage.setItem('username', username); // Storing the username in local storage
        setUsername(username);
        setAuthHeader(token);
  
      // Reset error message and redirect user
      setError('');
      console.log('Login successful');
         history(`/`); // Redirect to user page after successful login  
    } catch (error) {
      setError('Invalid username or password');
      console.error('Login failed:', error);
    }
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