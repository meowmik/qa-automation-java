package com.tcs.edu;

import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.TimestampMessageDecorator;

class Application {
    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            ConsolePrinter.print(TimestampMessageDecorator.decorate("Hello world!"));
        }
    }
}

