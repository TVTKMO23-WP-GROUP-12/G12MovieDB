package com.group12.moviedb.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer id;

    @Column(name = "creator_id", columnDefinition = "INTEGER")
    private Integer creatorId;
    
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    
    @Column(name = "parent_message_id", columnDefinition = "INTEGER")
    private Integer parentMessageId;
    
    @CreationTimestamp
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDate createDate;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "message_recipient",
        joinColumns = @JoinColumn(name = "message_id"),
        inverseJoinColumns = @JoinColumn(name = "recipient_id")
    )
    private List<User> recipient = new ArrayList<>();

    public Message() {}

    public Message(Integer messageId, Integer creatorId, String content, Integer parentMessageId, LocalDate createDate) {
        this.id = messageId;
        this.creatorId = creatorId;
        this.content = content;
        this.parentMessageId = parentMessageId;
        this.createDate = createDate;
    }

    public Integer getMessageId(Integer messageId) {
        return this.id;
    }

    public Integer getCreatorId() {
        return this.creatorId;
    }

    public String getContent() {
        return this.content;
    }

    public Integer getParentMessageId() {
        return this.parentMessageId;
    }

    public LocalDate getCreateDate() {
        return this.createDate;
    }

    public void setMessageId(Integer messageId) {
        this.id = messageId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setParentMessageId(Integer parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    
}