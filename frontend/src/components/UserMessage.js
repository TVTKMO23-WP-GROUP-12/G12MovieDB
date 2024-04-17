import * as React from 'react';
import axios from'axios';
import auth from '../auth/auth.js';

export default class UserMessage extends React.Component {

	constructor(props) {
	super(props);
	this.state = {
		data : null,
		isAuthenticated : auth.isLoggedIn()
	};
	}

	componentDidMount() {
		if (this.state.isAuthenticated) {

			axios.get('/welcome')
			.then((response) => {
				this.setState({data : response.data })
			})
			.catch((error) => {
				console.error('Error fetching data:', error);
		});
	}
}
	
	render() {
		const { data } = this.state;

		return (
				<div>
				{data ? (
					<p>Welcome, {data.username}!</p>
				) : (
			<p>....</p>
		)}
		</div>
		);
	}
}

