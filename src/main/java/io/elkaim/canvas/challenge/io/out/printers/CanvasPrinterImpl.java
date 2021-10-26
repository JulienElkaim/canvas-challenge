package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.io.out.CanvasPrinter;

import java.util.Objects;

/**
 * Printer able to print a Canvas object.
 * It interprets the Canvas' PointTable to make the ORIGIN point on the upper left corner.
 */
public class CanvasPrinterImpl implements CanvasPrinter {

    private static final String BOTTOM_BORDER_UNIT = "¯";
    private static final String TOP_BORDER_UNIT = "_";
    private static final String SIDE_BORDER_UNIT = "║";

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
        System.out.println(verticalBorder);
    }

    private void printCanvasBody(DrawTable drawablePoints, Integer height, Integer width) {
        for (int y = 1; y <= height; y++) {
            System.out.print(SIDE_BORDER_UNIT);
            for (int x = 1; x <= width; x++) {
                System.out.print(Objects.requireNonNullElse(
                        drawablePoints.getPointValue(x, y), ' '));
            }
            System.out.println(SIDE_BORDER_UNIT);
        }
    }
}
