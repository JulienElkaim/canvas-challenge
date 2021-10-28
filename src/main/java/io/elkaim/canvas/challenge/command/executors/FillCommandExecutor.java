package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.executors.abstracts.MaskSupplierExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import io.elkaim.canvas.challenge.utils.CoordinatesUtils;

import java.util.Map;
import java.util.Objects;

public class FillCommandExecutor extends MaskSupplierExecutor {

    public FillCommandExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.FILL;
    }

    @Override
    public void execute(Command cmd) {
        this.checkCanvasExists();
        Map<String, String> args = this.parseCommandBody(this.getCommandType().getBodyFormat(), cmd.getBody());

        String colorArg = getArg(args, "color", false);
        Character value = Objects.isNull(colorArg) ? BASIC_POINT_VALUE : colorArg.charAt(0);

        Point origin = Point.builder()
                .value(value)
                .x(Integer.parseInt(this.getArg(args, "x")))
                .y(Integer.parseInt(this.getArg(args, "y")))
                .build();

        Canvas canvas = this.canvasService.getCanvas();

        CoordinatesUtils.assertCoordinatesPositive(origin.getX(), origin.getY());
        if (origin.getY() > canvas.getHeight() || origin.getX() > canvas.getWidth()) {
            throw new MalFormedCommandException(String
                    .format("Origin point of fill function is not inside the canvas's limits. (%s,%s)",
                            origin.getX(), origin.getY()));
        }

        Character oldValue = canvas.getPointValue(origin.getX(), origin.getY());

        DrawTable mask = new DrawTable();
        fillArea(mask, origin, oldValue, canvas);

        this.canvasService.applyMask(mask, true);
    }

    /**
     * Colorize space with defined color {@see newValue}. Only propagate to adjacent pixels with
     * same initial value as the origin point.
     * This method colorize empty spaces, shapes already drawn on the canvas.
     * As a Canvas displays only between width and height but has points outside its bounds (svg editor like)
     * Limit of colorizing is Canvas's bounds, to avoid infinite recursion of this method.
     *
     * @param mask             mask that will be applied to the canvas to colorize.
     * @param point            is the next point to be assessed for change (fill function).
     * @param originPointValue value of original point of recursion.
     * @param canvasReference  canvas object to define limits of fillArea function.
     */
    private void fillArea(DrawTable mask, Point point,
                          Character originPointValue, Canvas canvasReference) {

        Character value = canvasReference.getPointValue(point.getX(), point.getY());

        //If this point's value equals the origin point's value and
        // the mask did not already define  a new value at this coordinate
        if (((Objects.isNull(value) && Objects.isNull(originPointValue)) ||
                (value != null && value.equals(originPointValue))) &&
                Objects.isNull(mask.getPointValue(point.getX(), point.getY()))) {
            mask.addPoint(point);

            if (point.getX() - 1 > 0) {
                Point leftPoint = Point.builder().x(point.getX() - 1).y(point.getY())
                        .value(point.getValue()).build();
                fillArea(mask, leftPoint, originPointValue, canvasReference);
            }
            if (point.getY() - 1 > 0) {
                Point upperPoint = Point.builder().x(point.getX()).y(point.getY() - 1)
                        .value(point.getValue()).build();
                fillArea(mask, upperPoint, originPointValue, canvasReference);
            }
            if (point.getX() + 1 <= canvasReference.getWidth()) {
                Point rightPoint = Point.builder().x(point.getX() + 1).y(point.getY())
                        .value(point.getValue()).build();
                fillArea(mask, rightPoint, originPointValue, canvasReference);
            }
            if (point.getY() + 1 <= canvasReference.getHeight()) {
                Point bottomPoint = Point.builder().x(point.getX()).y(point.getY() + 1)
                        .value(point.getValue()).build();
                fillArea(mask, bottomPoint, originPointValue, canvasReference);
            }
        }
    }
}
