package com.tcs.edu.service;

import com.tcs.edu.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
}
