package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.command.exceptions.MalFormedCommandException;
import io.elkaim.canvas.challenge.command.executors.abstracts.AbstractCanvasDrawerExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.painter.MessagePainter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanvasCreationExecutor extends AbstractCanvasDrawerExecutor {

    private static final String CANVAS_PROPS_PATTERN = "^(?<width>\\d+)\\s+(?<height>\\d+)\\s*$";


    public CanvasCreationExecutor(MessagePainter painter, CanvasService canvasService) {
        super( painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_CANVAS;
    }

    @Override
    public void execute(Command cmd) {
        // Get le width et height from cmd body
        final Pattern pattern = Pattern.compile(CANVAS_PROPS_PATTERN);
        final Matcher matcher = pattern.matcher(cmd.getBody().trim());

        if (matcher.find()) {
            Integer width = Integer.parseInt(matcher.group("width"));
            Integer height = Integer.parseInt(matcher.group("height"));
            if(!this.canvasService.canvasExist()){
                // si canvas exist pas, le creer !
                this.canvasService.setCanvas(new Canvas(width,height));

            }else{
                // Changer le width et height !
                this.canvasService.getCanvas().setHeight(height);
                this.canvasService.getCanvas().setWidth(width);
            }
        }else{
            throw new MalFormedCommandException("Unable to find width and height in your command's body: " + cmd.getBody());
        }



    }
}
