package com.group12.moviedb.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name="members")
public class GroupMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    @Column(name="is_admin")
    private boolean isAdmin;
    
    @Column(name="joined_at")
    private LocalDateTime joinedAt;
    
    @Column(name="left_at")
    private LocalDateTime leftAt;

    @SuppressWarnings("unused")
    private GroupMembers() {}

    public GroupMembers(Integer memberId, Boolean isAdmin, LocalDateTime joinedAt, LocalDateTime leftAt) {
        this.id = memberId;
        this.isAdmin = isAdmin;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
    }

    public int getGroupMembersId() {
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

    public User getUserId() {
        return this.user;
    }

    public Group getGroupId() {
        return this.group;
    }

    public void setGroupMembersId(int memberId) {
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
