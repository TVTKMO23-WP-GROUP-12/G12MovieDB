package com.group12.moviedb.repository;

import java.nio.LongBuffer;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group12.moviedb.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByCreatorId(Integer creator_id);
    List<Message> findByParentMessageId(Integer parentMessage_id);
    List<Message> findByRecipientsId(Integer recipient_id);
    Optional<Message> findById(Integer message_id);

}
