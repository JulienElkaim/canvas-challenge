package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.DrawTable;
import io.elkaim.canvas.challenge.canvas.model.Point;
import io.elkaim.canvas.challenge.command.executors.abstracts.MaskSupplierExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;
import io.elkaim.canvas.challenge.utils.CoordinatesUtils;

import java.util.Map;
import java.util.Objects;

public class PointCommandExecutor extends MaskSupplierExecutor {

    public PointCommandExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.POINT;
    }

    @Override
    public void execute(Command cmd) {
        this.checkCanvasExists();
        Map<String, String> args = this.parseCommandBody(this.getCommandType().getBodyFormat(), cmd.getBody());

        String colorArg = getArg(args, "color", false);
        Character value = Objects.isNull(colorArg) ? BASIC_POINT_VALUE : colorArg.charAt(0);

        Point point = Point.builder()
                .x(Integer.parseInt(getArg(args, "x")))
                .y(Integer.parseInt(getArg(args, "y")))
                .value(value)
                .build();

        CoordinatesUtils.assertXCoordinates(point.getX());
        CoordinatesUtils.assertYCoordinates(point.getY());

        DrawTable mask = new DrawTable();
        mask.addPoint(point);

        this.canvasService.applyMask(mask, true);
        isOutsideCanvas(point);

    }


}
