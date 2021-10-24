package io.elkaim.canvas.challenge.command.executors.abstracts;

import io.elkaim.canvas.challenge.canvas.CanvasService;
import io.elkaim.canvas.challenge.painter.MessagePainter;

public abstract class AbstractCanvasDrawerExecutor extends AbstractExecutor {
    protected final Character BASIC_POINT_FORMAT = 'x';
    protected final CanvasService canvasService;

    public AbstractCanvasDrawerExecutor(MessagePainter painter, CanvasService canvasService){
        super(painter);
        this.canvasService = canvasService;
    }
}
