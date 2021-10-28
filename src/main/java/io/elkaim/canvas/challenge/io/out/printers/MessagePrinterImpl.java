package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.MessagePrinter;

import java.io.PrintStream;

/**
 * Print every messages to help the user when providing a wrong input.
 * Being able to do so is part of our application's functionalities.
 */
public class MessagePrinterImpl implements MessagePrinter {

    private final PrintStream printStream;

    public MessagePrinterImpl(PrintStream printStream){
        this.printStream = printStream;
    }

    @Override
    public void print(String msg) {
        this.printStream.println(msg);
    }
}
