package com.group12.moviedb.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Group> groups;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<GroupMembers> groupMembers;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Favorites> favorites;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<MoviesWatched> moviesWatched;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<MoviesToWatch> moviesToWatch;

    @ManyToMany(mappedBy = "recipient")
    private List<Message> receivedMessages = new ArrayList<>();
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)

    private List<MessageRecipient> messageRecipient;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Followers> followers;
    
    @Column(name="username", unique = true)
    private String username;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;
    
    @Column(unique = true)
    private String login;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "user_description")
    private String userDescription;


    public User() {}


    public User(Integer userId, String username, String email, String password, String userDescription) {
        this.id = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userDescription = userDescription;
    }

    public Integer getId(Integer UserId) {
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

    public String getUserDescription() {
        return userDescription;
    }

    public void setId(Integer userId) {
    this.id = userId;
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

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }



}