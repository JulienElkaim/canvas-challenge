package io.elkaim.canvas.challenge.canvas;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * TODO: Singleton ?? Tout le monde pourra dans ce cas getInstance
 */
@Getter
@AllArgsConstructor
public class Canvas {
    private final Integer width;
    private final Integer height;

}
