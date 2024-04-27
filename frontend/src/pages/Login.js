import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';
import { setAuthHeader } from '../auth/authContent';

function LoginPage() {
    const [error, setError] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState ('');
    const history = useNavigate();

  const onLoginSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/login', {
        username,
        password,
        email
      });

      const token = response.data; // Backend sends JWT token upon successful login
      localStorage.setItem('token', token); // Store token in local storage

      setError('');
      console.log('Login successful');
      history("/user/{userId}"); // Redirect user
    } catch (error) {
      setError('Invalid username or password');
      console.error('Login failed:', error);
    }
};
            // Redirect 

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