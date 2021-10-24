package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;

public interface CommandService {
    /**
     * Take
     * @param cmd to process and translate into a CanvasMask
     */
    void execute(Command cmd);
}
