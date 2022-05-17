package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;

public abstract class ValidatedService {

    protected boolean isArrayValid(Message[] messages) {
        if (messages == null || messages.length == 0) return false;
        return true;
    }

    protected boolean isParamValid(Message message) {
        if (message == null || message.getBody().isEmpty()) return false;
        return true;
    }

    protected boolean isOrderValid(MessageOrder order){
        return isArgValid(order,"order");
    }

    protected boolean isDoublingValid(Doubling doubling){
        return isArgValid(doubling,"doubling");
    }
    private boolean isArgValid(Object arg, String param){
        if(arg == null){
            System.err.println(String.format("Параметр %s передан как null. Метод отвечает ошибкой", param));
            return false;
        }
        return true;
    }

}
