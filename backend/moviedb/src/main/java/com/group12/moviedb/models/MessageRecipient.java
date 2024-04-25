package com.group12.moviedb.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "message_recipient")
public class MessageRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipient_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    private Group group;
    
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "is_read")
    private boolean isRead;

    public MessageRecipient() {}

    public MessageRecipient(Integer recipientId, User recipient, Message message, Boolean isRead) {
        this.id = recipientId;
        this.user = recipient;
        this.message = message;
        this.isRead = isRead;
    }

    public Integer getRecipientId(Integer recipientId) {
        return this.id;
    }

    public User getUser(User recipient) {
        return this.user;
    }

    public Message getMessage() {
        return this.message;
    }

    public boolean getIsRead() {
        return this.isRead;
    }
    public Group getGroup() {
        return this.group;
    }

    public void setRecipientId(Integer recipientId) {
        this.id = recipientId;
    }

    public void setUser(User recipient) {
        this.user = recipient;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}