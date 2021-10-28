package io.elkaim.canvas.challenge.utils;

import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MaskUtilsTest {

    @Test
    void should_drawHorizontal_draw_line() {
        int y = 10;
        int x1 = 4;
        int x2 = 15;
        DrawTable mask = new DrawTable();
        MaskUtils.drawHorizontal(mask, y, x1, x2, 'r');
        Assertions.assertNotNull(mask.getPointValue(x1 + (x2-x1)/2, y));
        mask.getPoints().forEach((point)->
            Assertions.assertTrue(point.getX() >= x1 && point.getX() <= x2 && point.getY().equals(y),
                    String.format("Point (%s,%s) not expected", point.getX(), point.getY()))
        );
    }

    @Test
    void should_drawVertical_draw_line() {
        int x = 10;
        int y1 = 4;
        int y2 = 15;
        DrawTable mask = new DrawTable();
        MaskUtils.drawVertical(mask, x, y1, y2, 'r');
        Assertions.assertNotNull(mask.getPointValue(x,y1 + (y2-y1)/2));
        mask.getPoints().forEach((point)->
                Assertions.assertTrue(point.getY() >= y1 && point.getY() <= y2 && point.getX().equals(x),
                        String.format("Point (%s,%s) not expected", point.getX(), point.getY()))
        );
    }
}
