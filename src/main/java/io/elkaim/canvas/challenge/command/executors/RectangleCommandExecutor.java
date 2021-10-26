package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Coordinate;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.executors.abstracts.MaskSupplierExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import io.elkaim.canvas.challenge.utils.CoordinatesUtils;
import io.elkaim.canvas.challenge.utils.MaskUtils;

import java.util.Map;
import java.util.Objects;

public class RectangleCommandExecutor extends MaskSupplierExecutor {

    public RectangleCommandExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.RECTANGLE;
    }

    @Override
    public void execute(Command cmd) {
        this.checkCanvasExists();
        Map<String, String> args = this.parseCommandBody(this.getCommandType().getBodyFormat(), cmd.getBody());

        Coordinate upperLeft = Coordinate.builder()
                .x(Integer.parseInt(getArg(args, "x1")))
                .y(Integer.parseInt(getArg(args, "y1")))
                .build();
        Coordinate bottomRight = Coordinate.builder()
                .x(Integer.parseInt(getArg(args, "x2")))
                .y(Integer.parseInt(getArg(args, "y2")))
                .build();

        CoordinatesUtils.assertXCoordinates(upperLeft.getX(), bottomRight.getX());
        CoordinatesUtils.assertYCoordinates(upperLeft.getY(), bottomRight.getY());

        if (upperLeft.getX().equals(bottomRight.getX()) || upperLeft.getY().equals(bottomRight.getY())) {
            throw new MalFormedCommandException(String
                    .format("A rectangle between (%s,%s) and (%s,%s) is a line. Please use the line command instead.",
                            upperLeft.getX(), upperLeft.getY(),
                            bottomRight.getX(), bottomRight.getY()));

        } else if (upperLeft.getY() > bottomRight.getY() || upperLeft.getX() > bottomRight.getX()) {
            throw new MalFormedCommandException(String
                    .format("First point (%s,%s) should be the upper left corner of the rectangle, and the second point (%s,%s) is the bottom right corner.",
                            upperLeft.getX(), upperLeft.getY(),
                            bottomRight.getX(), bottomRight.getY()));

        } else {
            String colorArg = getArg(args, "color", false);
            Character value = Objects.isNull(colorArg) ? BASIC_POINT_VALUE : colorArg.charAt(0);
            DrawTable mask = this.createRectangleMask(upperLeft, bottomRight, value);
            this.canvasService.applyMask(mask, Objects.isNull(getArg(args, "noPriority", false)));

            this.isOutsideCanvas(upperLeft, bottomRight);
        }
    }

    /**
     * Create a rectangle mask based on the assumption upperLeft and Bottom Right corner are in the right order.
     *
     * @param upperLeft   is the upper left corner of the rectangle.
     * @param bottomRight is the bottom right corner of the rectangle.
     * @param value       is the value of the points to be added to the mask.
     * @return a mask with a rectangle shape inside.
     */
    private DrawTable createRectangleMask(Coordinate upperLeft, Coordinate bottomRight, Character value) {
        DrawTable rectangleMask = new DrawTable();
        Coordinate upperRight = Coordinate.builder()
                .x(bottomRight.getX())
                .y(upperLeft.getY())
                .build();
        Coordinate bottomLeft = Coordinate.builder()
                .x(upperLeft.getX())
                .y(bottomRight.getY())
                .build();

        MaskUtils.drawHorizontal(rectangleMask, upperLeft.getY(), upperLeft.getX(), upperRight.getX(), value);
        MaskUtils.drawHorizontal(rectangleMask, bottomLeft.getY(), bottomLeft.getX(), bottomRight.getX(), value);
        MaskUtils.drawVertical(rectangleMask, upperLeft.getX(), upperLeft.getY(), bottomLeft.getY(), value);
        MaskUtils.drawVertical(rectangleMask, upperRight.getX(), upperRight.getY(), bottomRight.getY(), value);

        return rectangleMask;
    }


}
