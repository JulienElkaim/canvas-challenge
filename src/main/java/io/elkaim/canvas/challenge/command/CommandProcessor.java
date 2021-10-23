package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.canvas.Canvas;
import io.elkaim.canvas.challenge.command.model.Command;

public interface CommandProcessor {
    /**
     * Take
     * @param cmd
     * @param canvas to take properties and be able to provide a CanvasMask
     */
    void exec(Command cmd, Canvas canvas);
}
