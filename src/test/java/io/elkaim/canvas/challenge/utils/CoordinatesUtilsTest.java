package io.elkaim.canvas.challenge.utils;

import io.elkaim.canvas.challenge.canvas.model.Coordinate;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CoordinatesUtilsTest {

    @Test
    void should_isInsideCanvas_return_false_when_outside() {
        Assertions.assertFalse(CoordinatesUtils.isInsideCanvas(
                        Coordinate.builder().x(30).y(5).build(), 29, 6),
                "X is not detected outside the canvas");
        Assertions.assertFalse(CoordinatesUtils.isInsideCanvas(
                        Coordinate.builder().x(30).y(5).build(), 40, 3),
                "Y is not detected outside the canvas");
    }

    @Test
    void should_isInsideCanvas_return_true_when_inside() {
        Assertions.assertTrue(CoordinatesUtils.isInsideCanvas(
                        Coordinate.builder().x(30).y(5).build(), 40, 6),
                "coo inside Canvas, but detected as not");
        Assertions.assertTrue(CoordinatesUtils.isInsideCanvas(
                        Coordinate.builder().x(30).y(5).build(), 30, 5),
                "coo inside Canvas, but detected as not");
    }

    @Test
    void should_assertXCoordinates_throw_when_at_least_one_is_out_of_bound() {
        Assertions.assertThrows(MalFormedCommandException.class, () -> CoordinatesUtils.assertXCoordinates(1, 2, -4));
        Assertions.assertThrows(MalFormedCommandException.class, () -> CoordinatesUtils.assertXCoordinates(1, 2, 90));
    }

    @Test
    void should_assertXCoordinates_pass_if_all_are_in_bounds() {
        Assertions.assertDoesNotThrow(() -> CoordinatesUtils.assertXCoordinates(1, 2, 40, 56, 80));

    }

    @Test
    void should_assertYCoordinates_throw_when_at_least_one_is_out_of_bound() {
        Assertions.assertThrows(MalFormedCommandException.class, () -> CoordinatesUtils.assertYCoordinates(1, 2, -4));
        Assertions.assertThrows(MalFormedCommandException.class, () -> CoordinatesUtils.assertYCoordinates(1, 2, 25));
    }

    @Test
    void should_assertYCoordinates_pass_if_all_are_in_bounds() {
        Assertions.assertDoesNotThrow(() -> CoordinatesUtils.assertYCoordinates(1, 2, 15, 13, 20));

    }

    @Test
    void should_assertCoordinatesPositive_throw_if_some_negatives() {
        Assertions.assertThrows(MalFormedCommandException.class, () -> CoordinatesUtils.assertYCoordinates(10, 2, -9));
    }

    @Test
    void should_assertCoordinatesPositive_pass_if_all_positives() {
        Assertions.assertDoesNotThrow(() -> CoordinatesUtils.assertCoordinatesPositive(1, 2, 15, 13, 20));
    }
}
