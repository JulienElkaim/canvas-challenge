package io.elkaim.canvas.challenge.command.executors;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.command.executors.abstracts.AbstractCanvasDrawerExecutor;
import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;
import io.elkaim.canvas.challenge.painter.MessagePainter;

public class BulkFillExecutor extends AbstractCanvasDrawerExecutor {
    public BulkFillExecutor(MessagePainter painter, CanvasService canvasService) {
        super( painter, canvasService);
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
