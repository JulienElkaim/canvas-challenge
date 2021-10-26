package io.elkaim.canvas.challenge.utils;

import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;

/**
 * Provide useful methods for masks creation.
 */
public class MaskUtils {

    public static void drawHorizontal(DrawTable mask, int y, int xLeft, int xRight, Character chr) {
        int min;
        int max;
        if (xLeft > xRight) {
            min = xRight;
            max = xLeft;
        } else {
            min = xLeft;
            max = xRight;
        }
        for (int i = min; i <= max; i++) {
            mask.addPoint(Point.builder().x(i).y(y).value(chr).build());
        }
    }

    public static void drawVertical(DrawTable mask, int x, int yTop, int yBottom, Character chr) {
        int min;
        int max;
        if (yTop > yBottom) {
            min = yBottom;
            max = yTop;
        } else {
            min = yTop;
            max = yBottom;
        }
        for (int i = min; i <= max; i++) {
            mask.addPoint(Point.builder().x(x).y(i).value(chr).build());
        }
    }
}
