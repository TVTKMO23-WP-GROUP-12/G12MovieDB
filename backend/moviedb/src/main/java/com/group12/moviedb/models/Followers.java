package com.group12.moviedb.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "followers")
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

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

    public Followers(LocalDateTime joinedAt, LocalDateTime leftAt, boolean following, User user) {
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.following = following;
        this.user = user;
    }

    public int getId() {
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

    public void setId(int id) {
        this.id = id;
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

    public Followers orElse(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }

}
