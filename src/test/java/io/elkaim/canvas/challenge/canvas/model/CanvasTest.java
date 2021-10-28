package io.elkaim.canvas.challenge.canvas.model;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CanvasTest {

    private Canvas sut;

    @BeforeEach
    public void init() {
        this.sut = new Canvas(40, 5);
    }

    @Test
    void should_addPointIfAbsent_add_when_coordinates_not_yet_set() {
        Point p1 = Point.builder()
                .x(20)
                .y(3)
                .value('t')
                .build();
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "Canvas should be totally empty at first.");
        this.sut.addPointIfAbsent(p1);
        Assertions.assertEquals(1, this.sut.getPoints().size(),
                "Canvas should now have 1 point.");
        Assertions.assertEquals('t', this.sut.getPointValue(p1.getX(), p1.getY()),
                "Point value should be " + p1.getValue());

        Point p2 = Point.builder()
                .x(20)
                .y(4)
                .value('r')
                .build();
        this.sut.addPointIfAbsent(p2);
        Assertions.assertEquals(2, this.sut.getPoints().size(),
                "Canvas should now have 1 point.");
        Assertions.assertEquals('r', this.sut.getPointValue(p2.getX(), p2.getY()),
                "Point value should be " + p1.getValue());

    }

    @Test
    void should_addPointIfAbsent_not_add_when_coordinates_already_set() {
        Point p1 = Point.builder()
                .x(20)
                .y(3)
                .value('x')
                .build();
        Point p2 = Point.builder()
                .x(20)
                .y(3)
                .value('r')
                .build();
        this.sut.addPointIfAbsent(p1);
        this.sut.addPointIfAbsent(p2);
        Assertions.assertEquals(1, this.sut.getPoints().size(),
                "Canvas should have only 1 point.");
        Assertions.assertEquals('x', this.sut.getPointValue(p1.getX(), p1.getY()),
                "Point value should be " + p1.getValue());

    }

    @Test
    void should_addPointIfAbsent_throw_if_point_of_bound() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "Canvas should be totally empty at first.");
        Point pOutOfBounds1 = Point.builder()
                .x(3)
                .y(Canvas.MAX_HEIGHT * 10)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPointIfAbsent(pOutOfBounds1);
        }, "Out of upper-bound Y, should throw an error");
        Point pOutOfBounds2 = Point.builder()
                .x(Canvas.MAX_WIDTH * 10)
                .y(3)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPointIfAbsent(pOutOfBounds2);
        }, "Out of upper-bound X, should throw an error");

        Point pOutOfBounds3 = Point.builder()
                .x(-1)
                .y(3)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPointIfAbsent(pOutOfBounds3);
        }, "X value can't be negative, should throw an error");

        Point pOutOfBounds4 = Point.builder()
                .x(3)
                .y(-1)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPointIfAbsent(pOutOfBounds4);
        }, "Y value can't be negative, should throw an error");

        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "Points should not have been added to the canvas.");

    }
}
