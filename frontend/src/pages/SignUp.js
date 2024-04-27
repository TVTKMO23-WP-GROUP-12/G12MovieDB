import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';
import { setAuthHeader } from '../auth/authContent';

function SignUpPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');
  const history = useNavigate();

  const onSignUpSubmit = async (e) => {
    e.preventDefault();
  
    try {
      if (!username || !password || !email) {
        setError('Täytä kaikki kentät.');
        return;
      }
      const response = await axios.postForm('http://localhost:8080/signup', {
        username: username,
        password: password,
        email : email
      });

      console.log('Signup successful');
      history('/login');
    } catch (error) {
      setError('Signup failed. Please try again later.');
      console.error('Signup error:', error);
    }
  };

  return (
    <section className="main-content">
      <div className="content">
        <h2 className="title">Sign Up</h2>
        <form onSubmit={onSignUpSubmit}>
          <div className="form-container">
            <div className="form-field">
              <input
                type="text"
                autoComplete="off"
                name="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                placeholder="Username"
              />
            </div>
            <div className="form-field">
              <input
                type="email"
                autoComplete="off"
                name="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Email"
              />
            </div>
            <div className="form-field">
              <input
                type="password"
                autoComplete="off"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Password"
              />
            </div>
            <div className="form-field">
              <input
                type="password"
                autoComplete="off"
                name="confirmPassword"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Confirm Password"
              />
            </div>
            {error && <p className="error-message">{error}</p>}
            <button type="submit" className="btn-login">
              Sign Up
            </button>
          </div>
        </form>
        <div className="old-user">
          <h5 className="new">
            Already have an account? <Link to="/login">Login</Link>
          </h5>
        </div>
      </div>
    </section>
  );
}

export default SignUpPage;