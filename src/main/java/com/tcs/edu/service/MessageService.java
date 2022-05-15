package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.*;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.printer.Printer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;


/**
 * Класс предназначен для обогащения строки.
 *
 * @author Сегида Татьяна
 */
public class MessageService extends ValidatedService implements Service {
    private final Printer printer;
    private final Decorator decorator;
    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;
    private static final Doubling DEFAULT_DOUBLING = Doubling.DOUBLES;

    public MessageService(Printer consolePrinter, Decorator messageDecorator) {
        this.decorator = messageDecorator;
        this.printer = consolePrinter;

    }

    /**
     * Метод предназначен для вывода сообщений в консоль.
     *
     * @param message - строка не обогащённая строка
     */
    public void print(Message message, Message... messages) {
        print(DEFAULT_ORDER, message, messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message - строка не обогащённая строка
     * @param order   - параметр сортировки сообщений
     */
    public void print(MessageOrder order, Message message, Message... messages) {
        print(DEFAULT_DOUBLING, order, message, messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message  - строка не обогащённая строка
     * @param order    - параметр сортировки сообщений
     * @param doubling - признак отстутствия или существования дублирования
     */
    public void print(Doubling doubling, MessageOrder order, Message message, Message... messages) {
        Message[] newMessages = join(message, messages);
        newMessages = cleanNull(newMessages);
        newMessages = sort(order, newMessages);
        newMessages = modifyDoubles(doubling, newMessages);
        privatePrint(newMessages);
    }

    private Message[] join(Message message, Message... messages) {
        if (!isArrayValid(messages)) {
            return new Message[]{message};
        }
        Message[] newArray = new Message[messages.length + 1];
        newArray[0] = message;
        System.arraycopy(messages, 0, newArray, 1, messages.length);
        return newArray;

    }


    private void privatePrint(Message... messages) {
        for (Message current : messages) {
            printer.print(decorator.decorate(current.toString()));
        }
    }

    private  Message[] sort(MessageOrder order, Message... messages) {
        if (order == MessageOrder.ASC) {
            return messages;
        }
        Message[] newMessages = new Message[messages.length];
        int j = 0;
        for (int i = messages.length - 1; i >= 0; i--) {
            newMessages[j] = messages[i];
            j++;
        }
        return newMessages;
    }

    private  Message[] modifyDoubles(Doubling doubling, Message... messages) {
        if (doubling == Doubling.DOUBLES) {
            return messages;
        }
        Message[] clean = new Message[messages.length];
        int n = 0;
        for (Message s : messages) {
            int j = 0;
            boolean flag = false;
            while (j < clean.length && !flag) {
                if (clean[j] != null && Objects.equals(clean[j].getBody(), s.getBody())) {
                    flag = true;
                }
                j++;
            }
            if (!flag) {
                clean[n] = s;
                n++;
            }
        }
        return cleanNull(clean);
    }


}
