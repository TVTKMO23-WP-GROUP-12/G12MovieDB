import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './LoginSignUp.css';

function LoginPage() {
    const [error, setError] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [login, setLogin] = useState('');
    const history = useNavigate();

    let isLoggedIn = false;

    const onChangeHandler = (event) => {
            const { name, value } = event.target;
            switch (name) {
                case 'login':
                    setLogin(value);
                    break;
                case 'password':
                    setPassword(value);
                    break;
                default:
                    break;
            }
        };
            


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
                <form onSubmit={onSubmitLogin}>
                    <div className='form-group'>    
                        <div className="field">
                            <input
                                type="login"
                                autoComplete="off"
                                name="login"
                                value={login}
                                onChange={onChangeHandler}
                                id="username"
                                placeholder="Käyttäjätunnus"
                            />
                        </div>
                        <div className="field">
                            <input
                                type="password"
                                autoComplete="off"
                                name="password"
                                id="password"
                                value={password}
                                onChange={onChangeHandler}
                                placeholder="Salasana"
                            />
                        </div>
                        {error && <p style={{ color: 'red' }}>{error}</p>}
                        <button type="submit" className="btn-login" onClick={onSubmitLogin}>
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

};

export default LoginPage;
