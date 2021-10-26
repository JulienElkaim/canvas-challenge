package io.elkaim.canvas.challenge.canvas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Represent a point in a 2D space (Coordinates + value at these coordinates(
 */
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class Point extends Coordinate {
    private Character value;
}
