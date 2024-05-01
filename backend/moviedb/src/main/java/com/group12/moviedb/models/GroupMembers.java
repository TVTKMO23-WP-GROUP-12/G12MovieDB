package com.group12.moviedb.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "members")
public class GroupMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "is_admin")
    private boolean isAdmin;
    
    @CreationTimestamp
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;
    
    @UpdateTimestamp
    @Column(name = "left_at")
    private LocalDateTime leftAt;

  
    public GroupMembers() {}


    public GroupMembers(Integer memberId, Boolean isAdmin, LocalDateTime joinedAt, LocalDateTime leftAt) {
        this.id = memberId;
        this.isAdmin = isAdmin;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
    }

    public Integer getGroupMemberId() {
        return this.id;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public LocalDateTime getJoinedAt() {
        return this.joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return this.leftAt;
    }

    public User getUser() {
        return this.user;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setGroupMemberId(Integer memberId) {
        this.id = memberId;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}