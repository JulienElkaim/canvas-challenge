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

public class LineCommandExecutor extends MaskSupplierExecutor {

    public LineCommandExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.LINE;
    }

    @Override
    public void execute(Command cmd) {
        this.checkCanvasExists();
        Map<String, String> args = this.parseCommandBody(this.getCommandType().getBodyFormat(), cmd.getBody());


        Coordinate p1 = Coordinate.builder()
                .x(Integer.parseInt(getArg(args, "x1"))).y(Integer.parseInt(getArg(args, "y1")))
                .build();
        Coordinate p2 = Coordinate.builder()
                .x(Integer.parseInt(getArg(args, "x2"))).y(Integer.parseInt(getArg(args, "y2")))
                .build();

        String colorArg = getArg(args, "color", false);
        Character value = Objects.isNull(colorArg) ? BASIC_POINT_VALUE : colorArg.charAt(0);

        CoordinatesUtils.assertXCoordinates(p1.getX(), p2.getX());
        CoordinatesUtils.assertYCoordinates(p1.getY(), p2.getY());

        DrawTable mask = new DrawTable();
        if (p1.getX().equals(p2.getX())) { //vertical
            MaskUtils.drawVertical(mask, p1.getX(), p1.getY(), p2.getY(), value);
        } else if (p1.getY().equals(p2.getY())) { //horizontal
            MaskUtils.drawHorizontal(mask, p1.getY(), p1.getX(), p2.getX(), value);
        } else { // Diagonal
            throw new MalFormedCommandException("Diagonal lines are not supported for the moment.");
        }

        this.canvasService.applyMask(mask, Objects.isNull(getArg(args, "noPriority", false)));
        this.isOutsideCanvas(p1, p2);
    }


}
