package com.tcs.edu.decorator;

import java.time.Instant;
/**
 * Класс предназначен для обогащения строки текущей датой и временем
 * @author Сегида Татьяна
 */
public class TimestampMessageDecorator {
    /**
     * Метод предназначен для обогащенния строки текущей датой, Перед переданной на вход строкой добавляется текущею дату и время
     * @param message строка, к которой нужно добавить текущую дату и время
     * @return обогащённая строка
     */
    public static String decorat(String message){
        return Instant.now() + " " + message;
    }

}
