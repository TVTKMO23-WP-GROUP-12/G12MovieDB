package com.group12.moviedb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByCreatorId(int creatorId);
    List<Message> findByParentMessageId(int parentMessageId);
    List<Message> findByRecipientId(int recipientId);
    List<Message> findByRead(boolean read);
    Message findById(int messageId);
    Message deleteById(int messageId);
}
