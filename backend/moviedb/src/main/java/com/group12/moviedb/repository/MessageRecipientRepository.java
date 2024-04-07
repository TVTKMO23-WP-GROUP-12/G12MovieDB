package com.group12.moviedb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.MessageRecipient;
import com.group12.moviedb.models.User;

@Repository
public interface MessageRecipientRepository extends JpaRepository<MessageRecipient, Integer> {
    List<MessageRecipient> findByGroup(Group group);
    List<MessageRecipient> findByUserId(User user);
    List<MessageRecipient> findByMessageId(int messageId);
    List<MessageRecipient> findByRead(boolean isRead);
    List<MessageRecipient> findByRecipientId(int recipientId);
    List<MessageRecipient> findByReadAndUserId(boolean isRead, User user);
    List<MessageRecipient> findByReadAndGroup(boolean isRead, Group group);
    List<MessageRecipient> findByReadAndRecipientId(boolean isRead, int recipientId);
    List<MessageRecipient> findByReadAndMessageId(boolean isRead, int messageId);
    MessageRecipient deleteById(int id);
    MessageRecipient deleteByUserId(User user);
    MessageRecipient deleteByGroup(Group group);
    MessageRecipient deleteByRecipientId(int recipientId);
    MessageRecipient deleteByMessageId(int messageId);
    MessageRecipient findById(int memberId);
}
