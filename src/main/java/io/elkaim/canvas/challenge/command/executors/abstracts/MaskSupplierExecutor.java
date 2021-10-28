package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.exceptions.NoCanvasExistsException;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.Coordinate;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import io.elkaim.canvas.challenge.utils.CoordinatesUtils;

import java.util.Arrays;

public abstract class MaskSupplierExecutor extends CanvasRelatedExecutor {

    public MaskSupplierExecutor(MessagePrinter printer, CanvasService canvasService) {
        super(printer, canvasService);
    }

    public void checkCanvasExists() {
        if (canvasService.canvasNotYetCreated()) {
            throw new NoCanvasExistsException();
        }
    }

    /**
     * Verify if any points are outside the canvas to be able to notify the user if there is a chance they don't see
     * the figure they are drawing.
     *
     * @param points elements to be checked.
     */
    protected void isOutsideCanvas(Coordinate... points) {
        Canvas canvas = this.canvasService.getCanvas();
        Integer height = canvas.getHeight();
        Integer width = canvas.getWidth();
        if (Arrays.stream(points).anyMatch(p -> !CoordinatesUtils.isInsideCanvas(p, width, height))) {
            this.messagePrinter.print("- WARNING - Shape is fully or partially outside the Canvas. You have to enlarge the canvas to see it.");
        }
    }


}
