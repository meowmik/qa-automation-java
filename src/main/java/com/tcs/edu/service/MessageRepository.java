package com.tcs.edu.service;

import com.tcs.edu.Message;

import java.util.UUID;

public interface MessageRepository {
    UUID create(Message message);
    UUID[] createFew(Message[] messages);
    Message findByPrimaryKey(UUID key);
}
