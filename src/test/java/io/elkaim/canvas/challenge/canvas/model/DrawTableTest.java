package io.elkaim.canvas.challenge.canvas.model;

import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DrawTableTest {

    private DrawTable sut;

    @BeforeEach
    public void init() {
        this.sut = new DrawTable();
    }

    @Test
    void should_getPointValue_return_null_if_not_exist() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "DrawTable should be totally empty at first.");
        Assertions.assertNull(this.sut.getPointValue(20, 3),
                "Non existing point should result as null value");
    }

    @Test
    void should_getPointValue_return_good_value_if_exist() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "DrawTable should be totally empty at first.");
        Point p1 = Point.builder()
                .x(20)
                .y(3)
                .value('t')
                .build();
        this.sut.addPoint(p1);
        Assertions.assertNotNull(this.sut.getPointValue(p1.getX(), p1.getY()),
                "Existing point, getPointValue should not be null");

        Assertions.assertEquals('t', this.sut.getPointValue(p1.getX(), p1.getY()),
                "Result should be " + p1.getValue());
    }

    @Test
    void should_addPoint_add_new_point() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "DrawTable should be totally empty at first.");
        Point p1 = Point.builder()
                .x(20)
                .y(3)
                .value('t')
                .build();
        this.sut.addPoint(p1);
        Assertions.assertEquals(1, this.sut.getPoints().size(),
                "DrawTable should now have 1 point");
        Assertions.assertEquals(p1.getValue(), this.sut.getPointValue(p1.getX(), p1.getY()),
                "Should the point added be the same as the one inserted.");

    }

    @Test
    void should_addPoint_throw_if_point_out_of_bounds() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "DrawTable should be totally empty at first.");
        Point p1 = Point.builder()
                .x(20)
                .y(3)
                .value('t')
                .build();
        this.sut.addPoint(p1);
        Point p2 = Point.builder()
                .x(20)
                .y(3)
                .value('y')
                .build();
        this.sut.addPoint(p2);
        Assertions.assertEquals('y', this.sut.getPointValue(p1.getX(), p1.getY()),
                "Point value should have been replaced by the 2nd point's value");
    }

    @Test
    void should_addPoint_replace_existing_point_by_new_one_same_coos() {
        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "DrawTable should be totally empty at first.");
        Point pOutOfBounds1 = Point.builder()
                .x(3)
                .y(Canvas.MAX_HEIGHT * 10)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPoint(pOutOfBounds1);
        }, "Out of upper-bound Y, should throw an error");
        Point pOutOfBounds2 = Point.builder()
                .x(Canvas.MAX_WIDTH * 10)
                .y(3)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPoint(pOutOfBounds2);
        }, "Out of upper-bound X, should throw an error");

        Point pOutOfBounds3 = Point.builder()
                .x(-1)
                .y(3)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPoint(pOutOfBounds3);
        }, "X value can't be negative, should throw an error");

        Point pOutOfBounds4 = Point.builder()
                .x(3)
                .y(-1)
                .value('x')
                .build();
        Assertions.assertThrows(MalFormedCommandException.class, () -> {
            this.sut.addPoint(pOutOfBounds4);
        }, "Y value can't be negative, should throw an error");

        Assertions.assertEquals(0, this.sut.getPoints().size(),
                "No points should have been added to the drawTable.");

    }

}
