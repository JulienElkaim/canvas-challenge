package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.command.CommandExecutor;
import io.elkaim.canvas.challenge.painter.MessagePainter;

public abstract class AbstractExecutor implements CommandExecutor {
    protected final MessagePainter messagePainter;

    public AbstractExecutor(MessagePainter messagePainter){
        this.messagePainter = messagePainter;
    }
}
