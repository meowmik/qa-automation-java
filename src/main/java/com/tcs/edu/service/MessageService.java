package com.tcs.edu.service;

import com.tcs.edu.Message;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.printer.ConsolePrinter;

import java.util.Objects;


/**
 * Класс предназначен для обогащения строки.
 *
 * @author Сегида Татьяна
 */
public class MessageService {
    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;
    private static final Doubling DEFAULT_DOUBLING = Doubling.DOUBLES;

    /**
     * Метод предназначен для вывода сообщений в консоль.
     *
     * @param message - строка не обогащённая строка
     */
    public static void print(Message message, Message... messages) {
        print(DEFAULT_ORDER, message, messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message - строка не обогащённая строка
     * @param order   - параметр сортировки сообщений
     */
    public static void print(MessageOrder order, Message message, Message... messages) {
        print(DEFAULT_DOUBLING, order, message, messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message  - строка не обогащённая строка
     * @param order    - параметр сортировки сообщений
     * @param doubling - признак отстутствия или существования дублирования
     */
    public static void print(Doubling doubling, MessageOrder order, Message message, Message... messages) {
        Message[] newMessages = join(message, messages);
        newMessages = sort(order, newMessages);
        newMessages = modifyDoubles(doubling, newMessages);
        print(newMessages);
    }

    private static Message[] join(Message message, Message... messages) {
        if (messages == null) {
            return new Message[]{message};
        }
        Message[] array = new Message[messages.length + 1];
        if (message != null) {
            array[0] = message;
        }
        int i = 1;
        for (Message current : messages) {
            if (current != null) {
                array[i] = current;
                i++;
            }
        }
        Message[] newArray = new Message[i];
        System.arraycopy(array, 0, newArray, 0, i);
        return newArray;

    }


    private static void print(Message... messages) {
        for (Message current : messages) {
            if (current != null) {
                ConsolePrinter.print(MessageDecorator.decorate(current.getBody(), current.getLevel()));
            }
        }
    }

    private static Message[] sort(MessageOrder order, Message... messages) {
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

    private static Message[] modifyDoubles(Doubling doubling, Message... messages) {
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
        return clean;
    }


}
