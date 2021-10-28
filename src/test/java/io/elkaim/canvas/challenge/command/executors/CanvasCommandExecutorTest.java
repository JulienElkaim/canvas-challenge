package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
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
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CanvasCommandExecutorTest {

    @Mock
    private CanvasService canvasService;

    @Mock
    private MessagePrinter printer;

    @InjectMocks
    private CanvasCommandExecutor sut;


    @Test
    void should_execute_on_bad_cmd_throw_error() {
        Command missingHeight = new Command(CommandType.CREATE_CANVAS, "50");
        Assertions.assertThrows(MalFormedCommandException.class, () -> this.sut.execute(missingHeight));
    }

    @Test
    void should_execute_on_good_cmd_canvas_exist_replace_width_height() {
        Command cmd = new Command(CommandType.CREATE_CANVAS, "50 10");

        Canvas canvas = Mockito.mock(Canvas.class, Mockito.withSettings()
                .useConstructor(40, 5)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        Mockito.when(this.canvasService.getCanvas()).thenReturn(canvas);
        Mockito.when(this.canvasService.canvasNotYetCreated()).thenReturn(false);

        this.sut.execute(cmd);
        Mockito.verify(this.canvasService, Mockito.atLeastOnce()).canvasNotYetCreated();
        Mockito.verify(canvas, Mockito.atLeastOnce()).setHeight(Mockito.anyInt());
        Mockito.verify(canvas, Mockito.atLeastOnce()).setWidth(Mockito.anyInt());
        Assertions.assertEquals(50, canvas.getWidth(), "Width should have change.");
        Assertions.assertEquals(10, canvas.getHeight(), "Height should have change.");
    }

    @Test
    void should_execute_on_good_cmd_no_canvas_create_one() {
        Command cmd = new Command(CommandType.CREATE_CANVAS, "50 10");

        Mockito.when(this.canvasService.canvasNotYetCreated()).thenReturn(true);

        this.sut.execute(cmd);
        Mockito.verify(this.canvasService, Mockito.atLeastOnce()).canvasNotYetCreated();
        Mockito.verify(this.canvasService, Mockito.atLeastOnce()).setCanvas(Mockito.any());
    }
}
