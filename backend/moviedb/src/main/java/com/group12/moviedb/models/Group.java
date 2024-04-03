package com.group12.moviedb.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name="user_group")
@JsonIgnoreProperties({"userId"})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id")
    private int id;

    @OneToMany(mappedBy="group", cascade = CascadeType.ALL)
    private List<GroupMembers> groupMembers;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="group_name")
    private String groupName;
    @Column(name="group_description")
    private String groupDescription;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="updated_at")
    private Date updatedAt;

    Group() {}

    public Group(Integer id, String groupName, String groupDescription, Date createdAt, Date updatedAt) {
        this.id = id;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return this.id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupDescription() {
        return this.groupDescription;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public User getUserId() {
        return this.user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Group orElse(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElse'");
    }

}
