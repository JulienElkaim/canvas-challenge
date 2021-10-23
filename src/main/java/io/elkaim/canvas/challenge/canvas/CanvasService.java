package io.elkaim.canvas.challenge.canvas;

/**
 * Knows how to handle and update a Canvas Object.
 *
 */
public interface CanvasService {

    Canvas getCanvas();
    Canvas applyMask(CanvasMask mask);

}
