import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; // FIXME

const auth = {
    // Login function
    login: async (username, password) => {
        try {
        	 const response = await axios.post(`${BASE_URL}/login`,{ username, password },
            );
            const { token } = response.data;
            localStorage.setItem('token', token); // Store token in local storage
            return token;
        } catch (error) {
            console.error('Login failed:', error);
            throw error; 
        }
    },

    // Signup function
    signup: async (username, email, password) => {
        try {
            const response = await axios.post(`${BASE_URL}/public/signup`, { 
                username, 
                email, 
                password },
            );
            const { token } = response.data;
            localStorage.setItem('token',token); // Store token in local storage
            return token;
        } catch (error) {
            console.error('SignUp failed:', error);
            throw error; // Error handling
        }
    },

    // Function to logout
    logout: () => {
        localStorage.removeItem('token'); // Remove token from storage
    },

    // Check if the user is logged in
    isLoggedIn: () => {
        return localStorage.getItem('token') !== null;
    },

    // Get JWT token
    getToken: () => {
        return localStorage.getItem('token');
    }
};

export default auth;