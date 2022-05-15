package com.tcs.edu.service;

import com.tcs.edu.Message;

public abstract class ValidatedService {
    public Message[] cleanNull(Message[] messages) {
        Message[] array = new Message[messages.length];
        int i = 0;
        for (Message current : messages) {
            if (isParamValid(current)) {
                array[i++] = current;
            }
        }
        Message[] newArray = new Message[i];
        System.arraycopy(array, 0, newArray, 0, i);
        return newArray;
    }

    public boolean isArrayValid(Message[] messages) {
        if (messages == null || messages.length == 0) return false;
        return true;
    }

    public boolean isParamValid(Message message) {
        if (message == null) return false;
        return true;
    }
}
