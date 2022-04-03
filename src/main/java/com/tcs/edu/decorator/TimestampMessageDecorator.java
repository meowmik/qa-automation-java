package com.tcs.edu.decorator;

import java.time.Instant;
/**
 * Класс предназначен для обогащения строки текущей датой и временем
 * @author Сегида Татьяна
 */
public class TimestampMessageDecorator {
    private static int messageCount = 0;
    /**
     * Метод предназначен для обогащенния строки текущей датой, Перед переданной на вход строкой добавляется текущею дату и время
     * @param message строка, к которой нужно добавить текущую дату и время
     * @return обогащённая строка
     */
    public static String decorate(String message) {
        messageCount++;
        //noinspection UnnecessaryLocalVariable
        String decoratedMessage = messageCount + " " + Instant.now() + " " + message;
        return decoratedMessage;
    }

}
