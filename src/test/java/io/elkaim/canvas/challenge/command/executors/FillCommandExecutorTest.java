package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.CanvasServiceImpl;
import io.elkaim.canvas.challenge.canvas.exceptions.NoCanvasExistsException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FillCommandExecutorTest {

    @Spy
    private CanvasServiceImpl canvasService;

    @Mock
    private MessagePrinter printer;

    @InjectMocks
    private FillCommandExecutor sut;

    @Test
    void should_execute_missing_required_arg_throw_error() {
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Command missingYAndColor = new Command(CommandType.FILL,"50");
        Command missingOnlyY = new Command(CommandType.FILL,"50 c");
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(missingYAndColor));
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(missingOnlyY));
    }

    @Test
    void should_execute_throw_if_no_canvas() {
        Command normal = new Command(CommandType.RECTANGLE,"30 3 r");
        Assertions.assertThrows(NoCanvasExistsException.class, ()-> this.sut.execute(normal));
    }



    @Test
    void should_execute_on_good_body_fill_area(){
        Command cmd = new Command(CommandType.FILL,"30 3 c");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Assertions.assertEquals(200, canvas.getPoints().size());
        Assertions.assertEquals('c', canvas.getPoints().stream().toList().get(0).getValue());

    }

    @Test
    void should_execute_on_good_body_missing_color_default_it(){
        Command cmd = new Command(CommandType.FILL,"30 3");

        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Assertions.assertEquals('x', canvas.getPoints().stream().toList().get(0).getValue());

    }

    @Test
    void should_execute_on_good_body_repaint_existing_points(){
        Command cmd = new Command(CommandType.FILL,"30 3");

        Canvas canvas = new Canvas(40, 5);
        canvas.addPoint(Point.builder().x(30).y(3).value('v').build());
        Assertions.assertEquals('v',canvas.getPointValue(30,3));

        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);
        Assertions.assertEquals('x', canvas.getPointValue(30,3));
    }

    @Test
    void should_execute_on_good_body_target_existing_shape_repaint_only_this_shape(){
        Command cmd = new Command(CommandType.FILL,"30 3 t");

        Canvas canvas = new Canvas(40, 5);
        canvas.addPoint(Point.builder().x(30).y(3).value('v').build());
        Assertions.assertEquals('v',canvas.getPointValue(30,3));
        canvas.addPoint(Point.builder().x(30).y(4).value('v').build());
        Assertions.assertEquals('v',canvas.getPointValue(30,4));
        canvas.addPoint(Point.builder().x(29).y(4).value('v').build());
        Assertions.assertEquals('v',canvas.getPointValue(29,4));
        canvas.addPoint(Point.builder().x(31).y(4).value('r').build());
        Assertions.assertEquals('r',canvas.getPointValue(31,4));


        this.canvasService.setCanvas(canvas);
        this.sut.execute(cmd);

        Assertions.assertEquals('t', canvas.getPointValue(30,3));
        Assertions.assertEquals('t', canvas.getPointValue(30,4));
        Assertions.assertEquals('t', canvas.getPointValue(29,4));
        Assertions.assertEquals('r', canvas.getPointValue(31,4),
                "Only shape should has been repainted");
        Assertions.assertNull(canvas.getPointValue(29,3),
                "Only shape should has been repainted");
    }

    @Test
    void should_execute_throw_if_outside_canvas_bound_throw_error() {
        Command cmd = new Command(CommandType.FILL,"50 6 r");
        Canvas canvas = new Canvas(40, 5);
        this.canvasService.setCanvas(canvas);
        Assertions.assertThrows(MalFormedCommandException.class, ()-> this.sut.execute(cmd));
    }
}
