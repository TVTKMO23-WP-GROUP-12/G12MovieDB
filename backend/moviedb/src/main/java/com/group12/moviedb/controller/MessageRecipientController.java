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

    @GetMapping("/message/recipients")
    public Iterable<MessageRecipient> findAllMessageRecipients() {
        return this.messageRecipientRepository.findAll();
    }

    @GetMapping("/message/recipients/{recipient_id}")
    public MessageRecipient findMessageRecipientById(@PathVariable("recipient_id") int recipientId) {
        Optional<MessageRecipient> optionalRecipient = this.messageRecipientRepository.findById(recipientId);
        return optionalRecipient.orElse(null);
    }

    @GetMapping("/message/recipients/user={user_id}")
    public List<MessageRecipient> findMessageRecipientsByUserId(@PathVariable int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            return this.messageRecipientRepository.findByUser(user);
        }
        return Collections.emptyList();
    }

    @GetMapping("/message/recipients/message={message_id}")
    public List<MessageRecipient> findMessageRecipientsByMessageId(@PathVariable("message_id") int messageId) {
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
    public Iterable<MessageRecipient> findReadMessageRecipients() {
        return this.messageRecipientRepository.findByIsRead(true);
    }

    @GetMapping("/message/recipients/unread")
    public Iterable<MessageRecipient> findUnreadMessageRecipients() {
        return this.messageRecipientRepository.findByIsRead(false);
    }

    @GetMapping("/message/recipients/read/user={user_id}")
    public List<MessageRecipient> findReadMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            return this.messageRecipientRepository.findByIsReadAndUser(true, user);
        }
        return this.messageRecipientRepository.findByIsReadAndUser(true, null);
    }

    @GetMapping("/message/recipients/unread/user={user_id}")
    public List<MessageRecipient> findUnreadMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            return this.messageRecipientRepository.findByIsReadAndUser(false, user);
        }
        return this.messageRecipientRepository.findByIsReadAndUser(false, null);
    }

    @GetMapping("/message/recipients/read/message={message_id}")
    public Iterable<MessageRecipient> findReadMessageRecipientsByMessageId(@PathVariable("message_id") int messageId) {
        Optional<Message> messages = messageRepository.findById(messageId);
        if (!messages.isEmpty()) {
            Message message = messages.get();
            return this.messageRecipientRepository.findByIsReadAndMessage(true, message);
        } else {
            return Collections.emptyList();
        }
    }
    
    @GetMapping("/message/recipients/unread/message={message_id}")
    public Iterable<MessageRecipient> findUnreadMessageRecipientsByMessageId(@PathVariable("message_id") int messageId) {
        Optional<Message> messages = messageRepository.findById(messageId);
        if (!messages.isEmpty()) {
            Message message = messages.get();
            return this.messageRecipientRepository.findByIsReadAndMessage(false, message);
        } else {
            return Collections.emptyList();
        }
    }    

    @PostMapping("/message/recipients")
    public MessageRecipient addOneMessageRecipient(MessageRecipient messageRecipient) {
        return this.messageRecipientRepository.save(messageRecipient);
    }

    @PostMapping("/message/recipients/read")
    public Iterable<MessageRecipient> addReadMessageRecipients(Iterable<MessageRecipient> messageRecipients) {
        for (MessageRecipient messageRecipient : messageRecipients) {
            messageRecipient.setIsRead(true);
        }
        return this.messageRecipientRepository.saveAll(messageRecipients);
    }

    @PostMapping("/message/recipients/unread")
    public Iterable<MessageRecipient> addUnreadMessageRecipients(Iterable<MessageRecipient> messageRecipients) {
        for (MessageRecipient messageRecipient : messageRecipients) {
            messageRecipient.setIsRead(false);
        }
        return this.messageRecipientRepository.saveAll(messageRecipients);
    }

    @PatchMapping("/message/recipients/{recipient_id}")
    public MessageRecipient updateMessageRecipient(@PathVariable("recipient_id") int recipientId, @RequestBody MessageRecipient messageRecipient) {
        Optional<MessageRecipient> optionalRecipient = this.messageRecipientRepository.findById(recipientId);
        if (optionalRecipient.isPresent()) {
            MessageRecipient recipient = optionalRecipient.get();
            recipient.setIsRead(messageRecipient.getIsRead());
            return this.messageRecipientRepository.save(recipient);
        }
        return null;
    }

    @PatchMapping("/message/recipients/read")
    public Iterable<MessageRecipient> updateReadMessageRecipients(Iterable<MessageRecipient> messageRecipients) {
        for (MessageRecipient messageRecipient : messageRecipients) {
            messageRecipient.setIsRead(true);
        }
        return this.messageRecipientRepository.saveAll(messageRecipients);
    }

    @PatchMapping("/message/recipients/unread")
    public Iterable<MessageRecipient> updateUnreadMessageRecipients(Iterable<MessageRecipient> messageRecipients) {
        for (MessageRecipient messageRecipient : messageRecipients) {
            messageRecipient.setIsRead(false);
        }
        return this.messageRecipientRepository.saveAll(messageRecipients);
    }

    @DeleteMapping("/message/recipients")
    public void deleteById(int id) {
        this.messageRecipientRepository.deleteById(id);
    }

    @DeleteMapping("/message/recipients/{recipient_id}")
    public void deleteMessageRecipient(@PathVariable("recipient_id") int recipientId) {
        User recipient = userRepository.findById(recipientId).orElse(null);
        if (recipient != null) {
            messageRecipientRepository.deleteByRecipient(recipient);
        }
    }

    @DeleteMapping("/message/recipients/user={user_id}")
    public void deleteMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            this.messageRecipientRepository.deleteByUser(user);
        }
    }

    @DeleteMapping("/message/recipients/group={group_id}")
    public void deleteMessageRecipientsByGroupId(@PathVariable("group_id") int group_id) {
        Group group = groupRepository.findById(group_id)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));    
        if (group != null) {
            this.messageRecipientRepository.deleteByGroup(group);
        }
    }

    @DeleteMapping("/message/recipients/message={message_id}")
    public void deleteMessageRecipientsByMessageId(@PathVariable("message_id") int messageId) {
        List<MessageRecipient> messageRecipients = messageRecipientRepository.findByMessageId(messageId);
        if (!messageRecipients.isEmpty()) {
            messageRecipientRepository.deleteAll(messageRecipients);
        }
    }  
    
}