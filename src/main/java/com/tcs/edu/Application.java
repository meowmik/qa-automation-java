package com.tcs.edu;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;

class Application {
    //Пример проверки кода без использования api MessageService
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 4 ){
                ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.MINOR));
            }
            else
                if (i == 3 || i == 5){
                    ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.MAJOR));
                }
                else
                    {
                        ConsolePrinter.print(MessageDecorator.decorate("Hello world!", Severity.REGULAR));
                    }

        }
    //Заполнение массива
        String[] array = new String[10] ;
        array[9] = "Hello world!9";
        for (int i=0; i<7;i++){
            array[i] = "Hello world!" + i;
        }
        //Проверка метода api MessageService.print без параметра сортировкки
        MessageService.print(Severity.REGULAR, array[0], array);
        //Проверки метода api MessageService.print с параметром сортировки
        MessageService.print(MessageOrder.ASC, Severity.REGULAR, array[0], array);
        MessageService.print(MessageOrder.DESC, Severity.REGULAR, array[0], array);
    }
}

