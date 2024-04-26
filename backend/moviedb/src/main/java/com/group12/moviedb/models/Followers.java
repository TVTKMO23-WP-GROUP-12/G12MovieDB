package com.group12.moviedb.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "followers") // with @Table we give custom table name
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follower_id") //@Column let's us customize the database column name
    private Integer id;

    @CreationTimestamp
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    
    @CreationTimestamp
    @Column(name = "left_at")
    private LocalDateTime leftAt;
    
    @Column(name = "following")
    private boolean following;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Followers() {
    }

    public Followers(Integer followerId, LocalDateTime joinedAt, LocalDateTime leftAt, boolean following, User user) {
        this.id = followerId;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.following = following;
        this.user = user;
    }

    public Integer getFollowerId() {
        return id;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public boolean isFollowing() {
        return following;
    }

    public User getUser() {
        return user;
    }

    public void setFollowerId(Integer followerId) {
        this.id = followerId;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public void setUser(User user) {
        this.user = user;
    }

}