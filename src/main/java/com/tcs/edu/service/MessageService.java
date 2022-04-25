package com.tcs.edu.service;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;

import java.util.Arrays;
import java.util.Objects;


/**
 * Класс предназначен для обогащения строки.
 *
 * @author Сегида Татьяна
 */
public class MessageService {

    /**
     * Метод предназначен для вывода сообщений в консоль.
     *
     * @param message - строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     */
    public static void print(Severity level, String message, String... messages) {
        if (message != null) {
            ConsolePrinter.print(MessageDecorator.decorate(message, level));
        }
        if (messages != null) {
            for (String current : messages) {
                if (current != null) {
                    ConsolePrinter.print(MessageDecorator.decorate(current, level));
                }

            }
        }
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message - строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     * @param order   - параметр сортировки сообщений
     */
    public static void print(MessageOrder order, Severity level, String message, String... messages) {
        if (messages != null) {
            if (order == MessageOrder.ASC) {
                ConsolePrinter.print(MessageDecorator.decorate(message, level));
                for (String current : messages) {
                    if (current != null) {
                        ConsolePrinter.print(MessageDecorator.decorate(current, level));
                    }
                }
            } else {
                for (int i = messages.length - 1; i >= 0; i--) {
                    if (messages[i] != null) {
                        ConsolePrinter.print(MessageDecorator.decorate(messages[i], level));
                    }
                }
                if (message != null) {
                    ConsolePrinter.print(MessageDecorator.decorate(message, level));
                }
            }
        } else {
            if (message != null) {
                ConsolePrinter.print(MessageDecorator.decorate(message, level));
            }
        }
    }

    /**
     * Внутренний метод предназначен избавления от дублирования сообщений.
     *
     * @param array - строка не обогащённая строка
     */
    private static String[] clean(String[] array) {
        String[] clean = new String[array.length];
        int n = 0;
        for (String s : array) {
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

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message  - строка не обогащённая строка
     * @param level    - приоритеты, которые добавятся к строке
     * @param order    - параметр сортировки сообщений
     * @param doubling - признак отстутствия или существования дублирования
     */
    public static void print(Doubling doubling, MessageOrder order, Severity level, String message, String... messages) {
        if (doubling == Doubling.DISTINCT) {
            if (messages != null) {
                String[] array = new String[messages.length + 1];
                array[0] = message;
                System.arraycopy(messages, 0, array, 1, array.length - 1);
                String[] newArray = clean(array);
                String[] newArray2 = Arrays.copyOfRange(newArray, 1, newArray.length);
                print(order, level, newArray[0], newArray2);
            } else{
                if (message != null){
                    print(order, level, message);
                }
            }
        } else {
            print(order, level, message, messages);
        }

    }


}
