package com.group12.moviedb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRecipientId(Integer recipientId);
    List<Message> findByCreatorId(Integer creatorId);
    List<Message> findByParentMessageId(Integer parentMessageId);
    void deleteById(Integer messageId);
    Optional<Message> findById(Integer messageId);
}