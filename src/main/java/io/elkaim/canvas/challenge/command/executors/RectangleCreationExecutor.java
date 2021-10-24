package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.PointTable;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.executors.abstracts.AbstractCanvasDrawerExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.painter.MessagePainter;
import io.elkaim.canvas.challenge.utils.CoordinatesHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RectangleCreationExecutor extends AbstractCanvasDrawerExecutor {


    // for now, ignore added
    private final String COMMAND_BODY_PATTERN = "^(?<x1>\\d+)\\s+(?<y1>\\d+)\\s+(?<x2>\\d+)\\s+(?<y2>\\d+)\\s*$";

    public RectangleCreationExecutor(MessagePainter painter, CanvasService canvasService) {
        super( painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_RECTANGLE;
    }

    @Override
    public void execute(Command cmd) {
        if(!canvasService.canvasExist()){
            throw MalFormedCommandException.NO_CANVAS;
        }

        final Pattern pattern = Pattern.compile(COMMAND_BODY_PATTERN);
        final Matcher matcher = pattern.matcher(cmd.getBody().trim());

        if (matcher.find()){
            Integer x1 = Integer.parseInt(matcher.group("x1"));
            Integer y1 = Integer.parseInt(matcher.group("y1"));
            Integer x2 = Integer.parseInt(matcher.group("x2"));
            Integer y2 = Integer.parseInt(matcher.group("y2"));
            CoordinatesHelper.assertXCoordinates(x1, x2);
            CoordinatesHelper.assertYCoordinates(y1, y2);

            if(x1.equals(x2) || y1.equals(y2)){
                throw new MalFormedCommandException(String
                        .format("A rectangle between (%s,%s) and (%s,%s) is represented as a line. Please use the line command.",
                                x1,y1,
                                x2,y2));
            }else{
                //Compute the point closest to origin, go from there
                PointTable line = new PointTable();
                if(x1.equals(x2)){
                    //horizontal
                    Integer min = Math.min(y1, y2);
                    Integer max = Math.max(y1, y2);
                    for(int i = min; i<=max; i++){
                        line.addPoint(x1,i, BASIC_POINT_FORMAT);
                    }
                }else{
                    //vertical
                    Integer min = Math.min(x1,x2);
                    Integer max = Math.max(x1,x2);
                    for(int i = min; i<=max; i++){
                        line.addPoint(y1, i, BASIC_POINT_FORMAT);
                    }
                }


                this.canvasService.addElement(line,false);
            }
        }else{
            throw new MalFormedCommandException("Did not found coordinates of rectangle into " + cmd.getBody());
        }

    }
}
