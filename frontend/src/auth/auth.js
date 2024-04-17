import axios from 'axios';

const BASE_URL = 'http://localhost:8080'; //FIXME

const auth = {
	//login function
	login: async (username, password) => {
		try {
			const response = await axios.post(`${BASE_URL}/login`,{ username, password });
			const { token } = response.data;
			localStorage.setItem('token', token); //store token in local storage
			return token;
		} catch (error) {
			console.error('Login failed:', error);
			throw error; 
		}
	},


	//signup function
	signup: async(username, email, password) => {
		try {
			const response = await axios.post(`${BASE_URL}/signup`,{ username, email, password });
			const { token } = response.data;
			localStorage.setItem('token', token); //store token in local storage
			return token;
		} catch (error) {
			console.error('Signup failed:', error);
			throw error; //error handling
		}
	},

	//function to logout
	logout: () => {
		localStorage.removeItem('token'); //remove from storage
	},

	//check if the user is logged in
	isLoggedIn: () => {
		return localStorage.getItem('token') !== null;
		 },

	//get JWT token
	getToken: () => {
		return localStorage.getItem('token');
	}
};

export default auth;


