package com.group12.moviedb.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int messageId;

    @JoinTable(
        name="message_recipient",
        joinColumns=@JoinColumn(name="recipient_id"),
        inverseJoinColumns=@JoinColumn(name="user_id")
    )

    @Column(name="creator_id")
    private int creatorId;
    @OneToMany(mappedBy="message", cascade = CascadeType.ALL)
    private List<MessageRecipient> messageRecipient;

    @Column(name="content")
    private String content;

    @Column(name="parent_message_id")
    private int parentMessageId;
    
    @Column(name="create_date")
    private Date createDate;

    @SuppressWarnings("unused")
    private Message() {}

    public Message(Integer messageId, Integer creatorId, String content, Integer parentMessageId, Date createDate) {
        this.messageId = messageId;
        this.creatorId = creatorId;
        this.content = content;
        this.parentMessageId = parentMessageId;
        this.createDate = createDate;
    }

    public int getId() {
        return this.messageId;
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

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setId(int messageId) {
        this.messageId = messageId;
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

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}
