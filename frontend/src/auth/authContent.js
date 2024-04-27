

import axios from 'axios';


export const getAuthToken = () => {
    return window.localStorage.getItem('auth_token');
};

export const setAuthHeader = (token) => {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
};

axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.headers.post['Content-Type'] = 'application/json';

export const request = (method, url, data) => {

    let headers = {};
    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = {'Authorization': `Bearer ${getAuthToken()}`};
    }

    return axios({
        method: method,
        url: url,
        headers: headers,
        data: data});
};





const Login = (e, username, password) => {
    e.preventDefault();
    const formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('password', password);

    request("POST", "/login", formData)
        .then((response) => {
            setAuthHeader(response.data.token);
        })
        .catch((error) => {
            console.error('Login failed:', error);
            setAuthHeader(null);
        });
};

const SignUp = (event, username, email, password) => {
    event.preventDefault();
    const formData = new URLSearchParams();
    formData.append('username', username);
    formData.append('email', email);
    formData.append('password', password);

    request("POST", "/signup", formData)
        .then((response) => {
            setAuthHeader(response.data.token);
         
        })
        .catch((error) => {
            console.error('Signup failed:', error);
            setAuthHeader(null);
        });
};

export {Login, SignUp};