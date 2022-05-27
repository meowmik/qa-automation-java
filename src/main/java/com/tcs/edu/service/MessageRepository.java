package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);
    Message findByPrimaryKey(UUID key);
    Collection<Message> findAll();
    Collection<Message> findBySeverity(Severity level);
    void delete(UUID id);
    Message update(UUID id, Message message);

}
