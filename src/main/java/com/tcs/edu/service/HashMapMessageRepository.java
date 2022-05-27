package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;

import java.util.*;
import java.util.stream.Collectors;

public class HashMapMessageRepository implements MessageRepository{
    private Map<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        final UUID key = UUID.randomUUID();
        message.setId(key);
        messages.put(key,message);
        return key;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }

    @Override
    public Collection<Message> findBySeverity(Severity level) {
        return messages.values().stream()
                .filter(message -> message.getLevel() == level)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id){
        messages.remove(id);
    }

    @Override
    public Message update(Message message) {
        messages.put(message.getId(), message);
        return findByPrimaryKey(message.getId());
    }



}
