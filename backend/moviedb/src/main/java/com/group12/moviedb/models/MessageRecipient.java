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
    private User recipient;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="user_group_id")
    private Group group;
    
    @ManyToOne
    @JoinColumn(name="message_id")
    private Message message;

    @Column(name="is_read")
    private boolean isRead;

    @SuppressWarnings("unused")
    private MessageRecipient() {}

    public MessageRecipient(Integer id, User recipient, Message message, Boolean isRead) {
        this.id = id;
        this.recipient = recipient;
        this.message = message;
        this.isRead = isRead;
    }

    public int getId() {
        return this.id;
    }

    public User getRecipientId() {
        return this.recipient;
    }

    public Message getMessage() {
        return this.message;
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

    public void setRecipientId(User recipient) {
        this.recipient = recipient;
    }

    public void setMessage(Message message) {
        this.message = message;
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