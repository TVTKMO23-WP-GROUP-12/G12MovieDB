import React, { useState } from 'react';
import auth from '../auth/auth.js';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';

const SignUpPage = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState(''); //error handling msg
    const history = useNavigate(); // Redirect with history object

    const handleSignUp = async () => {
     
        try {
            //check for empty fields
            if (!username || !email || !password) {
                setError('Please fill in all fields.');
                return;
            }
            
            if (password !== confirmPassword) {
                throw new Error("Passwords do not match.");
            }

		await auth.signup(username, email, password);

            //Handle successful signup
            console.log('Signup successful.');
            history('/login');
        } catch (error) {
            // Handle signup error
            console.error('Signup failed:', error.response ? error.response.data : error.message);
            setError(error.response ? error.response.data : error.message);
        }
    }

    return (
        <section className="main-content">
            <div className="content">
                <h2 className="title">SignUp</h2>
                <div>

                    <div className="field">
                        <input type='text' autoComplete="off"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)} 
                            name="username" id="username" placeholder="Username" />
                    </div>
                    <div className="field">
                        <input type='email' autoComplete="off"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            name="email" id="email" placeholder="Email" />
                    </div>

                    <div className="field">
                        <input type='password' autoComplete="off"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            name="password" id="password" placeholder="Password" />
                    </div>
                    <div className="field">
                        <input type='password' autoComplete="off"
                            id='confirmPassword' placeholder={"Confirm Password"} value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)} />
                    </div>
                    {/*Render error message if exists*/}
                    {error && <p className="text-danger">{error}</p>}
                    <button className="btn-signup" onClick={handleSignUp}>
                        Sign Up
                    </button>
                </div>

                <div className="old-user">
                    <h5 className="new">Already a member? <Link to="/login">Login</Link></h5>
                </div>
            </div>
        </section>
    );
}

export default SignUpPage;
