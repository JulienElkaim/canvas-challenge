package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasServiceImplTest {

    private CanvasService sut;
    private Canvas canvas;

    @BeforeEach
    public void init() {
        this.sut = new CanvasServiceImpl();
        this.canvas = new Canvas(40, 10);

    }

    @Test
    void should_getCanvas_throw_if_no_canvas() {
        Assertions.assertThrows(MalFormedCommandException.class,
                () -> this.sut.getCanvas());
    }

    @Test
    void should_getCanvas_return_existing_canvas() {
        this.sut.setCanvas(this.canvas);
        Assertions.assertDoesNotThrow(() -> this.sut.getCanvas());
        Assertions.assertSame(this.canvas, this.sut.getCanvas());
    }

    @Test
    void should_applyMask_add_masks_points_to_canvas() {
        DrawTable mask = new DrawTable();
        Point pMask = Point.builder()
                .x(20)
                .y(3)
                .value('t')
                .build();
        mask.addPoint(pMask);
        Assertions.assertEquals(0, this.canvas.getPoints().size(),
                "Canvas should have no points beore applyMask");
        this.sut.setCanvas(this.canvas);
        this.sut.applyMask(mask,true);
        Assertions.assertEquals(1, this.canvas.getPoints().size(),
                "Canvas should now have points from mask");
        Assertions.assertSame(pMask.getValue(),
                this.canvas.getPoints().stream().toList().get(0).getValue());
    }

    @Test
    void should_applyMask_override_overlapping_points_if_priority_true() {
        DrawTable mask = new DrawTable();
        Point pMask = Point.builder()
                .x(20)
                .y(3)
                .value('n')
                .build();
        Point pCanvas = Point.builder()
                .x(20)
                .y(3)
                .value('o')
                .build();
        mask.addPoint(pMask);
        canvas.addPoint(pCanvas);
        this.sut.setCanvas(this.canvas);
        Character valBeforeApply = this.sut.getCanvas().getPointValue(20, 3);
        Assertions.assertEquals('o', valBeforeApply);
        this.sut.applyMask(mask, true);
        Character valAfterApply = this.sut.getCanvas().getPointValue(20, 3);
        Assertions.assertEquals('n', valAfterApply,
                "Value of point on Mask should have erased the value of existing point on canvas");
    }

    @Test
    void should_applyMask_NOT_override_overlapping_points_if_priority_false() {
        DrawTable mask = new DrawTable();
        Point pMask = Point.builder()
                .x(20)
                .y(3)
                .value('n')
                .build();
        Point pCanvas = Point.builder()
                .x(20)
                .y(3)
                .value('o')
                .build();
        mask.addPoint(pMask);
        canvas.addPoint(pCanvas);
        this.sut.setCanvas(this.canvas);
        Character valBeforeApply = this.sut.getCanvas().getPointValue(20, 3);
        Assertions.assertEquals('o', valBeforeApply);
        this.sut.applyMask(mask, false);
        Character valAfterApply = this.sut.getCanvas().getPointValue(20, 3);
        Assertions.assertEquals('o', valAfterApply,
                "Value of point on Mask should not have erased the previous value");

    }

    @Test
    void should_canvasNotYetCreated_return_true_if_no_canvas() {
        Assertions.assertTrue(this.sut.canvasNotYetCreated(),
                "Canvas does not exists, so should be true.");
    }

    @Test
    void should_canvasNotYetCreated_return_false_if_existing_canvas() {
        this.sut.setCanvas(this.canvas);
        Assertions.assertFalse(this.sut.canvasNotYetCreated(),
                "Canvas exists, so should be false.");
    }
}
