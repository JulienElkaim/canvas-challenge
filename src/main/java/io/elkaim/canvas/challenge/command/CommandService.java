package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;

public interface CommandService {
    /**
     * Execute a command from the user.
     *
     * @param cmd to process and translate into action.
     */
    void execute(Command cmd);
}
