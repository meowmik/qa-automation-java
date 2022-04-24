package com.tcs.edu;

import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.MessageService;

class Application {
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

        String[] array = new String[10] ;
        array[9] = "Hello world!9";
        for (int i=0; i<7;i++){
            array[i] = "Hello world!" + i;
        }
        MessageService.print(Severity.REGULAR, array[0], array);
    }
}

