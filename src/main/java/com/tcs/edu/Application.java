package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageDecorator;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.service.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

class Application {
    final static Service messageService = new MessageService(new ConsolePrinter(), new MessageDecorator());
    final static CrudService messageRepository = new CrudServiceImpl(new HashMapMessageRepository());

    public static void main(String[] args) {
//        checkEquals();
//        checkEqualsAndHash();
//        checkValidateParams();
//
//        checkPrintMessage();
//
//        checkOrder(MessageOrder.ASC);
//        checkOrder(MessageOrder.DESC);
//
//        checkDoubling(Doubling.DISTINCT);
//        checkDoubling(Doubling.DOUBLES);

//        checkHashMapCreateAndRead();
//        checkHashMapGetAll();

        chekDelete();
//        chekUpdate();

//        System.out.println(checkHashMapFindByLevel(Severity.REGULAR));
//        System.out.println(checkHashMapFindByLevel(Severity.MAJOR));

    }

    private static void chekUpdate() {
        Message message = new Message(Severity.MAJOR, "message");
        Message message1 = new Message(Severity.REGULAR, "message1");

        UUID id = messageRepository.post(message);
        message1.setId(id);
        System.out.println(messageRepository.getById(id));
        System.out.println(messageRepository.update(message1));
    }

    private static void chekDelete() {
        //+
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");
        UUID id = messageRepository.post(message1);
        messageRepository.post(message2);
        System.out.println(messageRepository.getAll());
        messageRepository.delete(id);
        System.out.println(messageRepository.getById(id));
        System.out.println(messageRepository.getAll());
    }

    private static void checkHashMapGetAll() {
        //+
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");
        messageRepository.post(message1);
        messageRepository.post(message2);
        messageRepository.post(message3);

        System.out.println(messageRepository.getAll());
    }

    private static void checkHashMapCreateAndRead() {
        //+
        Message message = new Message(Severity.MAJOR, "message");
        UUID id = messageRepository.post(message);
        System.out.println(id);
        System.out.println(messageRepository.getById(id));
    }

    private static Collection<Message> checkHashMapFindByLevel(Severity level) {
        //+
        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");
        messageRepository.post(message1);
        messageRepository.post(message2);
        messageRepository.post(message3);

        return messageRepository.getBySeverity(level);
    }



    private static void checkEquals() {
        //     Проверки сравнения через equals()

        Message message1 = new Message("message1");
        Message message2 = new Message(Severity.MAJOR, "message");
        Message message3 = new Message(Severity.MAJOR, "message");
        //объекты равны
        System.out.println(message3);
        System.out.println(message2);
        if (message2.equals(message3)) {
            System.out.println("OK");
        } else System.err.println("NOT OK");

        //объекты не равны
        System.out.println(message1);
        System.out.println(message2);
        if (message2.equals(message1)) {
            System.out.println("OK");
        } else System.err.println("NOT OK");
    }

    private static void checkEqualsAndHash() {
        //Проверка выполнения контракта equals()/hashCode()

        Message message1 = new Message(Severity.MAJOR, "message");
        Message message2 = new Message(Severity.MAJOR, "message");
        System.out.println(message1);
        System.out.println(message2);
        if (message2.hashCode() == message1.hashCode()) {
            System.out.println("OK");
        }
    }

    private static void checkValidateParams() {
        //Проверки метода на валидацию входных параметров

        messageService.print(null, null);

        messageService.print(new Message(Severity.MAJOR, "message"));

        Message[] messages = new Message[2];
        messages[0] = new Message(Severity.MAJOR, "message1");
        messages[1] = new Message("message1");
        messageService.print(null, messages);

        messageService.print(null, messages[0],messages);

        messageService.print(null, MessageOrder.ASC, new Message("message"),
                new Message(Severity.MAJOR, "message2"),
                new Message("message"));

    }

    private static void checkDoubling(Doubling doubling) {
        //Проверки метода с параметрами дублирования

        /*messageService.print(doubling, MessageOrder.DESC, new Message("message"),
                new Message(Severity.MAJOR, "message2"),
                new Message("message"));*/

        messageService.print(doubling, MessageOrder.ASC, new Message("message"),
                new Message(Severity.MAJOR, "message2"),
                new Message("message"));
    }

    private static void checkOrder(MessageOrder order) {
        //Проверки метода api MessageService.print с параметром сортировки

        Message[] messages = new Message[2];
        messages[0] = new Message(Severity.MAJOR, "message1");
        messages[1] = new Message("message1");

        messageService.print(order, new Message(Severity.MINOR, "message"), messages);
    }

    private static void checkPrintMessage() {
        //Проверки метода без параметров сортировки и дублирования
        Message[] messages = new Message[2];
        messages[0] = new Message(Severity.MAJOR, "message1");
        messages[1] = new Message("message1");
        messageService.print(new Message(Severity.MINOR, "message"), messages);
        messageService.print(new Message(Severity.MINOR, "message3"));
    }

}

