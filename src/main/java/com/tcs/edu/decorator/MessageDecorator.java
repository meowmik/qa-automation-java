package com.tcs.edu.decorator;

import java.time.Instant;

import static com.tcs.edu.decorator.ChoiceSeverity.choiceSeverity;

public class MessageDecorator implements Decorator{
    private static final int PAGE_SIZE = 3;
    private static int messageCount = 0;

    /**
     * Метод предназначен для обогащенния строки порядковым номером, текущей датой и временем, а так же уровнем значимости. Ещё метод добавляет разграничение строк.
     * @param message строка, которую нужно дополнить
     * @param level - приоритет, который с помощью метода choiceSeverity конвертируется в уровень значимости
     * @return обогащённая строка
     */
    public String decorate(String message, Severity level) {
        messageCount++;
        String decoratedMessage;
        if (messageCount % PAGE_SIZE == 0) {
            decoratedMessage = String.format("%d %s %s %s %n---", messageCount, Instant.now(), message, choiceSeverity(level));
        } else
            decoratedMessage = String.format("%d %s %s %s", messageCount, Instant.now(), message, choiceSeverity(level));
        return decoratedMessage;
    }
}
