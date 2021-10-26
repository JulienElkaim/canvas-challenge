package io.elkaim.canvas.challenge.canvas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
public class Coordinate {
    private Integer x;
    private Integer y;
}
