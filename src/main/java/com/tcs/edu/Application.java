package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;

class Application {
    public static void main(String[] args) {
        //Пример проверки кода без использования api MessageService
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 4) {
                ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.MINOR));
            } else if (i == 3 || i == 5) {
                ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.MAJOR));
            } else {
                ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.REGULAR));
            }
        }
        //Заполнение массива
        String[] array = new String[10];
        array[9] = "Hello world!9";
        array[6] = "Hello world!";
        array[7] = "Hello world!";
        for (int i = 0; i < 5; i++) {
            array[i] = "Hello world!" + i;
        }
        //Проверки, когда приходит null в messages и message
        MessageService.print(Severity.REGULAR, null);
        MessageService.print(Severity.REGULAR, "message");
        MessageService.print(Severity.REGULAR, null, array);
        //Проверка метода api MessageService.print без параметра сортировкки
        MessageService.print(Severity.REGULAR, array[0], array);
        //Проверки метода api MessageService.print с параметром сортировки
        MessageService.print(MessageOrder.ASC, Severity.REGULAR, array[0], array);
        MessageService.print(MessageOrder.DESC, Severity.REGULAR, array[0], array);
        //Проверки метода api MessageService.print с параметром сортировки и с параметром distinct
        MessageService.print(Doubling.DISTINCT, MessageOrder.ASC, Severity.REGULAR, array[0], array);
        MessageService.print(Doubling.DISTINCT, MessageOrder.DESC, Severity.REGULAR, array[0], array);
        //Проверки метода api MessageService.print с параметром сортировки и с параметром doubles
        MessageService.print(Doubling.DOUBLES, MessageOrder.ASC, Severity.REGULAR, array[0], array);
        MessageService.print(Doubling.DOUBLES, MessageOrder.DESC, Severity.REGULAR, array[0], array);


    }
}

