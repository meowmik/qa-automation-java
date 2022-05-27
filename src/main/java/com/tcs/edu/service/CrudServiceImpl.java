package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Severity;

import java.util.Collection;
import java.util.UUID;

public class CrudServiceImpl implements CrudService{
    private MessageRepository messageRepository;

    public CrudServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public UUID post(Message message) {
       return messageRepository.create(message);
    }


    @Override
    public Message getById(UUID id) {
        return messageRepository.findByPrimaryKey(id);
    }

    @Override
    public Collection<Message> getBySeverity(Severity level) {
        return messageRepository.findBySeverity(level);
    }

    @Override
    public Collection<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message update(Message message) {
        return messageRepository.update(message);
    }

    @Override
    public void delete(UUID id) {
        messageRepository.delete(id);
    }
}
