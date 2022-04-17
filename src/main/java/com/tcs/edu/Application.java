package com.tcs.edu;

import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.MessageService;

class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            if (i == 0 || i == 4 ){
                ConsolePrinter.print(MessageService.decorate("Hello world!", Severity.MINOR));
            }
            else
                if (i == 3 || i == 5){
                    ConsolePrinter.print(MessageService.decorate("Hello world!", Severity.MAJOR));
                }
                else
                    {
                        ConsolePrinter.print(MessageService.decorate("Hello world!", Severity.REGULAR));
                    }

        }
    }
}

