import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';

function SignUpPage() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState(''); //error handling msg
    const history = useNavigate(); // Redirect with history object

    async function handleSignUp(event) {
        event.preventDefault();
        try {
            //check for empty fields
            if (!username || !email || !password) {
                setError('Please fill in all fields.');
                return;
            }
            
            if (password !== confirmPassword) {
                throw new Error("Passwords do not match.");
            }
            const response = await axios.post("http://localhost:8080/register", {
                username: username,
                email: email,
                password: password,
            });

            //Handle successful signup
            console.log(response.data);
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
                <form>

                    <div className="field">
                        <input type='text' autoComplete="off"
                            value={username}
                            onChange={(event) => {
                                setUsername(event.target.value);
                            }}
                            name="username" id="username" placeholder="Username" />
                    </div>
                    <div className="field">
                        <input type='email' autoComplete="off"
                            value={email}
                            onChange={(event) => {
                                setEmail(event.target.value);
                            }}
                            name="email" id="email" placeholder="Email" />
                    </div>

                    <div className="field">
                        <input type='password' autoComplete="off"
                            value={password}
                            onChange={(event) => {
                                setPassword(event.target.value);
                            }}
                            name="password" id="password" placeholder="Password" />
                    </div>
                    <div className="field">
                        <input type='password' autoComplete="off"
                            id='confirmPassword' placeholder={"Confirm Password"} value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)} />
                    </div>
                    {/*Render error message if exists*/}
                    {error && <p className="text-danger">{error}</p>}
                    <button className="btn-signup" type="submit" onClick={handleSignUp}>
                        Signup
                    </button>
                </form>

                <div className="old-user">
                    <h5 className="new">Already a member? <Link to="/login">Login</Link></h5>
                </div>
            </div>
        </section>
    );
}

export default SignUpPage;
