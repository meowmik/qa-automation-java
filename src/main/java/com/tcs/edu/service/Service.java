package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;

public interface Service {
    void print (Message message,Message... messages);
    void print (MessageOrder order, Message message, Message... messages);
    void print (Doubling doubling, MessageOrder order, Message message, Message... messages);
}
