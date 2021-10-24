package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.painter.MessagePainter;
import io.elkaim.canvas.challenge.utils.CoordinatesHelper;

public abstract class AbstractShapeDrawerExecutor extends AbstractCanvasDrawerExecutor {

    public AbstractShapeDrawerExecutor(MessagePainter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    protected void warnShapeIsOutOfCanvasBounds(int x1, int y1, int x2, int y2, Canvas canvas) {
        Integer height = canvas.getHeight();
        Integer width = canvas.getWidth();
        if( !CoordinatesHelper.isInsideCanvas(x1, y1, width, height) ||
                !CoordinatesHelper.isInsideCanvas(x2, y2, width, height)){
            this.messagePainter.draw("- WARNING - Shape drawn outside the Canvas. Please enlarge the canvas to see it.");
        }
    }
}
