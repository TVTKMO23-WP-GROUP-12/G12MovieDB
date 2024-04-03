package com.group12.moviedb.models;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Group> groups;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<GroupMembers> groupMembers;
    @OneToMany(mappedBy="userId", cascade = CascadeType.ALL)
    private List<Favorites> favorites;
    

    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password; 

    @SuppressWarnings("unused")
    private User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
    return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Group> getGroups() {
        return this.groups;
    }

    public List<Favorites> getFavorites() {
        return this.favorites;
    }

    public void setId(int id) {
    this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

    public User orElse(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }

}
