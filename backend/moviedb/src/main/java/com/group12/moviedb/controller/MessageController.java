package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Message;
import com.group12.moviedb.repository.MessageRepository;

@RestController
public class MessageController {
    
    private final MessageRepository messageRepository;
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/messages")
    public Iterable<Message> findAllMessages() {
        return this.messageRepository.findAll();
    }

    @GetMapping("/messages/{message_id}")

    public Message findMessageById(int messageId) {
        return this.messageRepository.findById(messageId);
    }

    @GetMapping("/messages/sender={creator_id}")
    public Iterable<Message> findMessagesByCreatorId(int creatorId) {
        return this.messageRepository.findByCreatorId(creatorId);
    }

    @GetMapping("/messages/parent={parent_message_id}")
    public Iterable<Message> findMessagesByParentMessageId(int parentMessageId) {
        return this.messageRepository.findByParentMessageId(parentMessageId);
    }

    @GetMapping("/messages/recipient={recipient_id}")
    public Iterable<Message> findMessagesByRecipientId(int recipientId) {
        return this.messageRepository.findByRecipientId(recipientId);
    }

    @GetMapping("/messages/read")
    public Iterable<Message> findReadMessages() {
        return this.messageRepository.findByRead(true);
    }

    @GetMapping("/messages/unread")
    public Iterable<Message> findUnreadMessages() {
        return this.messageRepository.findByRead(false);
    }

    @PostMapping("/messages")
    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @PostMapping("/messages/{message_id}")
    public Message updateMessage(int messageId, Message message) {
        return this.messageRepository.save(message);
    }

    @DeleteMapping("/messages/{message_id}")
    public Message deleteMessage(int messageId) {
        return this.messageRepository.deleteById(messageId);
    }
    
}
