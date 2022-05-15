package com.tcs.edu.printer;

/**
 * Класс предназначен для вывода информации в консоль
 * @author Сегида Татьяна
 */
public class ConsolePrinterError implements Printer{

    /**
     * Метод предназначен для вывода переданной строки в консоль
     * @param message строка, которую нужно вывести в консолль
     */
    public void print(String message) {System.err.println(message);
    }
}
