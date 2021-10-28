package io.elkaim.canvas.challenge.io.out;

public interface ErrorPrinter {
    /**
     * Knows how to print on outputStream.
     * @param e exception providing the message to be printed.
     */
    void print(Exception e);

}
