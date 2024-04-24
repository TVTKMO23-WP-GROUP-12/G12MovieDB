package com.group12.moviedb.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "user_group")
@JsonIgnoreProperties({"userId"})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="group_id")
    private Integer id;

    @OneToMany(mappedBy="group", cascade = CascadeType.ALL)
    private List<GroupMembers> groupMembers;

    @OneToMany(mappedBy="group", cascade = CascadeType.ALL)
    private List<MessageRecipient> messageRecipient;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="group_name")
    private String groupName;

    @Column(name="group_description")
    private String groupDescription;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    public Group() {}

    public Group(Integer groupId, String groupName, String groupDescription, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getGroupId(Integer groupId) {
        return this.id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public String getGroupDescription() {
        return this.groupDescription;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setGroupId(Integer groupId) {
        this.id = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }


}