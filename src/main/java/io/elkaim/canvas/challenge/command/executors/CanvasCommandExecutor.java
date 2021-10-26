package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.canvas.model.Canvas;
import io.elkaim.canvas.challenge.command.executors.abstracts.CanvasRelatedExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.io.out.MessagePrinter;

import java.util.Map;

public class CanvasCommandExecutor extends CanvasRelatedExecutor {

    public CanvasCommandExecutor(MessagePrinter painter, CanvasService canvasService) {
        super(painter, canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_CANVAS;
    }

    @Override
    public void execute(Command cmd) {
        Map<String, String> args = this.parseCommandBody(this.getCommandType().getBodyFormat(), cmd.getBody());
        Integer width = Integer.parseInt(this.getArg(args, "width"));
        Integer height = Integer.parseInt(this.getArg(args, "height"));

        if (this.canvasService.canvasNotYetCreated()) {
            this.canvasService.setCanvas(new Canvas(width, height));
        } else {
            this.canvasService.getCanvas().setHeight(height);
            this.canvasService.getCanvas().setWidth(width);
        }
    }
}
