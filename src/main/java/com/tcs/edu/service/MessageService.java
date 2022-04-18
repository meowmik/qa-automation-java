package com.tcs.edu.service;

import com.tcs.edu.decorator.MessageDecorator;
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
            ConsolePrinter.print(MessageDecorator.decorate(current, level));
        }
    }

}
