package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

public class BulkFillExecutor extends AbstractCanvasExecutor {
    public BulkFillExecutor(CanvasService canvasService) {
        super(canvasService);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.CREATE_RECTANGLE;
    }

    @Override
    public void execute(Command cmd) {
        //TODO: Ask to CanvasService to draw a rectangle

    }
}
