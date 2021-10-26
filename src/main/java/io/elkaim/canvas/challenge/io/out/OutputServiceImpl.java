package io.elkaim.canvas.challenge.io.out;

import io.elkaim.canvas.challenge.canvas.model.Canvas;

/**
 * Provide a unique API for every printers available.
 * Provide access to the Output stream, to communicate with the users.
 * Allow other components to deal with only one component rather than n printers.
 */
public class OutputServiceImpl implements OutputService {

    private final MessagePrinter messagePrinter;

    private final CanvasPrinter canvasPrinter;

    private final ErrorPrinter errorPrinter;

    public OutputServiceImpl(MessagePrinter messagePrinter,
                             CanvasPrinter canvasPrinter,
                             ErrorPrinter errorPrinter) {
        this.messagePrinter = messagePrinter;
        this.canvasPrinter = canvasPrinter;
        this.errorPrinter = errorPrinter;
    }

    @Override
    public void print(Canvas canvas) {
        this.canvasPrinter.print(canvas);
    }

    @Override
    public void print(Exception e) {
        this.errorPrinter.print(e);
    }

    @Override
    public void print(String msg) {
        this.messagePrinter.print(msg);
    }
}
