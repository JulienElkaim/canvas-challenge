package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.PointTable;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCreationExecutor extends AbstractCanvasExecutor {

    private final String LINE_COORDINATES_PATTERN = "^(?<x1>\\d+)\\s+(?<y1>\\d+)\\s+(?<x2>\\d+)\\s+(?<y2>\\d+)\\s*$";

    public LineCreationExecutor(CanvasService canvasService) {
        super(canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_LINE;
    }

    @Override
    public void execute(Command cmd) {
        if (!canvasService.canvasExist()) {
            throw MalFormedCommandException.NO_CANVAS;
        }

        final Pattern pattern = Pattern.compile(LINE_COORDINATES_PATTERN);
        final Matcher matcher = pattern.matcher(cmd.getBody());

        if (matcher.find()) {
            Integer x1 = Integer.parseInt(matcher.group("x1"));
            Integer y1 = Integer.parseInt(matcher.group("y1"));
            int x2 = Integer.parseInt(matcher.group("x2"));
            int y2 = Integer.parseInt(matcher.group("y2"));
            noNegativeValue(x1, y1, x2, y2);

            if (x1.equals(x2) || y1.equals(y2)) {
                PointTable line = new PointTable();
                if (x1.equals(x2)) { //horizontal
                    Integer min = Math.min(y1, y2);
                    Integer max = Math.max(y1, y2);
                    for (int i = min; i <= max; i++) {
                        line.addPoint(x1, i, BASIC_POINT_FORMAT);
                    }

                } else { //vertical
                    Integer min = Math.min(x1, x2);
                    Integer max = Math.max(x1, x2);
                    for (int i = min; i <= max; i++) {
                        line.addPoint(i, y1, BASIC_POINT_FORMAT);
                    }
                }
                this.canvasService.addElement(line, false);
            } else {
                // Diagonal
                throw new MalFormedCommandException("Non vertical or Horizontal lines are prohibited for the moment.");
            }
        } else {
            throw new MalFormedCommandException("Did not found coordinates of line into " + cmd.getBody());
        }
    }
}
