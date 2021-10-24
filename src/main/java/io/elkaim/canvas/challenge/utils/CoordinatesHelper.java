package io.elkaim.canvas.challenge.utils;

import io.elkaim.canvas.challenge.ErrorMessageException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoordinatesHelper {

    public static boolean isInsideCanvas(int x, int y, int cWidth, int cHeight){
        return x <= cWidth && y <= cHeight;

    }
    public static void assertXCoordinates(int... xs) throws ErrorMessageException {
        assertCoordinateLowerThan( Arrays.stream(xs), Canvas.MAX_WIDTH, "Max width is " + Canvas.MAX_WIDTH);
        assertCoordinatesPositive(Arrays.stream(xs));
    }

    public static void assertYCoordinates(int... ys) throws ErrorMessageException {
        assertCoordinateLowerThan(Arrays.stream(ys), Canvas.MAX_WIDTH, "Max height is " + Canvas.MAX_HEIGHT);
        assertCoordinatesPositive(Arrays.stream(ys));
    }

    private static void assertCoordinatesPositive(IntStream values) throws ErrorMessageException {
        String valuesUnexpected = values.filter(v -> v <= 0)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
        if(!valuesUnexpected.isEmpty()){
            throw new MalFormedCommandException("Only positives values accepted. Please review: " + valuesUnexpected);
        }
    }



    public static void assertXLowerThanMaxWidth(int... xs) throws ErrorMessageException {
        assertCoordinateLowerThan(Arrays.stream(xs), Canvas.MAX_WIDTH, "Max width is " + Canvas.MAX_WIDTH);
    }

    public static void assertYLowerThanMaxHeight(int... ys) throws ErrorMessageException {
        assertCoordinateLowerThan(Arrays.stream(ys), Canvas.MAX_HEIGHT, "Max height is " + Canvas.MAX_HEIGHT);
    }

    private static void assertCoordinateLowerThan(IntStream coos, int upperBound, String message){
        String valuesUnexpected = coos.filter(coo -> coo > upperBound)
                .mapToObj(String::valueOf).collect(Collectors.joining(", "));
        if(!valuesUnexpected.isEmpty()){
            throw new MalFormedCommandException(String
                    .format("%s. Following values are out of bounds: %s .", message, valuesUnexpected));
        }
    }

}
