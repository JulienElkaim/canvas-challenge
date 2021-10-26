package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.io.out.ErrorPrinter;

/**
 * Print Error messages that are not a functionality of this application.
 * (i.e, MalformedCommandException is part of this application normal functions, NullPointerException is not.)
 */
public class ErrorPrinterImpl implements ErrorPrinter {
    @Override
    public void print(Exception err) {
        System.out.println("Unexpected error occurred: " + err.getMessage());
        err.printStackTrace();
        System.out.flush();
    }

}
