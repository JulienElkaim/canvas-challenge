package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.ErrorPrinter;

import java.io.PrintStream;

/**
 * Print Error messages that are not a functionality of this application.
 * (i.e, MalformedCommandException is part of this application normal functions, NullPointerException is not.)
 */
public class ErrorPrinterImpl implements ErrorPrinter {

    private final PrintStream printStream;

    public ErrorPrinterImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(Exception err) {
        this.printStream.println("Unexpected error occurred: " + err.getMessage());
        err.printStackTrace();
    }

}
