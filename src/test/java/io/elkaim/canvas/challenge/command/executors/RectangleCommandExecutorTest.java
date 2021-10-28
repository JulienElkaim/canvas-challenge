package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RectangleCommandExecutorTest {


    @Spy
    private CanvasServiceImpl canvasService;

    @Mock
    private MessagePrinter printer;

    @InjectMocks
    private RectangleCommandExecutor sut;

    @Test
    void should_execute_missing_required_arg_throw_error() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command missingY2 = new Command(CommandType.RECTANGLE,"50 10 40");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(missingY2));
    }

    @Test
    void should_execute_throw_if_no_canvas() {
        Command normal = new Command(CommandType.RECTANGLE,"30 2 40 3 r");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(normal));
    }


    @Test
    void should_execute_on_good_cmd_tolerate_missing_optional_arg(){
        Command missingColor = new Command(CommandType.RECTANGLE,"30 2 40 4");
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertDoesNotThrow(()-> this.sut.execute(missingColor));
    }

    @Test
    void should_execute_throw_on_aligned_corners() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command rectAsHorizontalLine = new Command(CommandType.RECTANGLE,"30 2 40 2");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(rectAsHorizontalLine));
        Command rectAsVerticalLine = new Command(CommandType.RECTANGLE,"30 2 30 4");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(rectAsVerticalLine));
    }

    @Test
    void should_execute_throw_on_corners_order_reversed() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command reversedCorner = new Command(CommandType.RECTANGLE,"40 4 30 2");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(reversedCorner));
    }

    @Test
    void should_execute_on_good_create_rectangle(){
        Command cmd = new Command(CommandType.RECTANGLE,"30 2 40 4");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);

        Assertions.assertEquals(0, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals(24, canvas.getPoints().size());
        Assertions.assertEquals('x', canvas.getPointValue(35,2));
        Assertions.assertEquals('x', canvas.getPointValue(35,4));
    }

    @Test
    void should_execute_on_good_with_color_use_it(){
        Command cmd = new Command(CommandType.RECTANGLE,"30 2 40 4 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Assertions.assertEquals('r', canvas.getPointValue(35,2));

    }

    @Test
    void should_execute_on_good_body_low_priority_not_override_existing_points(){
        Command cmd = new Command(CommandType.RECTANGLE,"30 2 40 4 r --low");

        Canvas canvas = new Canvas(40, 5);
        canvas.addPoint(Point.builder().x(35).y(2).value('o').build());
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(1, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals('o', canvas.getPointValue(35,2));
        Assertions.assertEquals('r', canvas.getPointValue(34,2));
    }

    @Test
    void should_execute_print_warn_message_when_outside_canvas(){
        Command cmd = new Command(CommandType.RECTANGLE,"30 2 50 4 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Mockito.verify(this.printer, Mockito.atLeastOnce()).print(Mockito.contains("- WARNING -"));
    }
}
