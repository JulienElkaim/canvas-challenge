package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MaskSupplierExecutorTest {
    @Mock
    private CanvasService canvasService;

    @Mock
    private MessagePrinter printer;

    private MaskSupplierExecutor sut;

    private Canvas canvas;


    @BeforeEach
    void init(){
        this.sut = Mockito.mock(MaskSupplierExecutor.class, Mockito.withSettings()
                .useConstructor(this.printer, this.canvasService)
                .defaultAnswer(Mockito.CALLS_REAL_METHODS));

        this.canvas = new Canvas(40,5);
    }
    @Test
    void should_checkCanvasExists_throw_If_no_canvas() {
        Mockito.when(this.canvasService.canvasNotYetCreated()).thenReturn(true);
        Assertions.assertThrows(MalFormedCommandException.class,()-> this.sut.checkCanvasExists());
    }

    @Test
    void should_checkCanvasExists_pass_If_canvas_exist() {
        Mockito.when(this.canvasService.canvasNotYetCreated()).thenReturn(false);
        Assertions.assertDoesNotThrow(()-> this.sut.checkCanvasExists());

    }

    @Test
    void should_isOutsideCanvas_print_on_any_point_outside_canvas_limits() {
        Mockito.when(this.canvasService.getCanvas()).thenReturn(this.canvas);
        Point p1 = Point.builder()
                .x(3)
                .y(3)
                .value('x')
                .build();
        Point p2 = Point.builder()
                .x(55)
                .y(5)
                .value('x')
                .build();
        this.sut.isOutsideCanvas(p1,p2);
        Mockito.verify(this.printer,Mockito.atLeastOnce()).print(Mockito.anyString());

    }

    @Test
    void should_isOutsideCanvas_do_not_print_anything_on_no_point_outside_canvas_limits() {
        Mockito.when(this.canvasService.getCanvas()).thenReturn(this.canvas);
        Point p1 = Point.builder()
                .x(3)
                .y(3)
                .value('x')
                .build();
        Point p2 = Point.builder()
                .x(4)
                .y(5)
                .value('x')
                .build();
        this.sut.isOutsideCanvas(p1,p2);
        Mockito.verify(this.printer,Mockito.never()).print(Mockito.anyString());


    }
}
