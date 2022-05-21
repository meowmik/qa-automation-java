package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;

public abstract class ValidatedService extends LogException {

    protected boolean isArrayValid(Message[] messages) {
        if (messages == null || messages.length == 0) return false;
        return true;
    }

    protected boolean isParamValid(Message message) {
        if (message == null || message.getBody().isEmpty()) return false;
        return true;
    }

    protected void isOrderValid(MessageOrder order) {
        try {
             isArgValid(order, "order");
        } catch (IllegalArgumentException e) {
            throw new LogException("Параметр order передан неверно", e);
        }
    }

    protected void isDoublingValid(Doubling doubling) {
        try {
             isArgValid(doubling, "doubling");
        } catch (IllegalArgumentException e) {
            throw new LogException("Параметр doubling передан неверно", e);
        }
    }

    private void isArgValid(Object arg, String param) {
        if (arg == null) {
            throw new IllegalArgumentException(String.format("Параметр %s передан как null. Метод отвечает ошибкой", param));
        }
    }

}
