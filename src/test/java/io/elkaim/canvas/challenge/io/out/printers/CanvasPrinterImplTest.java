package io.elkaim.canvas.challenge.io.out.printers;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.io.out.CanvasPrinter;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CanvasPrinterImplTest {

    private CanvasPrinter sut;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void init(){
        this.outputStream= new ByteArrayOutputStream();
        this.sut = new CanvasPrinterImpl(new PrintStream(outputStream));



    }

    @Test
    void should_print_canvas_in_expected_format_on_output_stream() {
        Canvas canvas = new Canvas(2, 2);
        canvas.addPoint(Point.builder().x(1).y(1).value('o').build());

        Assertions.assertTrue(this.outputStream.toString().isEmpty());
        this.sut.print(canvas);

        StringBuilder result = new StringBuilder()
                .append("____\n")
                .append("║o ║\n")
                .append("║  ║\n")
                .append("¯¯¯¯\n");
        Assertions.assertEquals(result.toString(), this.outputStream.toString());
    }

    @Test
    void should_print_canvas_with_only_points_inside_its_limits() {
        Canvas canvas = new Canvas(2, 2);
        canvas.addPoint(Point.builder().x(2).y(2).value('t').build());
        canvas.addPoint(Point.builder().x(5).y(6).value('t').build());

        Assertions.assertTrue(this.outputStream.toString().isEmpty());
        this.sut.print(canvas);

        StringBuilder result = new StringBuilder()
                .append("____\n")
                .append("║  ║\n")
                .append("║ t║\n")
                .append("¯¯¯¯\n");
        Assertions.assertEquals(result.toString(), this.outputStream.toString());
    }

}
