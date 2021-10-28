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
class LineCommandExecutorTest {

    @Spy
    private CanvasServiceImpl canvasService;

    @Mock
    private MessagePrinter printer;

    @InjectMocks
    private LineCommandExecutor sut;

    @Test
    void should_execute_missing_required_arg_throw_error() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command missingY2 = new Command(CommandType.LINE,"50 10 40");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(missingY2));
    }

    @Test
    void should_execute_throw_if_no_canvas() {
        Command normal = new Command(CommandType.RECTANGLE,"30 2 40 3 r");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(normal));
    }



    @Test
    void should_execute_on_good_cmd_tolerate_missing_optional_arg(){
        Command missingColor = new Command(CommandType.LINE,"50 10 40 10");
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertDoesNotThrow(()-> this.sut.execute(missingColor));
    }
    @Test
    void should_execute_on_good_create_line_when_horizontal(){
        Command cmd = new Command(CommandType.LINE,"50 10 40 10");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(0, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals(11, canvas.getPoints().size());
        Assertions.assertEquals('x', canvas.getPointValue(45,10));
        Assertions.assertEquals('x', canvas.getPoints().stream().findAny().get().getValue());
    }

    @Test
    void should_execute_on_good_create_line_when_vertical(){
        Command cmd = new Command(CommandType.LINE,"50 1 50 10");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(0, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals(10, canvas.getPoints().size());
        Assertions.assertEquals('x', canvas.getPointValue(50,5));
        Assertions.assertEquals('x', canvas.getPoints().stream().findAny().get().getValue());
    }

    @Test
    void should_execute_throw_when_diagonal(){
        Command cmd = new Command(CommandType.LINE,"40 1 50 10");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(cmd));

    }

    @Test
    void should_execute_on_good_with_color_use_it(){
        Command cmd = new Command(CommandType.LINE,"50 10 40 10 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Assertions.assertEquals('r', canvas.getPointValue(45,10));

    }

    @Test
    void should_execute_on_good_body_low_priority_not_override_existing_points(){
        Command cmd = new Command(CommandType.LINE,"50 10 40 10 r --low");

        Canvas canvas = new Canvas(40, 5);
        canvas.addPoint(Point.builder().x(45).y(10).value('o').build());
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(1, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals('o', canvas.getPointValue(45,10));
        Assertions.assertEquals('r', canvas.getPointValue(46,10));
    }

    @Test
    void should_execute_print_warn_message_when_outside_canvas(){
        Command cmd = new Command(CommandType.LINE,"50 10 40 10 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Mockito.verify(this.printer, Mockito.atLeastOnce()).print(Mockito.contains("- WARNING -"));
    }

}
