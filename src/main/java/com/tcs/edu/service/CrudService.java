package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;

import java.util.Collection;
import java.util.UUID;

public interface CrudService {
    UUID post(Message message);
    Message getById(UUID id);
    Collection<Message> getBySeverity(Severity level);
    Collection<Message> getAll();
    Message update(UUID id, Message message);
    void delete(UUID id);
}
