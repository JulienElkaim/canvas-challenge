package io.elkaim.canvas.challenge.integration;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.integration.utils.UserInputChain;
import io.elkaim.canvas.challenge.io.out.printers.CanvasPrinterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class DrawOnCanvasITCase extends ApplicationLevelIntegration {

    @Test
    public void user_can_draw_points() {
        InputStream inputs = UserInputChain.of("C 2 2")
                .then("P 1 1 r")
                .build();

        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        Canvas canvas = new Canvas(2, 2);
        canvas.addPoint(Point.builder().x(1).y(1).value('r').build());
        new CanvasPrinterImpl(new PrintStream(expected)).print(canvas);

        this.runFullAppScenario(inputs, this.outputStream);
        Assertions.assertTrue(this.outputStream.toString().contains(expected.toString()));
    }

    @Test
    public void user_can_draw_line() {
        InputStream inputs = UserInputChain.of("C 2 2")
                .then("L 1 1 2 1 r")
                .build();

        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        Canvas canvas = new Canvas(2, 2);
        canvas.addPoint(Point.builder().x(1).y(1).value('r').build());
        canvas.addPoint(Point.builder().x(2).y(1).value('r').build());
        new CanvasPrinterImpl(new PrintStream(expected)).print(canvas);

        this.runFullAppScenario(inputs, this.outputStream);
        Assertions.assertTrue(this.outputStream.toString().contains(expected.toString()));
    }

    @Test
    public void user_can_fill() {
        InputStream inputs = UserInputChain.of("C 2 2")
                .then("B 1 1 t")
                .build();

        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        Canvas canvas = new Canvas(2, 2);
        canvas.addPoint(Point.builder().x(1).y(1).value('t').build());
        canvas.addPoint(Point.builder().x(2).y(1).value('t').build());
        canvas.addPoint(Point.builder().x(1).y(2).value('t').build());
        canvas.addPoint(Point.builder().x(2).y(2).value('t').build());
        new CanvasPrinterImpl(new PrintStream(expected)).print(canvas);

        this.runFullAppScenario(inputs, this.outputStream);
        Assertions.assertTrue(this.outputStream.toString().contains(expected.toString()));
    }

    @Test
    public void user_can_draw_rectangle() {
        InputStream inputs = UserInputChain.of("C 4 4")
                .then("R 1 1 4 4 v")
                .build();

        ByteArrayOutputStream expected = new ByteArrayOutputStream();
        Canvas canvas = new Canvas(4, 4);
        canvas.addPoint(Point.builder().x(1).y(1).value('v').build());
        canvas.addPoint(Point.builder().x(1).y(2).value('v').build());
        canvas.addPoint(Point.builder().x(1).y(3).value('v').build());
        canvas.addPoint(Point.builder().x(1).y(4).value('v').build());
        canvas.addPoint(Point.builder().x(2).y(4).value('v').build());
        canvas.addPoint(Point.builder().x(3).y(4).value('v').build());
        canvas.addPoint(Point.builder().x(4).y(4).value('v').build());
        canvas.addPoint(Point.builder().x(4).y(3).value('v').build());
        canvas.addPoint(Point.builder().x(4).y(2).value('v').build());
        canvas.addPoint(Point.builder().x(4).y(1).value('v').build());
        canvas.addPoint(Point.builder().x(3).y(1).value('v').build());
        canvas.addPoint(Point.builder().x(2).y(1).value('v').build());
        new CanvasPrinterImpl(new PrintStream(expected)).print(canvas);


        this.runFullAppScenario(inputs, this.outputStream);
        Assertions.assertTrue(this.outputStream.toString().contains(expected.toString()));
    }


}
