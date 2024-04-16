package com.group12.moviedb.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="followers")
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="follower_id")
    private int followerId;

    @Column(name="joined_at")
    private LocalDateTime joinedAt;
    @Column(name="left_at")
    private LocalDateTime leftAt;
    @Column(name="following")
    private boolean following;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Followers() {
    }

    public Followers(int FollowerId, LocalDateTime joinedAt, LocalDateTime leftAt, boolean following, User user) {
        this.followerId = FollowerId;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.following = following;
        this.user = user;
    }

    public int getFollowerId() {
        return followerId;
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

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
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
