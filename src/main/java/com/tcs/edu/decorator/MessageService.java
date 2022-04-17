package com.tcs.edu.decorator;

import com.tcs.edu.printer.ConsolePrinter;

import java.time.Instant;

import static com.tcs.edu.decorator.ChoiceSeverity.choiceSeverity;


/**
 * Класс предназначен для обогащения строки.
 * @author Сегида Татьяна
 */
public class MessageService {

    private static int messageCount = 0;

    private static final int PAGE_SIZE = 3;

    /**
     * Метод предназначен для обогащенния строки порядковым номером, текущей датой и временем, а так же уровнем значимости. Ещё метод добавляет разграничение строк.
     * @param message строка, которую нужно дополнить
     * @param level - приоритет, который с помощью метода choiceSeverity конвертируется в уровень значимости
     * @return обогащённая строка
     */
    public static String decorate(String message, Severity level) {
        messageCount++;
        String decoratedMessage;
        if (messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format("%d %s %s %s %n---", messageCount, Instant.now(), message, choiceSeverity(level));
        } else
            decoratedMessage = String.format("%d %s %s %s", messageCount, Instant.now(), message, choiceSeverity(level));
        return decoratedMessage;
    }


    /**
     * Метод предназначен для вывода сообщений в консоль.
     * @param message строка не обогащённая строка
     * @param level - приоритеты, которые добавятся к строке
     */
    public static void print (Severity level, String message, String... messages) {
        ConsolePrinter.print(decorate(message, level));
        for (String current : messages){
            ConsolePrinter.print(decorate(current, level));
        }
    }

}
