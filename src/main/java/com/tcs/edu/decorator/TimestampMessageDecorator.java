package com.tcs.edu.decorator;

import java.time.Instant;


/**
 * Класс предназначен для обогащения строки текущей датой и временем
 * @author Сегида Татьяна
 */
public class TimestampMessageDecorator {

    private static int messageCount = 0;
    private static final int PAGE_SIZE = 3;
    /**
     * Метод предназначен для обогащенния строки текущей датой, Перед переданной на вход строкой добавляется текущею дату и время
     * @param message строка, к которой нужно добавить текущую дату и время
     * @return обогащённая строка
     */
    public static String decorate(String message) {
        messageCount++;
        String decoratedMessage;
        if (messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format("%d %s %s %n" + "---", messageCount, Instant.now(), message);
        } else
            decoratedMessage = String.format("%d %s %s", messageCount, Instant.now(), message);
        return decoratedMessage;
    }

}
