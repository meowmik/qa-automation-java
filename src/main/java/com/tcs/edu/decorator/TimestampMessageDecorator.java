package com.tcs.edu.decorator;

import java.time.Instant;
/**
 * Класс предназначен для обогащения строки
 * @author Сегида Татьяна
 *
 */
public class TimestampMessageDecorator {
    /**
     * Метод предназначен для обогащенния строки
     * @param message строка, к которой нужно добавить текущее время
     * @return обогащённая строка
     * @affect Метод ни на что не влияет
     *
     */
    public static String decorator(String message){
        return Instant.now() + " " + message;
    }

}
