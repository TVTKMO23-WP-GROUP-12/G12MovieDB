package com.group12.moviedb.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="creator_id", columnDefinition = "INTEGER")
    private int creatorId;
    
    @Column(name="content", columnDefinition = "TEXT")
    private String content;
    
    @Column(name="parent_message_id", columnDefinition = "INTEGER")
    private int parentMessageId;
    
    @Column(name = "create_date", columnDefinition = "TIMESTAMP")
    private LocalDate createDate;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "message_recipient",
        joinColumns = @JoinColumn(name = "message_id"),
        inverseJoinColumns = @JoinColumn(name = "recipient_id")
    )
    private List<User> recipients = new ArrayList<>();

    @SuppressWarnings("unused")
    private Message() {}

    public Message(Integer id, Integer creatorId, String content, Integer parentMessageId, LocalDate createDate) {
        this.id = id;
        this.creatorId = creatorId;
        this.content = content;
        this.parentMessageId = parentMessageId;
        this.createDate = createDate;
    }

    public int getId() {
        return this.id;
    }

    public int getCreatorId() {
        return this.creatorId;
    }

    public String getContent() {
        return this.content;
    }

    public int getParentMessageId() {
        return this.parentMessageId;
    }

    public LocalDate getCreateDate() {
        return this.createDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setParentMessageId(int parentMessageId) {
        this.parentMessageId = parentMessageId;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    
}
