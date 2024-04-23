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
    List<MessageRecipient> findByUserId(Integer userId);
    List<MessageRecipient> findByMessage(MessageRecipient messageId);
    List<MessageRecipient> findByIsRead(boolean isRead);
    List<MessageRecipient> findByIsReadAndUser(boolean isRead, User userId); 
    List<MessageRecipient> findByIsReadAndUserId(boolean isRead, Integer userId);
    List<MessageRecipient> findByIsReadAndMessage(boolean isRead, Message message);
    List<MessageRecipient> findByMessageId(Integer messageId);
    void deleteById(Integer messageId);
    void deleteByUser(User user);
    void deleteByGroup(Group group);
    void deleteByUserId(Integer userId);
    void deleteByMessage(Message messageId);

    @Query("SELECT mr FROM MessageRecipient mr WHERE mr.isRead = :isRead AND mr.group = :group")
    List<MessageRecipient> findByIsReadAndGroup(boolean isRead, Group group);
    List<MessageRecipient> findByUser(Optional<User> user);
    Optional<MessageRecipient> findById(Integer recipientId);
 
    
    
}
