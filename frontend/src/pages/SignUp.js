import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';
import { request, setAuthHeader } from '../auth/auth-headers';

function SignUpPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();

    const onSubmitSignUp = async (e) => {
        e.preventDefault();

        try {
            if (!username || !email || !password) {
                setError('Please fill in all fields.');
                return;
            }

            if (password !== confirmPassword) {
                setError('Passwords do not match.');
                return;
            }

            const response = await axios.post('http://localhost:8080/signup', {
                username,
                password,
                email
              });

            // Assuming the response contains a token
            setAuthHeader(response.data.token);

            // Redirect to the user's profile page
            history(`/user/${response.data.userId}`);
        } catch (error) {
            setError('Registration failed. Please try again later.');
            console.error('Registration error:', error);
        }
    };

    return (
        <section className="main-content">
            <div className="content">
                <h2 className="title">Sign Up</h2>
                <form onSubmit={onSubmitSignUp}>
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
                        <button type="submit" className="btn-login">Sign Up</button>
                    </div>
                </form>
                <div className="old-user">
                    <h5 className="new">Already have an account? <Link to="/login">Login</Link></h5>
                </div>
            </div>
        </section>
    );
}

export default SignUpPage;