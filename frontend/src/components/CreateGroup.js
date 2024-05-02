import React, { Component } from "react";
import './CreateGroup.css';
import axios from 'axios';

class CreateGroupComponent extends Component {
  constructor(props) {
    super(props);

    // Retrieve the user ID from local storage
    const userId = localStorage.getItem('userId');

    this.state = {
      groupName: "",
      groupDescription: "",
      admin: userId, // Set the admin as the current user ID
    };

    this.changeGroupNameHandler = this.changeGroupNameHandler.bind(this);
    this.changeGroupDescriptionHandler = this.changeGroupDescriptionHandler.bind(this);
    this.saveGroup = this.saveGroup.bind(this);
  }

  changeGroupNameHandler(event) {
    this.setState({ groupName: event.target.value });
  }

  changeGroupDescriptionHandler(event) {
    this.setState({ groupDescription: event.target.value });
  }

  saveGroup(e) {
    e.preventDefault();

    const userId = localStorage.getItem('userId');

    const formData = new FormData(); 
    formData.append('groupName', this.state.groupName);
    formData.append('groupDescription', this.state.groupDescription);
  

    const config = {
      headers: {
        'Content-Type': 'multipart/form-data' // Use multipart/form-data for form submissions
      }
    };

    axios.postForm('http://localhost:8080/group', formData, {
      params: { userId: userId }
    }, config)
    .then(response => {
      console.log('Created group successfully');
      this.props.history.push("/groups");
    })
    .catch(error => {
      console.error('Failed to create group:', error);
    });
  };
  
  render() {
    return (
      <div className="groupAdd">
        <form onSubmit={this.saveGroup}>
          <h2>Lisää uusi ryhmä</h2>
          <div>
            <label>Ryhmän nimi</label>
            <input
              type="text"
              value={this.state.groupName}
              onChange={this.changeGroupNameHandler}
            />
          </div>
          <div className="second-div">
            <label>Ryhmän kuvaus</label>
            <input
              type="text"
              value={this.state.groupDescription}
              onChange={this.changeGroupDescriptionHandler}
            />
          </div>
          <button type="submit">Lisää ryhmä</button>
        </form>
      </div>
    );
  }
}

export default CreateGroupComponent;