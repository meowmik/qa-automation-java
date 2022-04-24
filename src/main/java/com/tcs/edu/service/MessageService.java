package com.tcs.edu.service;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;


/**
 * Класс предназначен для обогащения строки.
 * @author Сегида Татьяна
 */
public class MessageService {

    /**
     * Метод предназначен для вывода сообщений в консоль.
     * @param message строка не обогащённая строка
     * @param level - приоритеты, которые добавятся к строке
     */
    public static void print (Severity level, String message, String... messages) {
        ConsolePrinter.print(MessageDecorator.decorate(message, level));
        for (String current : messages){
            if (current != null) {
                ConsolePrinter.print(MessageDecorator.decorate(current, level));
            }

        }
    }
    /**
     * Метод предназначен для вывода сообщений в консоль, порядок вывода сообщений зависит от параметра сортировки.
     * @param message строка не обогащённая строка
     * @param level - приоритеты, которые добавятся к строке
     * @param order - параметр сортировки сообщений
     */
    public static void print (MessageOrder order, Severity level,  String message, String... messages){
        if (order == MessageOrder.ASC){
            ConsolePrinter.print(MessageDecorator.decorate(message, level));
            for (String current : messages){
                if (current != null) {
                    ConsolePrinter.print(MessageDecorator.decorate(current, level));
                }
            }
        }
        else{
            for (int i = messages.length - 1; i >= 0; i--){
                if (messages[i] != null) {
                    ConsolePrinter.print(MessageDecorator.decorate(messages[i], level));
                }
            }
            ConsolePrinter.print(MessageDecorator.decorate(message, level));
        }
    }
}
