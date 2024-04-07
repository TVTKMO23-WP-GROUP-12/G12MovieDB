package com.group12.moviedb.controller;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.MessageRecipient;
import com.group12.moviedb.models.User;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.repository.GroupRepository;
import com.group12.moviedb.repository.MessageRecipientRepository;
import com.group12.moviedb.repository.UserRepository;

@RestController
public class MessageRecipientController {

    private final MessageRecipientRepository messageRecipientRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public MessageRecipientController(MessageRecipientRepository messageRecipientRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.messageRecipientRepository = messageRecipientRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/message/recipients")
    public Iterable<MessageRecipient> findAllMessageRecipients() {
        return this.messageRecipientRepository.findAll();
    }

    @GetMapping("/message/recipients/{recipient_id}")
    public MessageRecipient findMessageRecipientById(int recipient_id) {
        return this.messageRecipientRepository.findById(recipient_id);
    }

    @GetMapping("/message/recipients/user={user_id}")
    public List<MessageRecipient> findMessageRecipientsByUserId(@PathVariable int user_id) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            return this.messageRecipientRepository.findByUserId(user);
        }
        return Collections.emptyList();
    }

    @GetMapping("/message/recipients/message={message_id}")
    public Iterable<MessageRecipient> findMessageRecipientsByMessageId(int message_id) {
        return this.messageRecipientRepository.findByMessageId(message_id);
    }

    @GetMapping("/message/recipients/read")
    public Iterable<MessageRecipient> findReadMessageRecipients() {
        return this.messageRecipientRepository.findByRead(true);
    }

    @GetMapping("/message/recipients/unread")
    public Iterable<MessageRecipient> findUnreadMessageRecipients() {
        return this.messageRecipientRepository.findByRead(false);
    }

    @GetMapping("/message/recipients/read/user={user_id}")
    public List<MessageRecipient> findReadMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            return this.messageRecipientRepository.findByReadAndUserId(true, user);
        }
        return this.messageRecipientRepository.findByReadAndUserId(true, null);
    }

    @GetMapping("/message/recipients/unread/user={user_id}")
    public List<MessageRecipient> findUnreadMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            return this.messageRecipientRepository.findByReadAndUserId(false, user);
        }
        return this.messageRecipientRepository.findByReadAndUserId(false, null);
    }

    @GetMapping("/message/recipients/read/message={message_id}")
    public Iterable<MessageRecipient> findReadMessageRecipientsByMessageId(int message_id) {
        return this.messageRecipientRepository.findByReadAndMessageId(true, message_id);
    }

    @GetMapping("/message/recipients/unread/message={message_id}")
    public Iterable<MessageRecipient> findUnreadMessageRecipientsByMessageId(int message_id) {
        return this.messageRecipientRepository.findByReadAndMessageId(false, message_id);
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
    public MessageRecipient updateMessageRecipient(int recipient_id, MessageRecipient messageRecipient) {
        MessageRecipient recipient = this.messageRecipientRepository.findById(recipient_id);
        if (recipient != null) {
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
    public void deleteMessageRecipient(int recipient_id) {
        this.messageRecipientRepository.deleteById(recipient_id);
    }

    @DeleteMapping("/message/recipients/user={user_id}")
    public void deleteMessageRecipientsByUserId(int user_id) {
        User user = userRepository.findById(user_id);
        if (user != null) {
            this.messageRecipientRepository.deleteByUserId(user);
        }
    }

    @DeleteMapping("/message/recipients/group={group_id}")
    public void deleteMessageRecipientsByGroupId(int group_id) {
        Group group = groupRepository.findById(group_id);
        if (group != null) {
            this.messageRecipientRepository.deleteByGroup(group);
        }
    }

    @DeleteMapping("/message/recipients/message={message_id}")
    public void deleteMessageRecipientsByMessageId(int message_id) {
        this.messageRecipientRepository.deleteByMessageId(message_id);
    }
}
