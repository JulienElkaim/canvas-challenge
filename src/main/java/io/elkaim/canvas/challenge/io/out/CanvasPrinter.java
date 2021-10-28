package io.elkaim.canvas.challenge.io.out;

import io.elkaim.canvas.challenge.canvas.model.Canvas;

public interface CanvasPrinter {
    /**
     * Knows how to print a Canvas object with a PrintStream.
     * @param canvas to be printed in outputStream
     */
    void print(Canvas canvas);
}
