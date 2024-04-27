package com.group12.moviedb.controller;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.Message;
import com.group12.moviedb.models.MessageRecipient;
import com.group12.moviedb.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.repository.GroupRepository;
import com.group12.moviedb.repository.MessageRecipientRepository;
import com.group12.moviedb.repository.MessageRepository;
import com.group12.moviedb.repository.UserRepository;

@RestController
public class MessageRecipientController {

    private final MessageRecipientRepository messageRecipientRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final MessageRepository messageRepository;

    public MessageRecipientController(  MessageRecipientRepository messageRecipientRepository, 
                                        UserRepository userRepository, 
                                        GroupRepository groupRepository,
                                        MessageRepository messageRepository) {
        this.messageRecipientRepository = messageRecipientRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.messageRepository = messageRepository;

    }

    @GetMapping("/message/recipient")
    public Iterable<MessageRecipient> findAllMessageRecipients() {
        return this.messageRecipientRepository.findAll();
    }

    @GetMapping("/message/recipient/{recipient_id}")
    public MessageRecipient findMessageRecipientById(@PathVariable("recipient_id") Integer recipientId) {
        Optional<MessageRecipient> optionalRecipient = this.messageRecipientRepository.findById(recipientId);
        return optionalRecipient.orElse(null);
    }

    @GetMapping("/message/recipient/user={user_id}")
    public List<MessageRecipient> findMessageRecipientByUserId(@PathVariable Integer userId) {
        User recipient = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (recipient != null) {
            return this.messageRecipientRepository.findByUserId(userId);
        }
        return Collections.emptyList();
    }

    @GetMapping("/message/recipient/message={message_id}")
    public List<MessageRecipient> findMessageRecipientsByMessageId(@PathVariable("message_id") Integer messageId) {
        MessageRecipient messageRecipient = messageRecipientRepository.findById(messageId).orElse(null);
        if (messageRecipient != null) {
            Message message = messageRecipient.getMessage();
            if (message != null) {
                return this.messageRecipientRepository.findByMessageId(message.getId());
            }
        }
        return new ArrayList<>();
    }

    @GetMapping("/message/recipients/read")
    public Iterable<MessageRecipient> findReadMessageRecipient() {
        return this.messageRecipientRepository.findByIsRead(true);
    }

    @GetMapping("/message/recipients/unread")
    public Iterable<MessageRecipient> findUnreadMessageRecipient() {
        return this.messageRecipientRepository.findByIsRead(false);
    }

    @GetMapping("/message/recipients/read/user={user_id}")
    public List<MessageRecipient> findReadMessageRecipientsByUserId(Integer userId) {
        User recipient = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (recipient != null) {
            return this.messageRecipientRepository.findByIsReadAndUserId(true, userId);
        }
        return this.messageRecipientRepository.findByIsReadAndUser(true, null);
    }


   
     @GetMapping("/message/recipient/unread/message={message_id}")
        public Iterable<MessageRecipient> findUnreadMessageRecipientsByMessageId(@PathVariable("message_id") Integer messageId) {
        Message message = messageRepository.findById(messageId)
            .orElseThrow(() -> new NoSuchElementException("Message not found with id: " + messageId));
        
            return this.messageRecipientRepository.findByIsReadAndMessage(false, message);
}


    @PostMapping("/message/recipient")
    public MessageRecipient addOneMessageRecipient(MessageRecipient recipient) {
        return this.messageRecipientRepository.save(recipient);
    }

    @PostMapping("/message/recipient/read")
    public Iterable<MessageRecipient> addReadMessageRecipient(Iterable<MessageRecipient> recipient) {
        for (MessageRecipient messageRecipient : recipient) {
            messageRecipient.setIsRead(true);
        }
        return this.messageRecipientRepository.saveAll(recipient);
    }

    @PostMapping("/message/recipient/unread")
    public Iterable<MessageRecipient> addUnreadMessageRecipients(Iterable<MessageRecipient> recipient) {
        for (MessageRecipient messageRecipient : recipient) {
            messageRecipient.setIsRead(false);
        }
        return this.messageRecipientRepository.saveAll(recipient);
    }

    @PatchMapping("/message/recipient/{recipient_id}")
    public MessageRecipient updateMessageRecipient(@PathVariable("recipient_id") Integer recipientId, @RequestBody MessageRecipient recipient) {
        Optional<MessageRecipient> optionalRecipient = this.messageRecipientRepository.findById(recipientId);
        if (optionalRecipient.isPresent()) {
            MessageRecipient messageRecipient = optionalRecipient.get();
            messageRecipient.setIsRead(recipient.getIsRead());
            return this.messageRecipientRepository.save(recipient);
        }
        return null;
    }

    @PatchMapping("/message/recipient/read")
    public Iterable<MessageRecipient> updateReadMessageRecipients(Iterable<MessageRecipient> recipient) {
        for (MessageRecipient messageRecipient : recipient) {
            messageRecipient.setIsRead(true);
        }
        return this.messageRecipientRepository.saveAll(recipient);
    }

    @PatchMapping("/message/recipient/unread")
    public Iterable<MessageRecipient> updateUnreadMessageRecipient(Iterable<MessageRecipient> recipient) {
        for (MessageRecipient messageRecipient : recipient) {
            messageRecipient.setIsRead(false);
        }
        return this.messageRecipientRepository.saveAll(recipient);
    }

    @DeleteMapping("/message/recipients")
    public void deleteById(Integer recipientId) {
        this.messageRecipientRepository.deleteById(recipientId);
    }

    @DeleteMapping("/message/recipients/{recipient_id}")
    public void deleteMessageRecipient(@PathVariable("recipient_id") Integer userId) {
        User recipient = userRepository.findById(userId).orElse(null);
        if (recipient != null) {
            messageRecipientRepository.deleteByUserId(userId);
        }
    }

    @DeleteMapping("/message/recipients/user={user_id}")
    public void deleteMessageRecipientByUserId(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            this.messageRecipientRepository.deleteByUser(user);
        }
    }

    @DeleteMapping("/message/recipients/group={group_id}")
    public void deleteMessageRecipientsByGroupId(@PathVariable("group_id") Integer groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));    
        if (group != null) {
            this.messageRecipientRepository.deleteByGroup(group);
        }
    }

    @DeleteMapping("/message/recipients/message={message_id}")
    public void deleteMessageRecipientsByMessageId(@PathVariable("message_id") Integer messageId) {
        List<MessageRecipient> messageRecipients = messageRecipientRepository.findByMessageId(messageId);
        if (!messageRecipients.isEmpty()) {
            messageRecipientRepository.deleteAll(messageRecipients);
        }
    }  
    
}