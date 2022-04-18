package com.tcs.edu.decorator;

/**
 * Класс предназначен для выбора уровня значимости
 * @author Сегида Татьяна
 */
public class ChoiceSeverity {

    /**
     * Метод предназначен для выбора уровня значимости
     * @param severity приоритет, который сопоставляется с уровнем значимости
     * @return уровень значимости отностительно переданного приоритета
     */
    public static String choiceSeverity (Severity severity){
        String severityString = "0";
        switch (severity){
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
            case MAJOR:
                severityString = "(!!!)";
                break;
            default:
                break;
        }
        return severityString;
    }
}
