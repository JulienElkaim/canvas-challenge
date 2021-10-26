package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;

/**
 * Set up the right dependency and shared attributes for any executor that will work on Canvas.
 */
public abstract class CanvasRelatedExecutor extends BasicCommandExecutor {
    protected final Character BASIC_POINT_VALUE = 'x';
    protected final CanvasService canvasService;

    public CanvasRelatedExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter);
        this.canvasService = canvasService;
    }

}
