import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';

function SignUpPage() { 
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [error, setError] = useState(''); //error handling msg
    const [confirmPassword, setConfirmPassword] = useState('');
    const history = useNavigate(); // Redirect with history object

    const onChangeHandler = (event) => {
        const { name, value } = event.target;
        switch (name) {
            case 'username':
                setUsername(value);
                break;
            case 'email':
                setEmail(value);
                break;
            case 'password':
                setPassword(value);
                break;
            case 'confirmPassword':
                setConfirmPassword(value);
                break;
            default:
                break;
        }
    };
        

    const onSubmitSignUp = (e) => {
        e.preventDefault(); // Prevent default form submission behavior
        
        try {
            // Check for empty fields
            if (!username || !email || !password) {
                setError('Täytä kaikki kentät.');
                return;
            }

            if (password !== confirmPassword) {
                throw new Error('Salasanat eivät täsmää. ');
            }
            // Handle successful signup
            console.log('Signup successful.');
            history("/user/{userid}");
        } catch (error) {
            // Handle signup error
            console.error('Signup failed:', error);
            setError(error.message || 'Rekisteröitymisessä tapahtui virhe. ');
        }
    };

    return (
        <section className="main-content">
            <div className="content">
                <h2 className="title">Rekisteröidy</h2>
                <div>
                    <form onSubmit={onSubmitSignUp}>
                        <div className='form-group'>
                            <div className="field">
                                <input
                                    type='text'
                                    autoComplete="off"
                                    name="username"
                                    id="username"
                                    value={username}
                                    onChange={onChangeHandler}
                                    placeholder="Käyttäjätunnus"
                                />
                            </div>
                            <div className="field">
                                <input
                                    type='email'
                                    autoComplete="off"
                                    name="email"
                                    id="email"
                                    value={email}
                                    onChange={onChangeHandler}
                                    placeholder="Sähköposti"
                                />
                            </div>

                            <div className="field">
                                <input
                                    type='password'
                                    autoComplete="off"
                                    name="password"
                                    id="password"
                                    value={password}
                                    onChange={onChangeHandler}
                                    placeholder="Salasana"
                                />
                            </div>
                            <div className="field">
                                <input
                                    type='password'
                                    autoComplete="off"
                                    id='confirmPassword'
                                    placeholder="Salasana uudelleen"
                                    value={confirmPassword}
                                    onChange={onChangeHandler}
                                    name="confirmPassword"
                                />
                            </div>
                            {error && <p style={{ color: 'red' }}>{error}</p>}
                            <button type="submit" className="btn-signup" onClick={onSubmitSignUp}>
                                Rekisteröidy
                            </button>
                        </div>
                    </form>

                    <div className="old-user">
                        <h5 className="new">Onko sinulla jo tili? <Link to="/public/login">Kirjaudu sisään</Link></h5>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default SignUpPage;