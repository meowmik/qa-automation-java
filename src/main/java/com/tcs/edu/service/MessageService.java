package com.tcs.edu.service;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;

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
     * @param message строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     */
    public static void print(Severity level, String message, String... messages) {
        ConsolePrinter.print(MessageDecorator.decorate(message, level));
        for (String current : messages) {
            if (current != null) {
                ConsolePrinter.print(MessageDecorator.decorate(current, level));
            }

        }
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     * @param order   - параметр сортировки сообщений
     */
    public static void print(MessageOrder order, Severity level, String message, String... messages) {
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
            ConsolePrinter.print(MessageDecorator.decorate(message, level));
        }
    }

    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     *
     * @param message строка не обогащённая строка
     * @param level   - приоритеты, которые добавятся к строке
     * @param order   - параметр сортировки сообщений
     */
    public static void print(Doubling doubling, MessageOrder order, Severity level, String message, String... messages) {
        if (doubling == Doubling.DISTINCT) {
            if (order == MessageOrder.ASC) {
                String[] array = new String[messages.length + 1];
                ConsolePrinter.print(MessageDecorator.decorate(message, level));
                array[0] = message;
                int n = 1;
                for (String current : messages) {
                    if (current != null) {
                        int j = 0;
                        boolean flag = false;
                        while (j < array.length && !flag) {
                            if (Objects.equals(current, array[j])) {
                                flag = true;
                            }
                            j++;
                        }
                        if (!flag) {
                            ConsolePrinter.print(MessageDecorator.decorate(current, level));
                            array[n] = current;
                            n++;
                        }
                    }
                }
            } else {
                String[] array = new String[messages.length + 1];
                int n = 0;
                for (int i = messages.length - 1; i >= 0; i--) {
                    if (messages[i] != null) {
                        int j = 0;
                        boolean flag = false;
                        while (j < array.length && !flag) {
                            if (Objects.equals(messages[i], array[j])) {
                                flag = true;
                            }
                            j++;
                        }
                        if (!flag) {
                            ConsolePrinter.print(MessageDecorator.decorate(messages[i], level));
                            array[n] = messages[i];
                            n++;
                        }
                    }
                }
                int j = 0;
                boolean flag = false;
                while (j < array.length && !flag) {
                    if (Objects.equals(message, array[j])) {
                        flag = true;
                    }
                    j++;
                }
                if (!flag) {
                    ConsolePrinter.print(MessageDecorator.decorate(message, level));
                }

            }
        } else {
            print(order, level, message, messages);
        }
    }


}
