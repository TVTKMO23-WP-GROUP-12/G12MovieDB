package com.group12.moviedb.models;

import jakarta.persistence.*;

@Entity
@Table(name="message_recipient")
public class MessageRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private int recipientId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="user_group_id")
    private Group group;
    
    @Column(name="message_id")
    private int messageId;

    @Column(name="is_read")
    private boolean isRead;

    @SuppressWarnings("unused")
    private MessageRecipient() {}

    public MessageRecipient(Integer id, Integer recipientId, Integer messageId, Boolean isRead) {
        this.id = id;
        this.recipientId = recipientId;
        this.messageId = messageId;
        this.isRead = isRead;
    }

    public int getId() {
        return this.id;
    }

    public int getRecipientId() {
        return this.recipientId;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public User getUser() {
        return this.user;
    }

    public Group getGroup() {
        return this.group;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
