package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.Message;
import com.group12.moviedb.models.MessageRecipient;
import com.group12.moviedb.models.User;

@Repository
public interface MessageRecipientRepository extends JpaRepository<MessageRecipient, Integer> {
    List<MessageRecipient> findByGroup(Group group);
    List<MessageRecipient> findByUser(User user);
    List<MessageRecipient> findByMessage(MessageRecipient messageRecipient);
    List<MessageRecipient> findByIsRead(boolean isRead);
    MessageRecipient findByRecipient(User recipient);
    List<MessageRecipient> findByIsReadAndUser(boolean isRead, User user); 
    List<MessageRecipient> findByIsReadAndRecipient(boolean isRead, User recipient);
    List<MessageRecipient> findByIsReadAndMessage(boolean isRead, Message message);
    List<MessageRecipient> findByMessageId(Integer id);
    @SuppressWarnings("null")
    void deleteById(Integer id);
    void deleteByUser(User user);
    void deleteByGroup(Group group);
    void deleteByRecipient(User recipient);
    void deleteByMessage(Message message);

    @Query("SELECT mr FROM MessageRecipient mr WHERE mr.isRead = :isRead AND mr.group = :group")
    List<MessageRecipient> findByIsReadAndGroup(boolean isRead, Group group);
    List<MessageRecipient> findByUser(Optional<User> user);
    @SuppressWarnings("null")
    Optional<MessageRecipient> findById(Integer recipient_id);
    
}
