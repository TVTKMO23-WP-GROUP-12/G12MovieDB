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

    public int getId() {
        return this.id;
    }

    public boolean getIsAdmin() {
        return this.isAdmin;
    }

    public Date getJoinedAt() {
        return this.joinedAt;
    }

    public Date getLeftAt() {
        return this.leftAt;
    }

    public int getUserId() {
        return this.user.getId();
    }

    public int getGroupId() {
        return this.group.getId();
    }

    public void setId(int memberId) {
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
