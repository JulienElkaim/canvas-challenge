package io.elkaim.canvas.challenge.utils;

import io.elkaim.canvas.challenge.app.exceptions.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Coordinate;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Provide useful methods to operate on Coordinates.
 * Common patterns, checks, are shared here and used by many components.
 */
public class CoordinatesUtils {

    public static boolean isInsideCanvas(Coordinate coo, int cWidth, int cHeight) {
        return coo.getX() <= cWidth && coo.getY() <= cHeight;
    }

    public static void assertXCoordinates(int... xs) throws ErrorMessageException {
        assertCoordinateLowerThan(Arrays.stream(xs), Canvas.MAX_WIDTH, "Max width is " + Canvas.MAX_WIDTH);
        assertCoordinatesPositive(Arrays.stream(xs));
    }

    public static void assertYCoordinates(int... ys) throws ErrorMessageException {
        assertCoordinateLowerThan(Arrays.stream(ys), Canvas.MAX_HEIGHT, "Max height is " + Canvas.MAX_HEIGHT);
        assertCoordinatesPositive(Arrays.stream(ys));
    }

    public static void assertCoordinatesPositive(int... values) throws ErrorMessageException {
        assertCoordinatesPositive(Arrays.stream(values));

    }

    private static void assertCoordinatesPositive(IntStream values) throws ErrorMessageException {
        String valuesUnexpected = values.filter(v -> v <= 0)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
        if (!valuesUnexpected.isEmpty()) {
            throw new MalFormedCommandException("Only positives values accepted. Please review: " + valuesUnexpected);
        }
    }

    private static void assertCoordinateLowerThan(IntStream coos, int upperBound, String message) {
        String valuesUnexpected = coos.filter(coo -> coo > upperBound)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
        if (!valuesUnexpected.isEmpty()) {
            throw new MalFormedCommandException(String
                    .format("%s. Following values are out of bounds: %s .", message, valuesUnexpected));
        }
    }

}
