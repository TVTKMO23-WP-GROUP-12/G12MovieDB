package com.group12.moviedb.models;

import java.time.LocalDateTime;
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
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
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
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
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