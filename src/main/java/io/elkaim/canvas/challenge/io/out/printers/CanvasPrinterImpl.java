package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.io.out.CanvasPrinter;

import java.io.PrintStream;
import java.util.Objects;

/**
 * Printer able to print a Canvas object.
 * It interprets the Canvas' PointTable to make the ORIGIN point in the upper left corner.
 */
public class CanvasPrinterImpl implements CanvasPrinter {

    private static final String BOTTOM_BORDER_UNIT = "¯";
    private static final String TOP_BORDER_UNIT = "_";
    private static final String SIDE_BORDER_UNIT = "║";

    private final PrintStream printStream;

    public CanvasPrinterImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void print(Canvas canvas) {
        Integer height = canvas.getHeight();
        Integer width = canvas.getWidth();

        this.printVerticalBorder(width, TOP_BORDER_UNIT);
        this.printCanvasBody(canvas, height, width);
        this.printVerticalBorder(width, BOTTOM_BORDER_UNIT);
    }

    private void printVerticalBorder(Integer width, String borderUnit) {
        String verticalBorder = new String(new char[width + 2]).replace("\0", borderUnit);
        this.printStream.println(verticalBorder);
    }

    private void printCanvasBody(DrawTable drawablePoints, Integer height, Integer width) {
        for (int y = 1; y <= height; y++) {
            this.printStream.print(SIDE_BORDER_UNIT);
            for (int x = 1; x <= width; x++) {
                this.printStream.print(Objects.requireNonNullElse(
                        drawablePoints.getPointValue(x, y), ' '));
            }
            this.printStream.println(SIDE_BORDER_UNIT);
        }
    }
}
