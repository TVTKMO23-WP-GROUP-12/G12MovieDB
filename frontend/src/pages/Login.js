import React, { useState } from 'react';
import auth from '../auth/auth.js';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }
            await auth.login(username, password);
            console.log('Login successful');
            history('/'); // Redirect to home page after successful login
        } catch (error) {
            setError('Invalid username or password');
        }
    };

    return (
        <section className="main-content">
            <div className="content">
                <h2 className="title">Login</h2>
                <form onSubmit={handleSubmit}>
                <div className='form-group'>    
                <div className="field">
                        <input
                            type="text"
                            autoComplete="off"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            name="username"
                            id="username"
                            placeholder="Username"
                        />
                    </div>
                    <div className="field">
                        <input
                            type="password"
                            autoComplete="off"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            name="password"
                            id="password"
                            placeholder="Password"
                        />
                    </div>
                    {error && <p style={{ color: 'red' }}>{error}</p>}
                    <button type="submit" className="btn-login">
                        Login
                    </button>
                    </div>
                </form>
                <div className="forgot-pass">
                    <Link to="#">Forgot password?</Link>
                </div>
                <div className="new-user">
                    <h5 className="new">
                        Not a member yet? <Link to="/signup">SignUp</Link>
                    </h5>
                </div>
            </div>
        </section>
    );
};

export default LoginPage;