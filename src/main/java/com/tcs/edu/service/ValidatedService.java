package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;

public abstract class ValidatedService {

    public boolean isArrayValid(Message[] messages) {
        if (messages == null || messages.length == 0) return false;
        return true;
    }

    public boolean isParamValid(Message message) {
        if (message == null || message.getBody().isEmpty()) return false;
        return true;
    }

    public boolean isOrderValid(MessageOrder order){
        if(order == null){
            System.err.println("Параметр order передан как null. Метод отвечает ошибкой");
            return false;
        }
        return true;
    }

    public boolean isDoublingValid(Doubling doubling){
        if(doubling == null){
            System.err.println("Параметр doubling передан как null. Метод отвечает ошибкой");
            return false;
        }
        return true;
    }

}
