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
        messages.put(key,message);
        return key;
    }

    @Override
    public UUID[] createFew(Message[] messages) {
        UUID[] keys = new UUID[messages.length];
        int i=0;
        for (Message current: messages){
            keys[i++] = create(current);
        }
        return keys;
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
    public Collection<Message> findBySeverity2(Severity level) {
        Collection<Message> filteredMessages = new ArrayList<>();
        for(Message current : messages.values()){
            if(current.getLevel() == level){
                filteredMessages.add(current);
            }
        }
        return filteredMessages;
    }
}
