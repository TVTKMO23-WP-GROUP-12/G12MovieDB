package com.group12.moviedb.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Message> findMessageById(@PathVariable("message_id") Integer messageId) {
        Optional<Message> message = this.messageRepository.findById(messageId);
        return message.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/messages/sender={creator_id}")
    public Iterable<Message> findMessageByCreatorId(@PathVariable("creator_id") Integer creatorId) {
        return this.messageRepository.findByCreatorId(creatorId);
    }

    @GetMapping("/messages/parent={parent_message_id}")
    public Iterable<Message> findMessageByParentMessageId(@PathVariable("parent_message_id") Integer parentMessageId) {
        return this.messageRepository.findByParentMessageId(parentMessageId);
    }

    @GetMapping("/messages/recipient={recipient_id}")
    public Iterable<Message> findMessageByRecipientId(@PathVariable("recipient_id") Integer recipientId) {
        return this.messageRepository.findByRecipientId(recipientId);
    }

    @PostMapping("/messages")
    public Message createMessage(Message message) {
        return this.messageRepository.save(message);
    }

    @PostMapping("/messages/{message_id}")
    public Message updateMessage(@PathVariable("message_id") Integer messageId, Message message) {
        return this.messageRepository.save(message);
    }

    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("message_id") Integer messageId) {
        try {
            messageRepository.deleteById(messageId);
            return ResponseEntity.ok("Message deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete message: " + e.getMessage());
        }
    }
    
}