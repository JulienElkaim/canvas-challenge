package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;

/**
 * Knows how to handle and update a Canvas Object.
 */
public interface CanvasService {

    Canvas getCanvas();

    void setCanvas(Canvas canvas);

    /**
     * Apply mask by adding its elements to the canvas.
     *
     * @param mask        contains elements (points) to add to the canvas.
     * @param hasPriority stipulates if mask's elements has priority (will override)
     *                    canvas's elements.
     */
    void applyMask(DrawTable mask, boolean hasPriority);

    /**
     * Check if the canvas service already have a canvas initialized.
     *
     * @return true if there is an existing canvas. False if none.
     */
    boolean canvasNotYetCreated();

}
