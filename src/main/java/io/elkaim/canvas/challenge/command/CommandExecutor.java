package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;
import io.elkaim.canvas.challenge.command.model.CommandType;

public interface CommandExecutor {

    CommandType getCommandType();
    /**
     * Take
     * @param cmd to process and translate into a CanvasMask
     */
    void execute(Command cmd);
}

//TODO: Not fitting yet, i.e CANVAS_CREATE don't return a CanvasMask, but create the Canvas
