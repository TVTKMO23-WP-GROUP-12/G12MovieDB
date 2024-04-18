import React, { useState, useEffect } from 'react';
import './GroupsMenu.css';
import { Link } from 'react-router-dom';

export default function GroupsMenu() {
    const [groups, setGroups] = useState([]);
    const [selectedGroup, setSelectedGroup] = useState('All');

    useEffect(() => {
        fetch('http://localhost:8080/group')    //get the groups from the backend
                                                //need to change the url to match our final backend
            .then(response => response.json())
            .then(data => {
                setGroups(data);
            })
            .catch(error => {
                console.error('Error fetching groups:', error);
            });
    }, []); 

    return (
        <div className="groupsmenu-container">
            <div className="groupsmenu-box">
                <h2>Groups</h2>
                <div className="groupsmenu-list">
                {groups.map(group => (
                    <div key={group.id}>
                        <h4><Link to={`/group/${group.id}`}>{group.groupName}</Link></h4>
                        <p>{group.groupDescription}</p>
                        <Link><p className='groupsmenu-join-button'>Liity Ryhmään</p></Link>
                        <hr></hr>
                    </div>
                ))}
                </div>
            </div>
        </div>
    )
}