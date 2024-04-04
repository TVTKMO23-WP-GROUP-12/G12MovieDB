package com.group12.moviedb.models;

import java.util.Date;

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
    private Date joinedAt;
    
    @Column(name="left_at")
    private Date leftAt;

    @SuppressWarnings("unused")
    private GroupMembers() {}

    public GroupMembers(Integer memberId, Boolean isAdmin, Date joinedAt, Date leftAt) {
        this.id = memberId;
        this.isAdmin = isAdmin;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
    }

    public int getGroupMembersId() {
        return id;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public Date getLeftAt() {
        return leftAt;
    }

    public User getUserId() {
        return user;
    }

    public Group getGroupId() {
        return group;
    }

    public void setGrouMembersId(int memberId) {
        this.id = memberId;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setLeftAt(Date leftAt) {
        this.leftAt = leftAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
