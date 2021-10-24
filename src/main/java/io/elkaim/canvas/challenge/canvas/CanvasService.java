package io.elkaim.canvas.challenge.canvas;

import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.PointTable;

/**
 * Knows how to handle and update a Canvas Object.
 *
 */
public interface CanvasService {

    Canvas getCanvas();
    void setCanvas(Canvas canvas);
    Canvas addElement(PointTable points, boolean isInFront);

    boolean canvasExist();

}
