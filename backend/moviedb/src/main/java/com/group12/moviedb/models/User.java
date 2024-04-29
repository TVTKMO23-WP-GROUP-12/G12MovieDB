package com.group12.moviedb.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group12.moviedb.dataSources.SignUpDto;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"groups", "groupMembers"}) // FIXME Added here so group menu works. Does this break the code?
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Group> group;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<GroupMembers> groupMembers;

    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Favorites> favorites;

    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<MoviesWatched> moviesWatched;
    
    @JsonBackReference
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<MoviesToWatch> moviesToWatch;

    @ManyToMany(mappedBy = "recipient")
    private List<Message> receivedMessages = new ArrayList<>();
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)

    private List<MessageRecipient> messageRecipient;
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Followers> followers;
    
    @Column(name="username", unique = true, updatable = false, length = 128)
    private String username;

    @Column(name="email", unique = true, length = 128)
    private String email;
    
    @Column(name="password", length = 255)
    private String password;
    
    @Column(unique = true)
    private String login;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @UpdateTimestamp
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "user_description", nullable = true, length = 255)
    private String userDescription;

    @Column(name ="profilePicture", nullable = true, length = 64)
    private String profilePicture;

    public User() {}


    public User(Integer userId, String username, String email, String password, String userDescription, String profilePicture) {
        this.id = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userDescription = userDescription;
        this.profilePicture = profilePicture;
    }

    public Integer getId(Integer UserId) {
    return this.id;
    }

    public Integer getId() {
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
        return this.group;
    }

    public String profilepicture() {
        return this.profilePicture;
    }

    public List<Favorites> getFavorites() {
        return this.favorites;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getProfilePicture() {
        return profilePicture;
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
    
    public void setGroups(List<Group> group) {
        this.group = group;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
        
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    public void addNewUser(String string, SignUpDto signUpDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNewUser'");
    }


}