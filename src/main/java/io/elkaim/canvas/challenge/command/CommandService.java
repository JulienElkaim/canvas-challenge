package io.elkaim.canvas.challenge.command;

import io.elkaim.canvas.challenge.command.model.Command;

public interface CommandService {
    /**
     * Send the command to the relevant Executor to execute it.
     *
     * @param cmd to process and translate into action.
     */
    void dispatch(Command cmd);
}
