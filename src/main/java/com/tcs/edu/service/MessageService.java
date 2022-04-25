package com.tcs.edu.service;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;

import java.util.Arrays;
import java.util.Objects;
import java.util.SplittableRandom;


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
     * @param level   - приоритеты, которые добавятся к строке
     */
    public static void print(Severity level, String message, String... messages) {
        print(DEFAULT_ORDER,level,message,messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message - строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     * @param order   - параметр сортировки сообщений
     */
    public static void print(MessageOrder order, Severity level, String message, String... messages) {
        print(DEFAULT_DOUBLING,order,level,message,messages);
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message  - строка не обогащённая строка
     * @param level    - приоритеты, которые добавятся к строке
     * @param order    - параметр сортировки сообщений
     * @param doubling - признак отстутствия или существования дублирования
     */
    public static void print(Doubling doubling, MessageOrder order, Severity level, String message, String... messages) {
        String[] newMessages = join(message, messages);
        newMessages = sort(order, newMessages);
        newMessages = modifyDoubles(doubling, newMessages);
        print(level,newMessages);
    }

    private static String[] join(String message, String[] messages){
        if(messages == null){
            return new String[]{message};
        }
        String[] array = new String[messages.length + 1];
        array[0] = message;
        System.arraycopy(messages, 0, array, 1, array.length - 1);
        return array;

    }

    private static void print(Severity level, String[] messages){
        for (String current : messages) {
            if (current != null) {
                ConsolePrinter.print(MessageDecorator.decorate(current, level));
            }
        }
    }

    private static String[] sort (MessageOrder order,String[] messages){
        if(order == MessageOrder.ASC){
            return messages;
        }
        String[] newMessages = new String[messages.length];
        int j = 0;
        for (int i = messages.length - 1; i >= 0; i--){
            newMessages[j] = messages[i];
            j++;
        }
        return newMessages;
    }

    private static String[] modifyDoubles(Doubling doubling,String[] messages) {
        if(doubling == Doubling.DOUBLES){
            return messages;
        }
        String[] clean = new String[messages.length];
        int n = 0;
        for (String s : messages) {
            int j = 0;
            boolean flag = false;
            while (j < clean.length && !flag) {
                if (Objects.equals(clean[j], s)) {
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
