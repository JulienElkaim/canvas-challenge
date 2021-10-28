package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.canvas.exceptions.NoCanvasExistsException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
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

@ExtendWith(MockitoExtension.class)
class PointCommandExecutorTest {


    @Spy
    private CanvasServiceImpl canvasService;

    @Mock
    private MessagePrinter printer;

    @InjectMocks
    private PointCommandExecutor sut;

    @Test
    void should_execute_missing_required_arg_throw_error() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command missingY = new Command(CommandType.POINT, "50");
        Assertions.assertThrows(MalFormedCommandException.class, () -> this.sut.execute(missingY));
    }

    @Test
    void should_execute_throw_if_no_canvas() {
        Command normal = new Command(CommandType.RECTANGLE, "30 2 r");
        Assertions.assertThrows(NoCanvasExistsException.class, () -> this.sut.execute(normal));
    }

    @Test
    void should_execute_on_good_cmd_tolerate_missing_optional_arg() {
        Command missingColor = new Command(CommandType.POINT, "50 10");
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertDoesNotThrow(() -> this.sut.execute(missingColor));
    }

    @Test
    void should_execute_on_good_create_point() {
        Command cmd = new Command(CommandType.POINT, "30 4");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(0, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals(1, canvas.getPoints().size());
        Assertions.assertEquals('x', canvas.getPointValue(30, 4));
    }


    @Test
    void should_execute_on_good_with_color_use_it() {
        Command cmd = new Command(CommandType.POINT, "30 3 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertEquals(0, canvas.getPoints().size());
        this.sut.execute(cmd);
        Assertions.assertEquals(1, canvas.getPoints().size());
        Assertions.assertEquals('r', canvas.getPointValue(30, 3));

    }

    @Test
    void should_execute_print_warn_message_when_outside_canvas() {
        Command cmd = new Command(CommandType.POINT, "50 10 r");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Mockito.verify(this.printer, Mockito.atLeastOnce()).print(Mockito.contains("- WARNING -"));
    }
}
