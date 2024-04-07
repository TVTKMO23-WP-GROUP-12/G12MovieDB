package com.group12.moviedb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Message> findMessageById(@PathVariable("message_id") int messageId) {
        return this.messageRepository.findById(messageId);
    }

    @GetMapping("/messages/sender={creator_id}")
    public Iterable<Message> findMessagesByCreatorId(@PathVariable("creator_id") int creatorId) {
        return this.messageRepository.findByCreatorId(creatorId);
    }

    @GetMapping("/messages/parent={parent_message_id}")
    public Iterable<Message> findMessagesByParentMessageId(@PathVariable("parent_message_id") int parentMessageId) {
        return this.messageRepository.findByParentMessageId(parentMessageId);
    }

    @GetMapping("/messages/recipient={recipient_id}")
    public Iterable<Message> findMessagesByRecipientId(@PathVariable("recipient_id") int recipientId) {
        return this.messageRepository.findByRecipientsId(recipientId);
    }

    @PostMapping("/messages")
    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @PostMapping("/messages/{message_id}")
    public Message updateMessage(@PathVariable("message_id") int messageId, Message message) {
        return this.messageRepository.save(message);
    }

    @DeleteMapping("/messages/{message_id}")
    public Message deleteMessage(@PathVariable("message_id") int messageId) {
        return this.messageRepository.deleteById(messageId);
    }
    
}
