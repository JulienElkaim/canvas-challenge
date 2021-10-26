package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.MessagePrinter;

/**
 * Print every messages to help the user when providing a wrong input.
 * Being able to do so is part of our application's functionalities.
 */
public class MessagePrinterImpl implements MessagePrinter {

    @Override
    public void print(String msg) {
        System.out.println(msg);
    }
}
